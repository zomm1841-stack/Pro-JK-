package com.quotexaipro

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.graphics.Color

class MainActivity : Activity() {
    private val bg = Color.rgb(9, 13, 18)
    private val panel = Color.rgb(21, 28, 36)
    private val white = Color.WHITE
    private val muted = Color.rgb(165, 175, 188)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(28, 42, 28, 28)
            setBackgroundColor(bg)
        }
        fun label(value: String, size: Float, color: Int, bold: Boolean = false) =
            TextView(this).apply {
                text = value
                textSize = size
                setTextColor(color)
                if (bold) setTypeface(null, android.graphics.Typeface.BOLD)
                setPadding(0, 10, 0, 10)
            }

        root.addView(label("QUOTEX AI PRO", 26f, white, true))
        root.addView(label("ADVANCED MARKET INTELLIGENCE", 11f, muted, true))
        val card = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 18, 20, 18)
            setBackgroundColor(panel)
        }
        card.addView(label("AI MARKET ANALYZER", 17f, white, true))
        card.addView(label("NEXT CANDLE DECISION", 11f, muted, true))
        card.addView(label("NO TRADE", 30f, Color.rgb(234, 179, 8), true))
        card.addView(label("Live chart analysis abhi connect nahi hai.\nLive signal generate nahi kiya ja raha.", 13f, muted))
        root.addView(card)
        root.addView(label("Features in this first build", 17f, white, true))
        root.addView(label("• Urdu + English interface\n• Dark trading-style design\n• Floating mini-panel over other apps\n• Manual user-controlled overlay permission\n• No automatic trades", 14f, muted))
        root.addView(label("اہم: یہ ابتدائی انٹرفیس ہے، لائیو ٹریڈنگ سگنل یا منافع کی ضمانت نہیں۔", 13f, white))

        val permissionButton = Button(this).apply {
            text = "Enable floating window permission"
            setOnClickListener {
                if (!Settings.canDrawOverlays(this@MainActivity)) {
                    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:$packageName"))
                    startActivity(intent)
                } else {
                    startFloatingService()
                }
            }
        }
        root.addView(permissionButton)
        val startButton = Button(this).apply {
            text = "Start floating AI panel"
            setOnClickListener {
                if (Settings.canDrawOverlays(this@MainActivity)) startFloatingService()
                else {
                    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:$packageName"))
                    startActivity(intent)
                }
            }
        }
        root.addView(startButton)
        setContentView(root)
    }

    private fun startFloatingService() {
        startService(Intent(this, FloatingService::class.java))
    }
}
