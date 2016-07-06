package com.example.evansdar.primer1;

/**
 * Created by mccrawbj on 7/6/16.
 */
public class AgendaEvent {

    String startTime;
    String endTime;
    String presenter;
    String title;

    public AgendaEvent(String startTime,String endTime, String presenter, String title)
    {
        this.startTime=startTime;
        this.endTime=endTime;
        this.presenter=presenter;
        this.title=title;
    }

    @Override
    public String toString() {
        return startTime + " to "+ endTime + "          " + presenter + "          " + title;
    }
}