package com.bojie.flickrbrowser;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by bojiejiang on 1/21/15.
 */

enum DownloadStatues {IDLE, PROCESSING, NOT_INITIALISED, FAILED_OR_EMPTY, OK}

public class GetRawData {

    private String TAG = GetRawData.class.getSimpleName();
    private String mRawUrl;
    private String mData;
    private DownloadStatues mDownloadStatues;

    public GetRawData(String rawUrl) {
        mRawUrl = rawUrl;
        this.mDownloadStatues = DownloadStatues.IDLE;
    }

    public void reset() {
        this.mDownloadStatues = DownloadStatues.IDLE;
        this.mData = null;
        this.mRawUrl = null;
    }

    public void setRawUrl(String rawUrl) {
        mRawUrl = rawUrl;
    }

    public String getData() {
        return mData;
    }

    public DownloadStatues getDownloadStatues() {
        return mDownloadStatues;
    }

    public void execute() {
        this.mDownloadStatues = DownloadStatues.PROCESSING;
        DownloadRawData downloadRawData = new DownloadRawData();
        downloadRawData.execute(mRawUrl);
    }

    public class DownloadRawData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            if(params == null) {
                return null;
            }

            try {

                URL url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                if(inputStream == null) {
                    return null;
                }

                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");

                }

                return buffer.toString();

            } catch (IOException e) {
                Log.e(TAG, "Error", e);
                return null;
            } finally {

                if(urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e(TAG, "Error closing stream", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String webData) {
            mData = webData;
            Log.v(TAG, "Data returned was: " + webData);
            if(mData == null) {
                if (mRawUrl == null) {
                    mDownloadStatues = DownloadStatues.NOT_INITIALISED;
                } else {
                    mDownloadStatues = DownloadStatues.FAILED_OR_EMPTY;
                }
            } else {
                // Success
                mDownloadStatues = DownloadStatues.OK;

            }
        }
    }
}
