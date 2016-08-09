package com.example.evansdar.primer1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evansdar.primer1.Models.Institution;
import com.example.evansdar.primer1.R;

import java.util.ArrayList;

/**
 * Created by Brandon on 7/24/2016.
 */
public class ExhibitorAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Institution> mDataSource;

    public ExhibitorAdapter(Context context, ArrayList<Institution> items) {
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
        View rowView = mInflater.inflate(R.layout.exhibitor_layout, parent, false);
// Get title element
        TextView titleTextView =
                (TextView) rowView.findViewById(R.id.exhibitorName);

// Get subtitle element
        TextView subtitleTextView =
                (TextView) rowView.findViewById(R.id.ExhibitorAddress);

// Get detail element
        TextView detailTextView =
                (TextView) rowView.findViewById(R.id.ContinueArrow);

// Get thumbnail element
        ImageView thumbnailImageView =
                (ImageView) rowView.findViewById(R.id.exhibitorLogo);

        // 1
        Institution institution = (Institution) getItem(position);

// 2
        titleTextView.setText(institution.getName());
        subtitleTextView.setText(institution.getCity());
        detailTextView.setText(">");

// 3
        thumbnailImageView.setImageResource(R.drawable.people);
        return rowView;
    }


}
