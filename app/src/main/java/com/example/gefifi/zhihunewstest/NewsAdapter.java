package com.example.gefifi.zhihunewstest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hasee on 2019/4/11.
 */

public class NewsAdapter extends ArrayAdapter<News.StoryBean> {

    private int resourceId;

    public NewsAdapter(@NonNull Context context, int resource, @NonNull List<News.StoryBean> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        News.StoryBean storyBean = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.newsImage = (ImageView)view.findViewById(R.id.news_image);
            viewHolder.newsTitle = (TextView)view.findViewById(R.id.news_title);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

//        viewHolder.newsImage.setImageBitmap();
        viewHolder.newsTitle.setText(storyBean.getTitle());

        return view;
    }

    class ViewHolder{
        ImageView newsImage;
        TextView newsTitle;
    }
}
