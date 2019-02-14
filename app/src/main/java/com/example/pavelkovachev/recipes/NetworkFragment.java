package com.example.pavelkovachev.recipes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkFragment extends Fragment {
    private static final String TAG = "NetworkFragment";
    private static final String URL_KEY = "UrlKey";

    private DownloadCallback downloadCallback;
    private DownloadTask downloadTask;
    private String urlString;

    public static NetworkFragment getInstance(FragmentManager fragmentManager, String url) {
        NetworkFragment networkFragment = (NetworkFragment) fragmentManager.findFragmentByTag(NetworkFragment.TAG);
        if (networkFragment == null) {
            networkFragment = new NetworkFragment();
            Bundle args = new Bundle();
            args.putString(URL_KEY, url);
            networkFragment.setArguments(args);
            fragmentManager.beginTransaction().add(networkFragment, TAG).commit();
        }
        return networkFragment;
    }

    public void startDownload() {
        cancelDownload();
        downloadTask = new DownloadTask();
        downloadTask.execute(urlString);
    }

    public void cancelDownload() {
        if (downloadTask != null) {
            downloadTask.cancel(true);
            downloadTask = null;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        urlString = getArguments().getString(URL_KEY);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        downloadCallback = (DownloadCallback) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        downloadCallback = null;
    }

    @Override
    public void onDestroy() {
        cancelDownload();
        super.onDestroy();
    }

    private class DownloadTask extends AsyncTask<String, Integer, DownloadTask.Result> {

        class Result {
            public String resultValue;
            public Exception exception;

            public Result(String resultValue) {
                this.resultValue = resultValue;
            }

            public Result(Exception exception) {
                this.exception = exception;
            }
        }

        @Override
        protected void onPreExecute() {
            if (downloadCallback != null) {
                NetworkInfo networkInfo = downloadCallback.getActiveNetworkInfo();
                if (networkInfo == null || !networkInfo.isConnected() ||
                        (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                                && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
                    downloadCallback.updateFromDownload(null);
                    cancel(true);
                }
            }
        }

        @Override
        protected Result doInBackground(String... urls) {
            Result result = null;
            if (!isCancelled() && urls != null && urls.length > 0) {
                String urlString = urls[0];
                try {
                    URL url = new URL(urlString);
                    String resultString = downloadUrl(url);
                    if (resultString != null) {
                        result = new Result(resultString);
                    } else {
                        throw new IOException("No response received");
                    }
                } catch (Exception e) {
                    result = new Result(e);
                    e.printStackTrace();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(Result result) {
            if (result != null && downloadCallback != null) {
                if (result.exception != null) {
                    downloadCallback.updateFromDownload(result.exception.getMessage());
                } else if (result.resultValue != null) {
                    downloadCallback.updateFromDownload(result.resultValue);
                }
                downloadCallback.finishDownloading();
            }
        }
    }

    private String downloadUrl(URL url) throws IOException {
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            stream = connection.getInputStream();
            if (stream != null) {
                result = readStream(stream, 500);
            }
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    private String readStream(InputStream stream, int maxLength) throws IOException {
        String result = null;
        InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[maxLength];
        int numChars = 0;
        int readSize = 0;
        while (numChars < maxLength && readSize != -1) {
            numChars += readSize;
            int pct = (100 * numChars) / maxLength;
            readSize = reader.read(buffer, numChars, buffer.length - numChars);
        }
        if (numChars != -1) {
            numChars = Math.min(numChars, maxLength);
            result = new String(buffer, 0, numChars);
        }
        return result;
    }
}