package com.deichlers.screensaver;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/** Manually launchable fullscreen fallback for firmware that blocks third-party dreams. */
public final class MainActivity extends Activity {
    private ClockWebView clock;

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        clock = new ClockWebView(this);
        setContentView(clock);
    }
    @Override protected void onPause() { super.onPause(); if (clock != null) clock.onPause(); }
    @Override protected void onResume() { super.onResume(); if (clock != null) clock.onResume(); }
    @Override protected void onDestroy() { if (clock != null) { clock.destroy(); clock = null; } super.onDestroy(); }
}
