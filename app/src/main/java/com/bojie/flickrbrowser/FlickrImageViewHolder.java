package com.bojie.flickrbrowser;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by bojiejiang on 1/24/15.
 */
public class FlickrImageViewHolder extends RecyclerView.ViewHolder {

    protected ImageView thumbnail;
    protected TextView tv_title;

    public FlickrImageViewHolder(View itemView) {
        super(itemView);
        this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
    }
}
