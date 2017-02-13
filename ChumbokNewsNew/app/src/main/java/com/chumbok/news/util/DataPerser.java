package com.chumbok.news.util;

import com.chumbok.news.Logger;
import com.chumbok.news.entity.FeedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataPerser {

    private static final Logger logger = Logger.getLogger();

    public static List<FeedItem> parseJsonFeed(JSONArray feedArray) {

        List<FeedItem> feedItems = new ArrayList<FeedItem>();
        try {
            for (int i = 0; i < feedArray.length(); i++) {

                JSONObject feedObj = (JSONObject) feedArray.get(i);

                FeedItem item = new FeedItem();
                item.setArticleId(feedObj.getInt("articleId"));
                item.setSourceName(StringUtil.getUnicodeString(feedObj.getString("sourceName")));
                item.setTitle(!feedObj.isNull("articleHeadline") ? StringUtil.getUnicodeString(feedObj.getString("articleHeadline")) : null);
                item.setDesc(!feedObj.isNull("articleBody") ? StringUtil.getUnicodeString(feedObj.getString("articleBody")) : null);
                //item.setProfilePicUrl(!feedObj.isNull("faviconUrl") ? feedObj.getString("faviconUrl") : null);
                item.setImageUrl(feedObj.isNull("imgSrc") ? null : feedObj.getString("imgSrc"));
                item.setDescBodyUrl(feedObj.isNull("articleUrl") ? null : StringUtil.getUnicodeString(feedObj.getString("articleUrl")));
                feedItems.add(item);
            }

        } catch (JSONException e) {
            logger.d("Error occurred. " + e);
        }

        return feedItems;
    }
}
