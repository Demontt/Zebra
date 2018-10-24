package httpapphost;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Title:    HttpAppHost
 * Description:    javabean数据
 *
 * @author dtt
 * @data 2018-09-13 09:29
 **/

public class HttpAppHost implements Writable {
    private String reportTime;//小时 时间片 文件名中：103_20150615143630_00_00_000.csv
    private int appType;//应用大类 22
    private int appSubType;//应用小类 23
    private String userIp;//用户IP 26
    private int userPort;//用户端口 28
    private String appServerIp;//服务器IP 30
    private int appServerPort;//服务器端口32
    private String host;//域名 58
    private String cellid;//小区ID 16 如果为空，手动补9个0

    // 需要计算：首先要获取一个中间数据：appTypeCode 18

    //如果appTypeCode=103，则设置为1，否则为0
    private int attemps;//尝试次数

    //如果appTypeCode=103，且第54个字段包含在(10,11,12,13,14,15,32,33,34,35,36,37,38,48,49,50,51,52,53,54,55,199,200,201,202,203,204,205,206,302,304,306)中，且第67个字段为空("")，则设置此值为1，否则为0
    private int accepts;//接受次数

    //如果appTypeCode=103，则设置接下来4个字段
    private long trafficUL;//上行流量 33
    private long tracfficDL;//下行流量 34
    private long retranUL;//重传上行数 39
    private long retranDL;//重传下行数 40

    //如果appTypeCode=103，且第54字段等于1，且67字段等于0，则设置此值为1，否则为0
    private int failCount;//延时失败次数

    //如果appTypeCode=103，设置此值为：第20字段-第19字段 的值
    private long transDelay;//传输时延

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(reportTime);
        dataOutput.writeInt(appType);
        dataOutput.writeInt(appSubType);
        dataOutput.writeUTF(userIp);
        dataOutput.writeInt(userPort);
        dataOutput.writeUTF(appServerIp);
        dataOutput.writeInt(appServerPort);
        dataOutput.writeUTF(host);
        dataOutput.writeUTF(cellid);

        dataOutput.writeInt(attemps);
        dataOutput.writeInt(accepts);
        dataOutput.writeLong(trafficUL);
        dataOutput.writeLong(tracfficDL);
        dataOutput.writeLong(retranUL);
        dataOutput.writeLong(retranDL);
        dataOutput.writeInt(failCount);
        dataOutput.writeLong(transDelay);
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.reportTime = dataInput.readUTF();
        this.appType = dataInput.readInt();
        this.appSubType = dataInput.readInt();
        this.userIp = dataInput.readUTF();
        this.userPort = dataInput.readInt();
        this.appServerIp = dataInput.readUTF();
        this.appServerPort = dataInput.readInt();
        this.host = dataInput.readUTF();
        this.cellid = dataInput.readUTF();

        this.attemps = dataInput.readInt();
        this.accepts = dataInput.readInt();
        this.trafficUL = dataInput.readLong();
        this.tracfficDL = dataInput.readLong();
        this.retranUL = dataInput.readLong();
        this.retranDL = dataInput.readLong();
        this.failCount = dataInput.readInt();
        this.transDelay = dataInput.readLong();
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public int getAppSubType() {
        return appSubType;
    }

    public void setAppSubType(int appSubType) {
        this.appSubType = appSubType;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public int getUserPort() {
        return userPort;
    }

    public void setUserPort(int userPort) {
        this.userPort = userPort;
    }

    public String getAppServerIp() {
        return appServerIp;
    }

    public void setAppServerIp(String appServerIp) {
        this.appServerIp = appServerIp;
    }

    public int getAppServerPort() {
        return appServerPort;
    }

    public void setAppServerPort(int appServerPort) {
        this.appServerPort = appServerPort;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCellid() {
        return cellid;
    }

    public void setCellid(String cellid) {
        this.cellid = cellid;
    }

    public int getAttemps() {
        return attemps;
    }

    public void setAttemps(int attemps) {
        this.attemps = attemps;
    }

    public int getAccepts() {
        return accepts;
    }

    public void setAccepts(int accepts) {
        this.accepts = accepts;
    }

    public long getTrafficUL() {
        return trafficUL;
    }

    public void setTrafficUL(long trafficUL) {
        this.trafficUL = trafficUL;
    }

    public long getTracfficDL() {
        return tracfficDL;
    }

    public void setTracfficDL(long tracfficDL) {
        this.tracfficDL = tracfficDL;
    }

    public long getRetranUL() {
        return retranUL;
    }

    public void setRetranUL(long retranUL) {
        this.retranUL = retranUL;
    }

    public long getRetranDL() {
        return retranDL;
    }

    public void setRetranDL(long retranDL) {
        this.retranDL = retranDL;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public long getTransDelay() {
        return transDelay;
    }

    public void setTransDelay(long transDelay) {
        this.transDelay = transDelay;
    }

    public HttpAppHost() {
        this.attemps = 0;
        this.accepts = 0;
        this.trafficUL = 0;
        this.tracfficDL = 0;
        this.retranUL = 0;
        this.retranDL = 0;
        this.failCount = 0;
        this.transDelay = 0;
    }



    public HttpAppHost(String reportTime, int appType, int appSubType, String userIp, int userPort, String appServerIp, int appServerPort, String host, String cellid) {
        this.reportTime = reportTime;
        this.appType = appType;
        this.appSubType = appSubType;
        this.userIp = userIp;
        this.userPort = userPort;
        this.appServerIp = appServerIp;
        this.appServerPort = appServerPort;
        this.host = host;
        this.cellid = cellid;
    }

    @Override
    public String toString() {
        return "HttpAppHost{" +
                "reportTime='" + reportTime + '\'' +
                ", appType=" + appType +
                ", appSubType=" + appSubType +
                ", userIp='" + userIp + '\'' +
                ", userPort=" + userPort +
                ", appServerIp='" + appServerIp + '\'' +
                ", appServerPort=" + appServerPort +
                ", host='" + host + '\'' +
                ", cellid='" + cellid + '\'' +
                ", attemps=" + attemps +
                ", accepts=" + accepts +
                ", trafficUL=" + trafficUL +
                ", tracfficDL=" + tracfficDL +
                ", retranUL=" + retranUL +
                ", retranDL=" + retranDL +
                ", failCount=" + failCount +
                ", transDelay=" + transDelay +
                '}';
    }


}