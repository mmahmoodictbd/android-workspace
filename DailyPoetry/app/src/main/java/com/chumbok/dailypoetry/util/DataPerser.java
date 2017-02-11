package com.chumbok.dailypoetry.util;

import com.chumbok.dailypoetry.Logger;
import com.chumbok.dailypoetry.entity.FeedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataPerser {

    private static final Logger logger = Logger.getLogger();

    public static List<FeedItem> parseJsonFeed(JSONObject obj) {

        List<FeedItem> feedItems = new ArrayList<FeedItem>();
        try {
            JSONArray feedArray = obj.getJSONArray("feed");

            for (int i = 0; i < feedArray.length(); i++) {

                JSONObject feedObj = (JSONObject) feedArray.get(i);

                FeedItem item = new FeedItem();
                item.setPoemId(feedObj.getInt("id"));
                item.setAuthorId(feedObj.getInt("authorId"));
                item.setAuthorName(!feedObj.isNull("authorName") ? feedObj.getString("authorName") : null);
                item.setTitle(!feedObj.isNull("title") ? feedObj.getString("title") : null);
                item.setDesc(!feedObj.isNull("desc") ? feedObj.getString("desc") : null);
                item.setProfilePicUrl(!feedObj.isNull("profilePicUrl") ? feedObj.getString("profilePicUrl") : null);
                item.setTimeStamp(!feedObj.isNull("timeStamp") ? feedObj.getString("timeStamp") : null);
                item.setLastUpdatedDate(!feedObj.isNull("lastUpdated") ? feedObj.getString("lastUpdated") : null);
                item.setImageUrl(feedObj.isNull("image") ? null : feedObj.getString("image"));
                item.setDescBodyUrl(feedObj.isNull("url") ? null : feedObj.getString("url"));

                feedItems.add(item);
            }

        } catch (JSONException e) {
            logger.d("Error occurred. " + e);
        }

        return feedItems;
    }
}
