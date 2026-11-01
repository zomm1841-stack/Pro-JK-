package com.quotexaipro

import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView

class FloatingService : Service() {
    private lateinit var wm: WindowManager
    private var bubble: TextView? = null
    private var panel: LinearLayout? = null

    override fun onCreate() {
        super.onCreate()
        wm = getSystemService(WINDOW_SERVICE) as WindowManager
        showBubble()
    }

    private fun showBubble() {
        if (bubble != null) return
        bubble = TextView(this).apply {
            text = "AI"
            textSize = 16f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            setBackgroundColor(Color.rgb(18, 110, 75))
            setOnClickListener { togglePanel() }
        }
        val params = WindowManager.LayoutParams(
            58, 58,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.END
            x = 18; y = 220
        }
        wm.addView(bubble, params)
    }

    private fun togglePanel() {
        if (panel != null) {
            try { wm.removeView(panel) } catch (_: Exception) {}
            panel = null
            return
        }
        val box = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(18, 16, 18, 16)
            setBackgroundColor(Color.rgb(9, 13, 18))
        }
        fun line(s: String, size: Float, color: Int) = TextView(this).apply {
            text = s; textSize = size; setTextColor(color); setPadding(0, 6, 0, 6)
        }
        box.addView(line("QUOTEX AI PRO", 18f, Color.WHITE))
        box.addView(line("NEXT CANDLE", 11f, Color.LTGRAY))
        box.addView(line("NO TRADE", 26f, Color.rgb(234, 179, 8)))
        box.addView(line("Live analysis not connected", 12f, Color.LTGRAY))
        box.addView(line("Tap AI bubble to close", 11f, Color.GRAY))
        panel = box
        val p = WindowManager.LayoutParams(
            260, WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.END
            x = 18; y = 290
        }
        wm.addView(box, p)
    }

    override fun onDestroy() {
        bubble?.let { try { wm.removeView(it) } catch (_: Exception) {} }
        panel?.let { try { wm.removeView(it) } catch (_: Exception) {} }
        bubble = null; panel = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
