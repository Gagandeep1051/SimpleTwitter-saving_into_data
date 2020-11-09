package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {
    Context context;
    List<Tweet> tweets;

    //Pass in the context and list of tweets
    public TweetAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    // for each row , inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }
    //bind values based on position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the data at the position
        Tweet tweet = tweets.get(position);

        //Bind the tweet with the view holder
        holder.bind(tweet);
        holder.tvTime.setText(tweet.getFormattedTimeStamp(tweet.CreatedAt));



    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> tweetList) {
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }



    //define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivProfileImage;
        ImageView ivMedia;
        TextView tvBody;
        TextView tvscreenName;
        TextView tvTime;
        RelativeLayout container;





        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage=itemView.findViewById(R.id.ivProfileImage);
            tvBody=itemView.findViewById(R.id.tvBody);
            tvscreenName=itemView.findViewById(R.id.tvscreenName);
            tvTime = itemView.findViewById(R.id.tvTime);
            container = itemView.findViewById(R.id.container);
            ivMedia = itemView.findViewById(R.id.ivMedia);


        }

        public void bind(final Tweet tweet) {
            tvBody.setText(tweet.body);
            tvscreenName.setText(tweet.user.screeNname);
            boolean isPortrait = context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

            int placeholder = isPortrait ? R.drawable.ph : R.drawable.ph;
            int radius = 20; // corner radius, higher value = more rounded
            int margin = 0; // crop margin, set to 0 for corners with no crop
            Glide.with(context).load(tweet.user.profileimageurl).transform(new RoundedCornersTransformation(radius, margin)).placeholder(placeholder).error(placeholder).into(ivProfileImage);

            if (tweet.mediaUrl == null) {
                ivMedia.setVisibility(View.GONE);
            } else {
                ivMedia.setVisibility(View.VISIBLE);

                boolean isPortrait1 = context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

                int placeholder1 = isPortrait ? R.drawable.ph : R.drawable.ph;
                Glide.with(context).load(tweet.mediaUrl).transform(new RoundedCornersTransformation(20, 0)).centerCrop().placeholder(placeholder1).error(placeholder).into(ivMedia);

            }


            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   Intent i = new Intent(context,DetailActivity.class);
                   i.putExtra("body",tweet.body);
                   i.putExtra("screenName",tweet.user.screeNname);
                   i.putExtra("image",tweet.user.profileimageurl);
                   i.putExtra("time",tweet.getFormattedTimeStamp(tweet.CreatedAt));
                   i.putExtra("media",tweet.mediaUrl);

                   context.startActivity(i);
                   }
                });




        }
    }
}
