package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tweet {

    public String body;
    public String CreatedAt;
    public User user;
    public long id;
    //private Entities entities;
    public String mediaUrl;
    public String video;
    public int bitrate;

    public static Tweet fromjson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.CreatedAt = jsonObject.getString("created_at");
        tweet.id = jsonObject.getLong("id");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));

        JSONObject entities = jsonObject.getJSONObject("entities");
        if (entities != null && entities.has("media")) {
            JSONArray medias = entities.getJSONArray("media");
            if (medias.length() > 0) {
                JSONObject media = (JSONObject) medias.get(0);
                if (media.has("media_url")) {
                    tweet.mediaUrl = media.getString("media_url");
                }
            }
        }
        /*JSONObject extended_entities = jsonObject.getJSONObject("extended_entities");
        if (extended_entities != null && extended_entities.has("media")) {
            JSONArray medias = extended_entities.getJSONArray("media");
            if (medias.length() > 0) {
                JSONObject media = (JSONObject) medias.get(0);
                if (media.has("variants")) {
                    JSONArray video = extended_entities.getJSONArray("variants");
                    if(video.length()>0){
                        JSONObject play = (JSONObject) video.get(0);
                        if(play.has("bitrate")){
                            tweet.bitrate = play.getInt("bitrate");
                            if(tweet.bitrate == 320000){
                                tweet.video = media.getString("url");
                            }

                        }

                    }

                }
            }
        }*/


        return tweet;

    }

    public static List<Tweet> fromJsonArrary(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            tweets.add(fromjson(jsonArray.getJSONObject(i)));

        }
        return tweets;
    }

    public String getFormattedTimeStamp(String CreatedAt) {

        return TimeFormatter.getTimeDifference(CreatedAt);

    }

}