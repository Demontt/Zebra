package utils;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Title:    IHDFS
 * Description:    hdfs操作接口
 *
 * @author dtt
 * @data 2018-09-05 16:46
 **/

public interface IHDFS {
//    显示文件
    void cat(String folder) throws IOException, URISyntaxException;
//    删除文件
    void rm(String folder) throws IOException, URISyntaxException;
//    删除多个文件
    void rms(String[] folders) throws IOException, URISyntaxException;
//    下载文件
    void get(String remote, String local) throws IllegalArgumentException, IOException, URISyntaxException;
//    下载多个文件
    void gets(String[] remotes, String[] locals) throws IllegalArgumentException, IOException, URISyntaxException;
//    上传文件
    void put(String local, String remote) throws IOException, URISyntaxException;
//    上传多个文件
    void puts(String[] locals, String[] remotes) throws IOException, URISyntaxException;
//    递归列出所有目录
    void lsr(String folder) throws IOException, URISyntaxException;
//    列出所有目录
    void ls(String folder) throws IOException, URISyntaxException;
//    删除目录
    void rmr(String folder) throws IOException, URISyntaxException;
//    删除多个目录
    void rmrs(String[] folder) throws IOException, URISyntaxException;
//    创建目录
    void mkdir(String folder) throws IOException, URISyntaxException;
//    创建多个目录
    void mkdirs(String[] folders) throws IOException, URISyntaxException;
//    重命名文件
    void rename(String old, String now) throws IOException, URISyntaxException;

//    待更新...
}