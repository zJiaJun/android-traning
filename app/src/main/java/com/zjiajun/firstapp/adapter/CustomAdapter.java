package com.zjiajun.firstapp.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.model.CustomItem;

import java.util.List;
import java.util.Random;


/**
 * Created by zhujiajun
 * 15/7/6 14:29
 */
public class CustomAdapter extends ArrayAdapter<CustomItem> {

    private int resourceId;

    public CustomAdapter(Context context, int resource, List<CustomItem> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    private static class ViewHolder {
        ImageView customImage;
        TextView customName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("getView " + position + " " + convertView);
        CustomItem item = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view =  LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.customImage = ((ImageView) view.findViewById(R.id.custom_image));
            viewHolder.customName = ((TextView) view.findViewById(R.id.custom_name));
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.customImage.setImageResource(item.getImageId());
        TextView customName = viewHolder.customName;
        customName.setText(item.getName());
        customName.setTextColor(ColorStateList.valueOf(getRandomColor()));
        return view;
    }

    private int getRandomColor() {
        int[] colors = {Color.BLACK, Color.BLUE,Color.RED};
        int index = new Random().nextInt(colors.length);
        return colors[index];
    }

}
