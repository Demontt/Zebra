package httpapphost;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Title:    HttpAppHostReudce
 * Description:
 *
 * @author dtt
 * @data 2018-09-13 09:36
 **/

public class HttpAppHostReudce extends Reducer<Text,HttpAppHost,Text, NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<HttpAppHost> values, Context context) throws IOException, InterruptedException {

        HttpAppHost appHost = new HttpAppHost();
        for (HttpAppHost value : values) {

            appHost.setReportTime(value.getReportTime());
            appHost.setAppType(value.getAppType());
            appHost.setAppSubType(value.getAppSubType());
            appHost.setUserIp(value.getUserIp());
            appHost.setUserPort(value.getUserPort());
            appHost.setAppServerIp(value.getAppServerIp());
            appHost.setAppServerPort(value.getAppServerPort());
            appHost.setHost(value.getHost());
            appHost.setCellid(value.getCellid());

            appHost.setAttemps(appHost.getAttemps()+value.getAttemps());
            appHost.setAccepts(appHost.getAccepts()+value.getAccepts());
            appHost.setTrafficUL(appHost.getTrafficUL()+value.getTrafficUL());
            appHost.setTracfficDL(appHost.getTracfficDL()+value.getTracfficDL());
            appHost.setRetranUL(appHost.getRetranUL()+value.getRetranUL());
            appHost.setRetranDL(appHost.getRetranDL()+value.getRetranDL());
            appHost.setFailCount(appHost.getFailCount()+value.getFailCount());
            appHost.setTransDelay(appHost.getTransDelay()+value.getTransDelay());
        }

//        System.out.println(appHost);

        String[] info = appHost.getReportTime().split("_");
        String line = info[1].substring(0,14);
        String time = line.substring(0,4)+"-"+line.substring(4,6)+"-"+line.substring(6,8)
                +" "+line.substring(8,10)+":"+line.substring(10,12)+":"+line.substring(12,14);

        String mykey = time+"|"+appHost.getAppType()+"|"+
                appHost.getAppSubType()+"|"+
                appHost.getUserIp()+"|"+
                appHost.getUserPort()+"|"+
                appHost.getAppServerIp()+"|"+
                appHost.getAppServerPort()+"|"+
                appHost.getHost()+"|"+
                appHost.getCellid()+"|"+
                appHost.getAttemps()+"|"+
                appHost.getAccepts()+"|"+
                appHost.getTrafficUL()+"|"+
                appHost.getTracfficDL()+"|"+
                appHost.getRetranUL()+"|"+
                appHost.getRetranDL()+"|"+
                appHost.getFailCount()+"|"+
                appHost.getTransDelay();

//        System.out.println(mykey);
        context.write(new Text(mykey),NullWritable.get());

    }
}