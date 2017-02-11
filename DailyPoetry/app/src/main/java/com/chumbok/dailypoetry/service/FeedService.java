package com.chumbok.dailypoetry.service;


import com.activeandroid.query.Select;
import com.chumbok.dailypoetry.FeedItemList;
import com.chumbok.dailypoetry.Logger;
import com.chumbok.dailypoetry.adapter.FeedListAdapter;
import com.chumbok.dailypoetry.entity.FeedItem;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

@EBean(scope = EBean.Scope.Singleton)
public class FeedService {

    public static final Logger logger = Logger.getLogger();

    @Bean
    FeedItemList feedItemList;

    @Bean
    FeedListAdapter listAdapter;

    List<FeedItem> feedItems;

    @AfterInject
    void initAdapter() {
        feedItems = feedItemList.getFeedItemList();
    }

    public void displayFeedItemsFromDB(int offset, int numberOfItemToFetch) {

        List<FeedItem> items = new Select()
                .from(FeedItem.class).offset(offset)
                .limit(numberOfItemToFetch)
                .execute();

        for (FeedItem item : items) {
            logger.d("Fetched " + item.getPoemId());
        }

        logger.d("Fetched " + items.size() + " items from DB.");

        feedItems.addAll(items);
        listAdapter.notifyDataSetChanged();

    }


}
