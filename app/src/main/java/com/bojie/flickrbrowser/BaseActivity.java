package com.bojie.flickrbrowser;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by bojiejiang on 1/30/15.
 */
public class BaseActivity extends ActionBarActivity{

    private Toolbar mToolbar;
    public static final String FLICKR_QUERY = "FLICKR_QUERY";

    protected Toolbar activateToolBar() {
        if(mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.app_bar);
            if (mToolbar != null) {
                setSupportActionBar(mToolbar);
            }
        }
        return mToolbar;

    }

    protected Toolbar activeToolbarWithHomeEnabled(){
        activateToolBar();
        if(mToolbar != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
        return mToolbar;
    }

}
