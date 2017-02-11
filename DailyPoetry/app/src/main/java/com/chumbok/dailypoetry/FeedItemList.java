package com.chumbok.dailypoetry;

import com.chumbok.dailypoetry.entity.FeedItem;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

@EBean(scope = EBean.Scope.Singleton)
public class FeedItemList {

    private static List<FeedItem> feedItemList;

    public List<FeedItem> getFeedItemList() {
        if (feedItemList == null) {
            feedItemList = new ArrayList<FeedItem>();
        }

        return feedItemList;
    }
}
