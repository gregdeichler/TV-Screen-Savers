package com.deichlers.screensaver;

import android.service.dreams.DreamService;

/** System screensaver entry point where TV firmware supports third-party dreams. */
public final class FamilyClockDream extends DreamService {
    private ClockWebView clock;

    @Override public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setInteractive(false);
        setFullscreen(true);
        clock = new ClockWebView(this);
        setContentView(clock);
    }
    @Override public void onDreamingStarted() {
        super.onDreamingStarted();
        if (clock != null) clock.onResume();
    }
    @Override public void onDreamingStopped() {
        if (clock != null) clock.onPause();
        super.onDreamingStopped();
    }
    @Override public void onDetachedFromWindow() {
        if (clock != null) {
            clock.destroy();
            clock = null;
        }
        super.onDetachedFromWindow();
    }
}
