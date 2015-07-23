package com.zjiajun.firstapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zjiajun.firstapp.R;
import com.zjiajun.firstapp.model.MsgItem;

import java.util.List;

/**
 * Created by zhujiajun
 * 15/7/7 20:47
 */
public class MsgAdapter extends ArrayAdapter<MsgItem> {

    private int resourceId;

    public MsgAdapter(Context context, int resource, List<MsgItem> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MsgItem item = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.tvLeft = (TextView) view.findViewById(R.id.left_msg);
            viewHolder.tvRight = (TextView) view.findViewById(R.id.right_msg);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        int type = item.getType();
        String content = item.getContent();
        if (MsgItem.TYPE_SENT == type) {
            //如果是发送消息,显示右边的消息布局,隐藏左边
            viewHolder.tvRight.setVisibility(View.GONE);
            viewHolder.tvLeft.setVisibility(View.VISIBLE);
            viewHolder.tvLeft.setText(content);
        }
        if (MsgItem.TYPE_RECEIVED == type) {
            //如果是接收消息,显示左边的消息布局,隐藏右边
            viewHolder.tvLeft.setVisibility(View.GONE);
            viewHolder.tvRight.setVisibility(View.VISIBLE);
            viewHolder.tvRight.setText(content);
        }
        return view;
    }

    private class ViewHolder {
        TextView tvLeft;
        TextView tvRight;
    }
}
