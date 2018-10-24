package utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * Title:    HDFS
 * Description:    工具分装
 *
 * @author dtt
 * @data 2018-09-05 15:38
 **/

public class HDFS implements IHDFS{
    private String url;
    private Configuration conf;
    private FileSystem fs;
    public HDFS(String url){
        this.url = url;
        this.conf = new Configuration();
    }

//    显示文件
    public void cat(String folder) throws IOException, URISyntaxException{
        fs = FileSystem.get(new URI(url),conf);
        System.out.println("cat: " + folder);
        Path path = new Path(folder);
        FSDataInputStream fsdis = fs.open(path);
        IOUtils.copyBytes(fsdis,System.out,4096,false);
        IOUtils.closeStream(fsdis);
        fs.close();
    }

//    删除文件
    public void rm(String folder) throws IOException, URISyntaxException{
        fs = FileSystem.get(new URI(url),conf);
        Path path = new Path(folder);
        if(fs.deleteOnExit(path)){
            fs.delete(path,false);
            System.out.println("delete:" + folder);
        }else{
            System.out.println("The file is not exist!");
        }
        fs.close();
    }

//    删除多个文件
    public void rms(String[] folders) throws IOException, URISyntaxException{
        fs = FileSystem.get(new URI(url),conf);
        for (String folder : folders) {
            Path path = new Path(folder);
            if(fs.deleteOnExit(path)){
                fs.delete(path,false);
                System.out.println("delete:" + folder);
            }else{
                System.out.println("The file is not exist!");
            }
        }
        fs.close();
    }

//    下载文件
    public void get(String remote,  String local) throws IllegalArgumentException, IOException, URISyntaxException {
        // 建立联系
        fs = FileSystem.get(new URI(url),conf);
        fs.copyToLocalFile(new Path(remote), new Path(local));
        System.out.println("Get From :   " + remote  + "   To :" + local);
        fs.close();
    }

//    下载多个文件
    public void gets(String[] remotes,  String[] locals) throws IllegalArgumentException, IOException, URISyntaxException {
        // 建立联系
        fs = FileSystem.get(new URI(url),conf);
        int len = remotes.length;
        for(int i=0;i<len;i++){
            fs.copyToLocalFile(new Path(remotes[i]), new Path(locals[i]));
            System.out.println("Get From :   " + remotes[i]  + "   To :" + locals[i]);
        }
        fs.close();
    }

//    上传文件
    public void put(String local, String remote) throws IOException, URISyntaxException {
        // 建立联系
        fs = FileSystem.get(new URI(url),conf);
        fs.copyFromLocalFile(new Path(local), new Path(remote));
        System.out.println("Put :" + local  + "   To : " + remote);
        fs.close();
    }

//    上传多个文件
    public void puts(String[] locals, String[] remotes) throws IOException, URISyntaxException {
        // 建立联系
        fs = FileSystem.get(new URI(url),conf);
        int len = locals.length;
        for(int i=0;i<len;i++){
            fs.copyFromLocalFile(new Path(locals[i]), new Path(remotes[i]));
            System.out.println("Put :" + locals[i]  + "   To : " + remotes[i]);
        }
        fs.close();
    }

//    递归列出全部目录
    public void lsr(String folder) throws IOException, URISyntaxException {
        //与hdfs建立联系
        fs = FileSystem.get(new URI(url),conf);
        Path path = new Path(folder);
        //得到该目录下的全部文件
        FileStatus[] fileList = fs.listStatus(path);
        for (FileStatus f : fileList) {
            System.out.printf("name: %s   |   folder: %s  |   size: %d\n", f.getPath(),  f.isDirectory() , f.getLen());
            try{
                FileStatus[] fileListR = fs.listStatus(f.getPath());
                for(FileStatus fr:fileListR){
                    System.out.printf("name: %s   |   folder: %s  |   size: %d\n", fr.getPath(),  fr.isDirectory() , fr.getLen());
                }
            }finally{
                continue;
            }
        }
        fs.close();
    }

//    列出全部目录
    public void ls(String folder) throws IOException, URISyntaxException {
        //与hdfs建立联系
        fs = FileSystem.get(new URI(url),conf);
        Path path = new Path(folder);
        //得到该目录下的全部文件
        FileStatus[] fileList = fs.listStatus(path);
        for (FileStatus f : fileList) {
            System.out.printf("name: %s   |   folder: %s  |   size: %d\n", f.getPath(),  f.isDirectory() , f.getLen());
        }
        fs.close();
    }

//    删除目录
    public void rmr(String folder) throws IOException, URISyntaxException {
        //与hdfs建立联系
        fs = FileSystem.get(new URI(url),conf);
        Path path = new Path(folder);
        fs.delete(path,true);
        System.out.println("delete:" + folder);
        fs.close();
    }

//    删除多个目录
    public void rmrs(String[] folders) throws IOException, URISyntaxException {
        //与hdfs建立联系
        fs = FileSystem.get(new URI(url),conf);
        for (String folder : folders) {
            Path path = new Path(folder);
            fs.delete(path,true);
            System.out.println("delete:" + folder);
        }
        fs.close();
    }

//    创建目录
    public void mkdir(String folder) throws IOException, URISyntaxException {
        //与hdfs建立联系
        fs = FileSystem.get(new URI(url),conf);
        Path path = new Path(folder);
        if (!fs.exists(path)) {
            fs.mkdirs(path);
            System.out.println("Create: " + folder);
        }else{
            System.out.println("it is have exist:" + folder);
        }
        fs.close();
    }

//    创建多个目录
    public void mkdirs(String[] folders) throws IOException, URISyntaxException {
        //与hdfs建立联系
        fs = FileSystem.get(new URI(url),conf);
        for (String folder : folders) {
            Path path = new Path(folder);
            if (!fs.exists(path)) {
                fs.mkdirs(path);
                System.out.println("Create: " + folder);
            }else{
                System.out.println("it is have exist:" + folder);
            }
        }
        fs.close();
    }

//    判断文件是否存在
    private void isExist(String folder) throws IOException, URISyntaxException {
        //与hdfs建立联系
        fs = FileSystem.get(new URI(url),conf);
        Path path = new Path(folder);
        if(fs.exists(path)){
            System.out.println("it is have exist:" + folder);
        }else{
            System.out.println("it is not exist:" + folder);
        }
        fs.close();
    }
//    重命名文件
    public void rename(String old, String now) throws IOException, URISyntaxException{
        //与hdfs建立联系
        fs = FileSystem.get(new URI(url),conf);
        Path path = new Path(old);
        Path path1 = new Path(now);
        if(fs.exists(path)){
            fs.rename(path,path1);
            System.out.println(old + "renamed to " + now);
        } else{
            System.out.println("it is not exist:" + old);
        }
    }


}