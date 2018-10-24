package httpapphost;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import utils.HDFS;

/**
 * Title:    HttpAppHostMapReudce
 * Description:    main
 *
 * @author dtt
 * @data 2018-09-13 09:36
 **/

public class HttpAppHostMapReudce {
    private static String myurl = "hdfs://192.168.40.129:9000";
    private static String localdir = "src/main/java/httpapphost";
    private static String webdir = "hdfs://192.168.40.129:9000/httpapphost";

    public static void main(String[] args)throws Exception {
        HDFS hdfs = new HDFS(myurl);
        hdfs.rmr("/httpapphost");
        hdfs.mkdir("/httpapphost");
        hdfs.put(localdir + "/103_20150615143630_00_00_000.csv", "/httpapphost");

        Configuration conf = new Configuration();
        // 创建job对象
        Job job = Job.getInstance(conf, "httpapphostMapReduce");

        // 设置运行job的类
        job.setJarByClass(HttpAppHostMapReudce.class);

        // 设置 mapper 类
        job.setMapperClass(HttpAppHostMapper.class);

        // 设置 map 输出的key value
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(HttpAppHost.class);

        // 设置 reduce 类
        job.setReducerClass(HttpAppHostReudce.class);

        // 设置reduce 输出的key value
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        // 设置输入输出路径
        FileInputFormat.setInputPaths(job,new Path(myurl + "/httpapphost"));
        FileOutputFormat.setOutputPath(job,new Path(webdir + "/output"));

        //         提交job
        boolean b = job.waitForCompletion(true);

        if(!b){
            System.out.println("httpapphostMapReduce" + " task fail!");
        } else{
            System.out.println("httpapphostMapReduce" + " task success");
        }
//        hdfs.get(webdir+ "/output/part-r-00000",localdir+"/part-r-00000");
//        hdfs.cat(webdir + "/output/part-r-00000");
    }

}