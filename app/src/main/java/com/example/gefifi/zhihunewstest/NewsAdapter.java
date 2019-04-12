package com.example.gefifi.zhihunewstest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by hasee on 2019/4/11.
 */

public class NewsAdapter extends ArrayAdapter<News.StoryBean> {

    private int resourceId;
    private ListView listView;
    private LruCache<String, BitmapDrawable> mImageCache;

    public NewsAdapter(@NonNull Context context, int resource, @NonNull List<News.StoryBean> objects) {
        super(context, resource, objects);
        resourceId = resource;

        int maxCache = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxCache / 8;
        mImageCache = new LruCache<String, BitmapDrawable>(cacheSize) {
            @Override
            protected int sizeOf(String key, BitmapDrawable value) {
                return value.getBitmap().getByteCount();
            }
        };
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(listView == null){
            listView = (ListView) parent;
        }

        News.StoryBean storyBean = getItem(position);
        View view;
        ViewHolder viewHolder;
        String imgUrl;
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
        if(storyBean.getImages() != null){
            imgUrl = storyBean.getImages().get(0);
        }else {
            imgUrl = storyBean.getImage();
        }

        viewHolder.newsImage.setTag(imgUrl);

        if (mImageCache.get(imgUrl) != null) {
            viewHolder.newsImage.setImageDrawable(mImageCache.get(imgUrl));
        } else {
            ImageTask it = new ImageTask();
            it.execute(imgUrl);
        }

        return view;
    }

    class ViewHolder{
        ImageView newsImage;
        TextView newsTitle;
    }


    class ImageTask extends AsyncTask<String, Void, BitmapDrawable> {

        private String imageUrl;

        @Override
        protected BitmapDrawable doInBackground(String... params) {
            imageUrl = params[0];
            Bitmap bitmap = downloadImage();
            BitmapDrawable db = new BitmapDrawable(listView.getResources(),
                    bitmap);
            // 如果本地还没缓存该图片，就缓存
            if (mImageCache.get(imageUrl) == null) {
                mImageCache.put(imageUrl, db);
            }
            return db;
        }

        @Override
        protected void onPostExecute(BitmapDrawable result) {
            // 通过Tag找到我们需要的ImageView，如果该ImageView所在的item已被移出页面，就会直接返回null
            ImageView iv = (ImageView) listView.findViewWithTag(imageUrl);
            if (iv != null && result != null) {
                iv.setImageDrawable(result);
            }
        }

        /**
         * 根据url从网络上下载图片
         *
         * @return
         */
        private Bitmap downloadImage() {
            HttpURLConnection con = null;
            Bitmap bitmap = null;
            try {
                URL url = new URL(imageUrl);
                con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(5 * 1000);
                con.setReadTimeout(10 * 1000);
                bitmap = BitmapFactory.decodeStream(con.getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
            }

            return bitmap;
        }

    }

}
