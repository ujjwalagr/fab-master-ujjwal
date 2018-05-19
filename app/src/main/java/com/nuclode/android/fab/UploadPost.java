package com.nuclode.android.fab;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class UploadPost {
    private static  Context mctx;

    private RequestQueue requestQueue;
    private static UploadPost mInstance;
    private UploadPost(Context context) {
        mctx=context;
        requestQueue=getRequestQueue();
    }
    private RequestQueue getRequestQueue()
    {
        if(requestQueue==null)
        {
            requestQueue= Volley.newRequestQueue(mctx.getApplicationContext());}
            return  requestQueue;
        //return mInstance;
    }
    public static synchronized UploadPost getInstance(Context context)

    {
        if(mInstance==null)
        {
            mInstance=new UploadPost(context);

        }
        return mInstance;
    }
    public <T> void addToRequestQue(Request<T> request)
    {
        getRequestQueue().add(request);
    }
}
