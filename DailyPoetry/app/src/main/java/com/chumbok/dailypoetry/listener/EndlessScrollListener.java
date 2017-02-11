package com.chumbok.dailypoetry.listener;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.chumbok.dailypoetry.FeedItemList;
import com.chumbok.dailypoetry.Logger;
import com.chumbok.dailypoetry.R;
import com.chumbok.dailypoetry.adapter.FeedListAdapter;
import com.chumbok.dailypoetry.entity.FeedItem;
import com.chumbok.dailypoetry.service.FeedService;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EBean(scope = EBean.Scope.Singleton)
public class EndlessScrollListener implements OnScrollListener {

    private static final Logger logger = Logger.getLogger();

    @Bean
    FeedService feedService;

    @Bean
    FeedListAdapter listAdapter;

    @Bean
    FeedItemList feedItemList;

    @ViewById(R.id.listView)
    ListView listView;

    List<FeedItem> feedItems;

    @AfterInject
    void initAdapter() {
        feedItems = feedItemList.getFeedItemList();
    }

    private int visibleThreshold = 5;
    private int currentPage = 1;
    private int previousTotal = 0;
    private boolean loading = true;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {


        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
                currentPage++;
                logger.d("*********************>>>>>???????/*???????/*/**/*/*/" + firstVisibleItem + "+++" + visibleItemCount + "+++" + totalItemCount);

            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            logger.d("***************|}");
            feedService.displayFeedItemsFromDB(currentPage * 5, 5);
            loading = true;
        }
    }

}
