package com.chumbok.dailypoetry.task;


import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.chumbok.dailypoetry.Logger;
import com.chumbok.dailypoetry.MainActivity;
import com.chumbok.dailypoetry.AppController;
import com.chumbok.dailypoetry.entity.FeedItem;
import com.chumbok.dailypoetry.util.DataPerser;
import com.chumbok.dailypoetry.util.NetworkUtil;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

@EBean(scope = EBean.Scope.Singleton)
public class InitDataBackgroundTask {

    private static final Logger logger = Logger.getLogger();
    private String URL_FEED = "http://192.168.0.105:8080/poetry/feed/";

    @RootContext
    Context context;

    @RootContext
    MainActivity activity;

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

            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED, null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
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
                        .where("poemId = ?", item.getPoemId())
                        .executeSingle();
                if (alreadyExistItem == null) {
                    item.save();
                } else if (!alreadyExistItem.getLastUpdatedDate().equals(item.getLastUpdatedDate())) {
                    alreadyExistItem.copy(item);
                    alreadyExistItem.save();
                } else {
                    logger.d("poemId=" + alreadyExistItem.getPoemId() + " already exist.");
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
