package com.chumbok.news.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chumbok.news.FeedItemList;
import com.chumbok.news.Logger;
import com.chumbok.news.MainActivity;
import com.chumbok.news.R;
import com.chumbok.news.entity.FeedItem;
import com.chumbok.news.service.CacheManager;

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

        Typeface font = Typeface.createFromAsset(context.getAssets(), "SolaimanLipi.ttf");

        TextView title = (TextView) convertView.findViewById(R.id.txtTitle);
        title.setTypeface(font);

        String content = "<a href=\"" + item.getDescBodyUrl() + "\">" + item.getTitle() + "</a>";
        Spannable s = (Spannable) Html.fromHtml(content);
        for (URLSpan u : s.getSpans(0, s.length(), URLSpan.class)) {
            s.setSpan(new UnderlineSpan() {
                public void updateDrawState(TextPaint tp) {
                    tp.setUnderlineText(false);
                }
            }, s.getSpanStart(u), s.getSpanEnd(u), 0);
        }
        title.setText(s);

        title.setMovementMethod(LinkMovementMethod.getInstance());

        TextView miniTitle = (TextView) convertView
                .findViewById(R.id.txtMiniTitle);
        if (item.getSourceName() != null) {
            miniTitle.setTypeface(font);
            miniTitle.setText(Html.fromHtml(item.getSourceName()));
            logger.d(item.getSourceName());

        } else {
            miniTitle.setVisibility(View.GONE);
        }


        TextView desc = (TextView) convertView
                .findViewById(R.id.txtDesc);
        if (!TextUtils.isEmpty(item.getDesc())) {
            desc.setTypeface(font);
            desc.setText(item.getDesc());
            desc.setVisibility(View.VISIBLE);
        } else {
            desc.setVisibility(View.GONE);
        }

        TextView url = (TextView) convertView.findViewById(R.id.txtDescBodyUrl);
        if (item.getDescBodyUrl() != null) {
            url.setTypeface(font);
            url.setText(Html.fromHtml("<a href=\"" + item.getDescBodyUrl() + "\">বিস্তারিত</a>"));
            url.setMovementMethod(LinkMovementMethod.getInstance());
            url.setVisibility(View.VISIBLE);
        } else {
            url.setVisibility(View.GONE);
        }

        ImageView feedImageView = (ImageView) convertView
                .findViewById(R.id.feedImage1);
        if (item.getImageUrl() != null) {
            CacheManager cacheManager = new CacheManager();
            cacheManager.loadImageView(activity, item.getImageUrl(), feedImageView, true);
            //feedImageView.setImageUrl(item.getImageUrl(), imageLoader);
            feedImageView.setVisibility(View.VISIBLE);
        } else {
            feedImageView.setVisibility(View.GONE);
        }

        return convertView;
    }


}