package app.weatheapp.config;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

/**
 * Created by devkkda on 7/22/2017.
 */

public class volleyRequestQueue {

    private static volleyRequestQueue mInstance;
    private static Context mCtx;
    private RequestQueue mRequestQueue;

    private volleyRequestQueue(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized volleyRequestQueue getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new volleyRequestQueue(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            Cache cache = new DiskBasedCache(mCtx.getCacheDir(), 10 * 1024 * 1024);
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache, network);
            // Don't forget to start the volley request queue
            mRequestQueue.start();
        }
        return mRequestQueue;
    }
}