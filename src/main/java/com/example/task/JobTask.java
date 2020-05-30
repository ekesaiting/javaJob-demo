package com.example.task;

import com.example.pojo.Job;
import com.example.service.JobService;
import com.example.utils.SalaryUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

/**
 * 脚本任务类，执行爬虫程序，获取数据并保存到数据库中
 */
@Component
@Slf4j
public class JobTask {
    @Autowired
    private JobService jobService;
    //每1个小时执行一次下载任务
    //@Scheduled(fixedDelay = 60*1000*60)
    public void getJobInfo(){

        //每次默认下载十页
        for (int i=2;i<=10;i++){
            int num=0;
            //默认url为前程无忧主页搜索java的url
            String tarUrl="https://search.51job.com/list/000000,000000,0000,00,9,99,java,2,"+ i+".html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=\n";
            Document document = parse(tarUrl,"GBK");
            Elements items = document.select("div#resultList div.el:not(.title)");
            log.info("开始下载第"+i+"页数据");
            for (Element item : items) {
                String jobInfoUrl = item.select("p.t1 a").attr("href");
                Document jobInfoDoc = parse(jobInfoUrl,"GBK");
                //主键id
                String id = item.select("p.t1 input").attr("value");
                if (id==null)
                    continue;
                //如果数据库已经有这条数据，跳过此次插入
                Job returnJob = jobService.findJobById(id);
                if (returnJob!=null){
                    continue;
                }
                //新建job对象，保存抓取到的数据
                Job job = new Job();
                job.setJobId(id);
                //设置详情页url
                job.setJobUrl(jobInfoUrl);
                //设置公司名称
                try{
                    String companyName = jobInfoDoc.selectFirst("div.cn p.cname a").text();
                    job.setCompanyName(companyName);
                }catch (RuntimeException e){
                  continue;
                }
                //String[] conditions= jobInfoDoc.select("p.msg").attr("title").toString().split("\\|");
                //设置公司地址,跳过没有填写地址的公司
                String address= item.select("span.t3").text();
                if (address.contains("异地招聘"))
                    continue;
                job.setJobAddress(address);

                // String address=conditions[0];
                //设置详细工作地址
                String temAddress = jobInfoDoc.select("div.bmsg:not(.job_msg) p").text();
                String detailAddress=temAddress.substring(temAddress.lastIndexOf("：")+1);
                job.setJobDetailAddress(detailAddress);
                //公司详情
                String companyInfo = jobInfoDoc.select("div.tmsg").text();
                job.setCompanyInfo(companyInfo);
                //工作名称
                String jobName= jobInfoDoc.select("div.cn h1").text();
                job.setJobName(jobName);
                //基本要求或信息
                 String[] conditions= jobInfoDoc.select("p.msg").attr("title").toString().split("\\|");
                 job.setConditions(conditions);
                //工作信息
                String jobInfo=jobInfoDoc.select("div.bmsg.job_msg.inbox").text();
                job.setJobInfo(jobInfo);
                //最大/小工资,调用工具类进行单位同意转换成以‘年’为单位
                String salaryStr = item.select("span.t4").text();
                Integer[] salary=null;
                //如果工资为空跳过
                if (salaryStr.length()==0||salaryStr==null)
                    continue;
                else {
                    salary=SalaryUtils.getSalary(salaryStr);
                    job.setMinSalary(salary[0]);
                    job.setMaxSalary(salary[1]);
                }
                //发布时间
                String date = item.select("span.t5").text();
                job.setDate(date);
                //保存数据到数据库中
                jobService.saveJob(job);
                num++;
            }
            log.info("第"+i+"页下载完成，本页保存"+num+"条数据");
        }
    }

    /**
     * 解析url,获取document对象
     * @param tarUrl
     * @param charSet
     * @return
     */
    private  Document parse(String tarUrl,String charSet) {
        Document document=null;
        try {
          document = Jsoup.parse(new URL(tarUrl).openStream(), charSet, tarUrl);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("解码错误，请更换字符集");
        }
        return document;
    }
}
