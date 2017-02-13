package com.chumbok.news.task;


import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.android.volley.Cache;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.chumbok.news.AppController;
import com.chumbok.news.Logger;
import com.chumbok.news.MainActivity;
import com.chumbok.news.entity.FeedItem;
import com.chumbok.news.util.DataPerser;
import com.chumbok.news.util.NetworkUtil;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.json.JSONArray;

import java.io.UnsupportedEncodingException;
import java.util.List;

@EBean(scope = EBean.Scope.Singleton)
public class InitDataBackgroundTask {

    private static final Logger logger = Logger.getLogger();
    @RootContext
    Context context;
    @RootContext
    MainActivity activity;
    private String URL_FEED = "http://www.chumbok.com/home.json";

    @AfterInject
    void initTask() {
        fetchSaveFeedItemInDB();
    }

    @Background(id = "initDataDownloadTask")
    protected void fetchSaveFeedItemInDB() {

        logger.d("initDataDownloadTask started.");

        try {

            if (!NetworkUtil.isNetworkAvailable()) {
                logger.d("Internet connection not available. Skipping Fetch Feed from Server.");
                return;
            }

            JsonArrayRequest jsonReq = new JsonArrayRequest(URL_FEED,
                    new Response.Listener<JSONArray>() {


                        @Override
                        public void onResponse(JSONArray response) {
                            if (response != null) {
                                logger.d("Got response of length - " + response.length());
                                List<FeedItem> feedItems = DataPerser.parseJsonFeed(response);
                                saveFeedItems(feedItems);
                            } else {
                                logger.e("Response is null.");
                            }
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    logger.e(error.getMessage());
                }
            });

            AppController.getInstance().addToRequestQueue(jsonReq);


        } catch (Exception e) {
            logger.e("Error - " + e);
        }

    }

    private void saveFeedItems(List<FeedItem> feedItems) {

        ActiveAndroid.beginTransaction();
        try {
            for (FeedItem item : feedItems) {

                FeedItem alreadyExistItem = new Select()
                        .from(FeedItem.class)
                        .where("articleId = ?", item.getArticleId())
                        .executeSingle();
                if (alreadyExistItem == null) {
                    logger.d("---------->>"+item.toString());
                    item.save();
                } else if (alreadyExistItem.getPubDate() != null && !alreadyExistItem.getPubDate().equals(item.getPubDate())) {
                    alreadyExistItem.copy(item);
                    alreadyExistItem.save();
                } else {
                    logger.d("articleId=" + alreadyExistItem.getArticleId() + " already exist.");
                }
            }

            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }


    private void readJsonFromCache() {

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED);
        entry = null;

        if (entry != null) {
            logger.d("Reading from cache.");
            try {
                String data = new String(entry.data, "UTF-8");

                //feedItems = parseJsonFeed(new JSONObject(data), feedItems);
                //listAdapter.notifyDataSetChanged();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
    }


    private void deleteAllFeedItemsFromDB() {
        new Delete().from(FeedItem.class).execute();
    }


}
