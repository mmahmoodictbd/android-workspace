package com.chumbok.news.service;


import android.app.Activity;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class CacheManager {

    public void loadImageView(Activity activity, String url, ImageView imageView, boolean fromNetwork) {

        if (fromNetwork) {
            Picasso.with(activity)
                    .load(url)
                    .into(imageView);
        } else {
            Picasso.with(activity)
                    .load(url)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(imageView, new Callback() {

                        Activity activity;
                        String url;
                        ImageView imageView;

                        public Callback init(Activity activity, String url, ImageView imageView) {
                            this.activity = activity;
                            this.url = url;
                            this.imageView = imageView;
                            return this;
                        }

                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            //Try again online if cache failed
                            loadImageView(activity, url, imageView, true);
                        }
                    }.init(activity, url, imageView));

        }

    }
}
