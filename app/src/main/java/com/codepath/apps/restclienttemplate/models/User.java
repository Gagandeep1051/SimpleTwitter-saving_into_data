package com.codepath.apps.restclienttemplate.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
@Entity
public class User {

    @ColumnInfo
    @PrimaryKey
    public long id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public String screeName;

    @ColumnInfo
    public String profileimageurl;



    public User(){}// empty constructor needed by the Parceler library

    public static User fromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.id=jsonObject.getLong("id");
        user.name=jsonObject.getString("name");
        user.screeName=jsonObject.getString("screen_name");
        user.profileimageurl=jsonObject.getString("profile_image_url_https");
        return user;

    }

    public static List<User> fromJsonTweetArray(List<Tweet> tweetsfromNetwork) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < tweetsfromNetwork.size(); i++ ){
            users.add(tweetsfromNetwork.get(i).user);
        }
        return users;
    }
}
