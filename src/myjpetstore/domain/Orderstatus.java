package myjpetstore.domain;

import java.util.Date;

/**
 * Created by zuo on 2015/6/6.
 */
public class Orderstatus {
    private int orderid;
    private int linenum;
    private Date timestamp;
    private String status;
    public Orderstatus(){}

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getLinenum() {
        return linenum;
    }

    public void setLinenum(int linenum) {
        this.linenum = linenum;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Orderstatus(int orderid, int linenum, Date timestamp, String status) {
        this.orderid = orderid;
        this.linenum = linenum;
        this.timestamp = timestamp;
        this.status = status;
    }
}
