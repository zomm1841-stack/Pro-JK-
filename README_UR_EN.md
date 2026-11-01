# QUOTEX AI PRO — Android starter (v0.1.0)

## What this starter includes
- Android app shell for Android 8.0+ (tested target: Android 12 / API 31)
- Dark black UI with Urdu/English text
- User-enabled floating overlay bubble and mini panel
- Safe placeholder state: **NO TRADE**
- No automated order placement

## Important limitations
This is a starter source project, **not a compiled APK**. It does not yet read Quotex charts,
capture the screen, calculate indicators, or produce validated predictions. The floating panel
currently displays a clear placeholder, not a live signal. No accuracy claim is made.

## Build
1. Install Android Studio on a computer.
2. Open this folder as an existing Gradle project.
3. Use JDK 17 and allow Gradle sync to download Android Gradle Plugin 8.6.1 / Kotlin 2.0.21.
4. Build > Build Bundle(s) / APK(s) > Build APK(s).
5. Transfer the generated APK to the Docomo F-51A and install it. Allow installation from that source if Android asks.
6. Open QUOTEX AI PRO, grant "Display over other apps", then tap Start floating AI panel.

## Next development stages
1. Add movable bubble drag support and close control.
2. Add user-initiated chart screenshot capture with Android MediaProjection consent.
3. Build and test candle/indicator extraction against sample charts.
4. Add backtested signal logic, confidence calibration, and a transparent accuracy log.
5. Test timing/latency on the actual F-51A before considering any near-candle-close alerts.

Trading involves substantial risk. Signals can be wrong; this app does not guarantee returns.
