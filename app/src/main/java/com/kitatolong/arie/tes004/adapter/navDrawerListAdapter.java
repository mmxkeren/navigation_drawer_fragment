package com.kitatolong.arie.tes004.adapter;

import com.kitatolong.arie.tes004.model.navDrawerItem;
import com.kitatolong.arie.tes004.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Arie on 2/25/2016.
 */
public class navDrawerListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<navDrawerItem> navDrawerItems;

    public navDrawerListAdapter(Context context, ArrayList<navDrawerItem> navDrawerItems){
        this.context=context;
        this.navDrawerItems=navDrawerItems;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txTitle = (TextView) convertView.findViewById(R.id.title);
        TextView txCounter = (TextView) convertView.findViewById(R.id.counter);

        imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
        txTitle.setText(navDrawerItems.get(position).getTitle());

        //displaying count
        //check whether is set visible or not

        if(navDrawerItems.get(position).getCountervisibility()){
            txCounter.setText(navDrawerItems.get(position).getCount());
        } else{
            txCounter.setVisibility(View.GONE);
        }

        return convertView;
    }
}