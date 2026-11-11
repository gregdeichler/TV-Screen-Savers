package com.deichlers.screensaver;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

/** Offline renderer shared by the launcher activity and DreamService. */
final class ClockWebView extends WebView {
    ClockWebView(Context context) {
        super(context);
        setBackgroundColor(Color.rgb(16, 17, 19));
        setFocusable(false);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setAllowFileAccess(true);
        getSettings().setAllowContentAccess(false);
        getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        getSettings().setDomStorageEnabled(false);
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            setImportantForAutofill(View.IMPORTANT_FOR_AUTOFILL_NO);
        }
        loadUrl("file:///android_asset/index.html");
    }
}
