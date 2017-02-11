package com.chumbok.dailypoetry.adapter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chumbok.dailypoetry.util.drawable.ColorGenerator;
import com.chumbok.dailypoetry.FeedImageView;
import com.chumbok.dailypoetry.FeedItemList;
import com.chumbok.dailypoetry.Logger;
import com.chumbok.dailypoetry.MainActivity;
import com.chumbok.dailypoetry.R;
import com.chumbok.dailypoetry.util.drawable.TextDrawable;
import com.chumbok.dailypoetry.entity.FeedItem;
import com.chumbok.dailypoetry.service.CacheManager;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

@EBean(scope = EBean.Scope.Singleton)
public class FeedListAdapter extends BaseAdapter {

    private static final Logger logger = Logger.getLogger();

    @RootContext
    Context context;

    @RootContext
    MainActivity activity;

    @Bean
    FeedItemList feedItemList;

    private LayoutInflater inflater;
    private List<FeedItem> feedItems;

    @AfterInject
    void initAdapter() {

        if (inflater == null) {
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        feedItems = feedItemList.getFeedItemList();
    }

    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return feedItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.feed_item, null);
        }

        FeedItem item = feedItems.get(position);

        ImageView profilePic = (ImageView) convertView
                .findViewById(R.id.profilePicUrl);

        if (item.getProfilePicUrl() != null) {
            CacheManager cacheManager = new CacheManager();
            cacheManager.loadImageView(activity, item.getProfilePicUrl(), profilePic, false);
        } else {
            TextDrawable drawable = TextDrawable.builder().rect().build(item.getAuthorName().charAt(0) + "", ColorGenerator.MATERIAL.getRandomColor());
            profilePic.setImageDrawable(drawable);
            //profilePic.setVisibility(View.GONE);
        }


        TextView title = (TextView) convertView.findViewById(R.id.txtTitle);
        title.setText(item.getTitle());

        TextView miniTitle = (TextView) convertView
                .findViewById(R.id.txtMiniTitle);
        if (item.getAuthorName() != null) {
            miniTitle.setText(item.getAuthorName());
        } else {
            miniTitle.setVisibility(View.GONE);
        }


        TextView desc = (TextView) convertView
                .findViewById(R.id.txtDesc);
        if (!TextUtils.isEmpty(item.getDesc())) {
            desc.setText(item.getDesc());
            desc.setVisibility(View.VISIBLE);
        } else {
            desc.setVisibility(View.GONE);
        }

        TextView url = (TextView) convertView.findViewById(R.id.txtDescBodyUrl);
        if (item.getDescBodyUrl() != null) {
            url.setText(Html.fromHtml("<a href=\"" + item.getDescBodyUrl() + "\">"
                    + item.getDescBodyUrl() + "</a> "));
            url.setMovementMethod(LinkMovementMethod.getInstance());// Making url clickable
            url.setVisibility(View.VISIBLE);
        } else {
            url.setVisibility(View.GONE);
        }

        FeedImageView feedImageView = (FeedImageView) convertView
                .findViewById(R.id.feedImage1);
        if (item.getImageUrl() != null) {
            //feedImageView.setImageUrl(item.getImageUrl(), imageLoader);
            feedImageView.setVisibility(View.VISIBLE);
            feedImageView
                    .setResponseObserver(new FeedImageView.ResponseObserver() {
                        @Override
                        public void onError() {
                        }

                        @Override
                        public void onSuccess() {
                        }
                    });
        } else {
            feedImageView.setVisibility(View.GONE);
        }

        return convertView;
    }


}