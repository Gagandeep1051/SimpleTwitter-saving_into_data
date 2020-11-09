package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    public String name;
    public String screeNname;
    public String profileimageurl;



    public static User fromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.name=jsonObject.getString("name");
        user.screeNname=jsonObject.getString("screen_name");
        user.profileimageurl=jsonObject.getString("profile_image_url_https");
        return user;

    }

}
