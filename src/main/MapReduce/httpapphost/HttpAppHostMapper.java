package httpapphost;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Title:    HttpAppHostMapper
 * Description:    mapper
 *
 * @author dtt
 * @data 2018-09-13 09:35
 **/

public class HttpAppHostMapper extends Mapper<LongWritable, Text,Text,HttpAppHost> {
    static Integer[] arr = {10,11,12,13,14,15,32,33,34,35,36,37,38,48,49,50,51,52,53,54,55,199,200,201,202,203,204,205,206,302,304,306};

    public static int binSearch(int key){
        int l = 0;
        int r = arr.length-1;
        int mid = (l+r)>>1;

        while(arr[mid]!=key){
            if(arr[mid]<key){
                l=mid+1;
            } else if(arr[mid]>key){
                r = mid-1;
            }
            mid=(l+r)>>1;
            if(l>r) {
                return -1;
            }
        }
        return mid;
    }


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        InputSplit inputSplit = context.getInputSplit();
        String filename = ((FileSplit)inputSplit).getPath().getName();
        String line = value.toString();
        String[] info = line.split("\\|");
        if(info[16].equals("")){
            info[16]= "000000000";
        }
        HttpAppHost appHost = new HttpAppHost(filename.split("\\.")[0],
                Integer.parseInt(info[22]),
                Integer.parseInt(info[23]),
                info[26],
                Integer.parseInt(info[28]),
                info[30],
                Integer.parseInt(info[32]),
                info[58],
                info[16]);

        int appTypeCode = Integer.parseInt(info[18]);
        if(appTypeCode == 103){
            appHost.setAttemps(1);
            if(binSearch(Integer.parseInt(info[54]))!=-1&&info[67].equals("")){
                appHost.setAccepts(1);
            } else{
                appHost.setAccepts(0);
            }
            appHost.setTrafficUL(Long.parseLong(info[33]));
            appHost.setTracfficDL(Long.parseLong(info[34]));
            appHost.setRetranUL(Long.parseLong(info[39]));
            appHost.setRetranDL(Long.parseLong(info[40]));

            if(info[54].equals("1")&&info[67].equals("0")){
                appHost.setFailCount(1);
            }else{
                appHost.setFailCount(0);
            }
            appHost.setTransDelay(Long.parseLong(info[20])-Long.parseLong(info[19]));

        }else{
            appHost.setAttemps(0);
            appHost.setAccepts(0);
            appHost.setFailCount(0);
        }
        String str = appHost.getReportTime()+"|"+
        appHost.getAppType()+"|"+
        appHost.getAppSubType()+"|"+
        appHost.getUserIp()+"|"+
        appHost.getUserPort()+"|"+
        appHost.getAppServerIp()+"|"+
        appHost.getAppServerPort()+"|"+
        appHost.getHost()+"|"+
        appHost.getCellid();
//        System.out.println(appHost);

        context.write(new Text(str),appHost);
    }
}