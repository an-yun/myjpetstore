package myjpetstore.domain;

/**
 * Created by zuo on 2015/6/6.
 */
public class Log {
    private String time;
    private String event;
    public Log()
    {
        this("","");
    }
    public Log(String time, String event) {
        this.time = time;
        this.event = event;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
