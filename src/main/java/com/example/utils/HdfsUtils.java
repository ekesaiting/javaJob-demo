package com.example.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class HdfsUtils {
    private  static String  hostAddress="hdfs://101.37.24.243:9000";
    private static FileSystem fs;
    public static Path setHostAddress(Configuration conf,String filePath){
        conf.set("fs.defaultFS",hostAddress);
        conf.set("fs.default.name", hostAddress);
        try {
             fs = FileSystem.get(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Path(filePath);
    }
    public static Path getFullPath(String filePath){
        return new Path(hostAddress+filePath);
    }
    public static boolean checkFileExist(Path outPath) {
        try {
            if (fs.exists(outPath)) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void deleteFileOrPath(Path outPath) {
        try {
            fs.delete(outPath, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
