# The Deichlers — TV Screensavers

A custom **offline family-crest screensaver** with a live silver analog clock on cool charcoal. Designed for 16:9 televisions, including Samsung Tizen and compatible Google TV / Android TV devices.

## Preview / start here

- **[Open the live web version](web/index.html)** — open locally with the adjacent `web/assets/scene.webp` file, or serve `web/` from any static web server.
- **[Standalone browser edition](standalone/Deichlers_Live_Screensaver.html)** — one HTML file with its 4K crest image embedded; no web server or network required. Double-click or press **F** to toggle browser fullscreen.
- **[Samsung TV source](samsung-tizen/)** — Tizen Studio TV Web Application source; installable when signed/built for the specific TV.
- **[Android/Google TV source](android-tv/)** — Android Studio/Gradle source featuring a `DreamService` and a manually launched fullscreen fallback.

The 3840 × 2160 image `web/assets/scene.webp` is the **clock-free**, approved Deichlers crest composition. The live clock is drawn as SVG above it, showing the **device's local time**. The stand-alone version embeds exactly that image.

## Design and behavior

- Family crest on the left; silver Roman-numeral clock on the right
- Matte charcoal background — **no bronze glow**
- Real hour and minute hands updated once per second using the local device time
- Offline/no ads, analytics, accounts, network requests, or subscription
- Gentle 560-second position drift; respects `prefers-reduced-motion` in browsers
- `?preview=1` intentionally fixes the hands to 10:10 **for screenshots/tests only**

**Screen safety:** Slow motion is not a guarantee against image retention or burn-in. Keep TV manufacturers' automatic power-off/brightness and protection settings enabled.

## Platforms

### Web/browser
Open `standalone/Deichlers_Live_Screensaver.html`, or `web/index.html` with its `assets/` directory. A server is optional for the latter and not needed for the standalone file. Press **F** or double-click for fullscreen, Esc to leave fullscreen.

### Android TV / Google TV
Import `android-tv/` into Android Studio. Requires JDK 17, Android SDK 35, Android Gradle Plugin 8.7.3 and Gradle 8.9. Build with Gradle installed locally (`gradle assembleDebug`), or let Android Studio configure its Gradle runtime. A Gradle *wrapper properties file* is included; the Gradle wrapper JAR is not included.

Sideload `android-tv/app/build/outputs/apk/debug/app-debug.apk` with ADB. The TV launcher app works in fullscreen. On firmware that exposes the Android screensaver picker, select **Deichlers Family Clock**.

Some Google TV and operator-managed cable boxes lock or hide the custom screensaver picker. Optional advanced ADB commands (not guaranteed to stick):

```sh
adb shell settings put secure screensaver_components com.deichlers.screensaver/.FamilyClockDream
adb shell settings put secure screensaver_enabled 1
```

These settings can be blocked or reverted by device firmware. A successful APK install does **not** guarantee automatic idle activation.

### Samsung Smart TV / Tizen
Import `samsung-tizen/` as a Tizen TV Web Application in Tizen Studio (with the TV extensions), configure TV developer access and a Samsung certificate profile, build a signed `.wgt`, and install it on the TV.

Samsung generally does **not** allow third-party Tizen apps to replace the system's automatic idle screensaver. This app is designed to launch and show the live crest/clock fullscreen; it is **not** a system screensaver override. For a static display, compatible Samsung models may support photo/Ambient Mode features.

## Project layout

```text
web/                 Browser-ready app + shared 4K image
standalone/          Single-file offline HTML edition
android-tv/          Android TV launcher and DreamService source
samsung-tizen/       Tizen TV web-app source
docs/                Platform limitations and deployment notes
```

## Current build status

**Source and offline browser edition are ready.** Android and Tizen app projects have not yet been compiled, signed, or tested on physical TVs. Automatic activation depends on the actual Google/cable box firmware; Tizen launch is manual. Samsung TV model/year and Google TV box make/model are needed for exact installation steps.

The static 4K JPEG used for the original design preview is not required by the runtime; the original full-resolution composition is available in `web/assets/scene.webp` and the clock is rendered separately.

## Asset provenance

The crest artwork was provided by the repository owner and a derived charcoal/silver TV composition was created during the screensaver design discussion. The crest composition is a private-family artistic design; please do not use it as a real historical coat-of-arms certification. No external remote image hosts are used.
