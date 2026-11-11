# Platform behavior and deployment

The user requested screensavers on a Samsung television and a Google/Android cable box. These are not equivalent operating systems.

## Google TV / Android TV

The app includes two entry points:

1. `MainActivity` (manually opened from the leanback launcher) runs the exact same offline HTML renderer in a WebView.
2. `FamilyClockDream` is a system-declared `android.service.dreams.DreamService`. The OS will invoke it **only if** the firmware allows selecting third-party dreams.

An ISP/operator-managed Android TV box may not allow sideloading, ADB, or switching its ambient display, even when the app itself can launch.

## Samsung Tizen

Tizen's TV Web Application model allows a self-contained fullscreen app, but the platform generally does not expose a third-party override for the TV's protected/inactivity screensaver. Build a signed .wgt for the actual model, or use the television's Ambient Mode/photo mode for a still where supported. A photo is not a live analog clock.

## Testing checklist

- Verify crest placement/clock positioning at 16:9 in a real browser.
- Verify the clock follows local time and the device time zone is correct.
- Check the `?preview=1` mode is only used for static captures.
- Android: build with Android Studio, sideload on target hardware, check launcher; test picker availability separately.
- Samsung: sign and run on the physical TV; test remote Back handling and sleep/power behavior.
- Keep TV screen protection and automatic power-off enabled.
