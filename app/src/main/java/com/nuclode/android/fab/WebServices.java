package com.nuclode.android.fab;

public class WebServices {
    private static final WebServices ourInstance = new WebServices();

    public static WebServices getInstance() {
        return ourInstance;
    }

    private WebServices() {
    }
}
