package com.example.evansdar.primer1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evansdar.primer1.Models.Event;
import com.example.evansdar.primer1.R;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Brandon on 7/24/2016.
 */
public class EventAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Event> mDataSource;

    public EventAdapter(Context context, ArrayList<Event> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //1
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    //2
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    //3
    @Override
    public long getItemId(int position) {
        return position;
    }

    //4
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.event_layout, parent, false);
// Get title element
        TextView titleTextView =
                (TextView) rowView.findViewById(R.id.eventTitle);

// Get subtitle element
        TextView subtitleTextView =
                (TextView) rowView.findViewById(R.id.eventTime);

// Get detail element
        TextView detailTextView =
                (TextView) rowView.findViewById(R.id.ContinueArrowForEvent);

// Get thumbnail element
        ImageView thumbnailImageView =
                (ImageView) rowView.findViewById(R.id.eventLogo);

        // 1
        Event event = (Event) getItem(position);

// 2

        titleTextView.setText(event.getTitle());

        DateTime dt = new DateTime(event.getStartTimeUtc());
        Date jdkDate = dt.toDate();
        Calendar formattedDate = Calendar.getInstance();
        formattedDate.setTime(jdkDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d h:mm");
        dateFormat.setTimeZone(formattedDate.getTimeZone());

        DateTime dt2 = new DateTime(event.getEndTimeUtc());
        Date jdkDate2 = dt2.toDate();
        Calendar formattedDate2 = Calendar.getInstance();
        formattedDate2.setTime(jdkDate2);
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("h:mm");
        dateFormat2.setTimeZone(formattedDate2.getTimeZone());


        subtitleTextView.setText(dateFormat.format(formattedDate.getTime()) + " - " + dateFormat2.format(formattedDate2.getTime()));

        detailTextView.setText("");

// 3
        thumbnailImageView.setImageResource(R.drawable.unost);
        return rowView;
    }


}
