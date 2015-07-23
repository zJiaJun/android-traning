package com.zjiajun.firstapp.activity;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zjiajun.firstapp.R;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by zhujiajun
 * 15/7/9 21:10
 */
public class MultipleItemsListActivity extends ListActivity {

    private MyCustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new MyCustomAdapter();
        for (int i = 0; i < 50; i++) {
            mAdapter.addItem("item " + i);
            if (i % 4 == 0) {
                mAdapter.addSeparatorItem("separator " + i);
            }
        }
        setListAdapter(mAdapter);

    }

    private class MyCustomAdapter extends BaseAdapter {

        private static final int TYPE_ITEM = 0;
        private static final int TYPE_SEPARATOR = 1;
        private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;
        private ArrayList<String> mData = new ArrayList<>();
        private LayoutInflater mInflater;
        private TreeSet<Integer> mSeparatorsSet = new TreeSet<>();

        public MyCustomAdapter() {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void addItem(final String item) {
            mData.add(item);
            notifyDataSetChanged();
        }

        public void addSeparatorItem(final String item) {
            mData.add(item);
            mSeparatorsSet.add(mData.size()-1);
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            return mSeparatorsSet.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
        }

        @Override
        public int getViewTypeCount() {
            return TYPE_MAX_COUNT;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            System.out.println("getView " + position + " " + convertView);
            ViewHolder viewHolder;
            int viewType = getItemViewType(position);
            if (convertView == null) {
                viewHolder = new ViewHolder();
                switch (viewType) {
                    case TYPE_ITEM :
                        convertView = mInflater.inflate(android.R.layout.simple_list_item_1,null);
                        viewHolder.textView = (TextView) convertView.findViewById(android.R.id.text1);
                        break;
                    case TYPE_SEPARATOR :
                        convertView = mInflater.inflate(R.layout.item2,null);
                        viewHolder.textView = (TextView) convertView.findViewById(R.id.textSeparator);
                        break;
                    default:
                }
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(mData.get(position));
            return convertView;
        }
    }

    private static class ViewHolder {
        TextView textView;
    }

}
