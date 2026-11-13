
package com.primenet.vpn

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.*
import android.graphics.drawable.GradientDrawable

class MainActivity : Activity() {

    private lateinit var root: LinearLayout

    private fun background(): GradientDrawable {
        return GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(
                Color.rgb(2, 8, 24),
                Color.rgb(4, 16, 39),
                Color.rgb(8, 35, 70)
            )
        )
    }

    private fun text(value: String, size: Float): TextView {
        return TextView(this).apply {
            text = value
            textSize = size
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            setPadding(12, 14, 12, 14)
        }
    }

    private fun button(value: String, action: () -> Unit): Button {
        return Button(this).apply {
            text = value
            setOnClickListener { action() }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLogin()
    }

    private fun setup() {
        root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(24, 24, 24, 24)
            background = background()
        }
        setContentView(root)
    }

    private fun showLogin() {
        setup()

        val title = text("♛ PRIME NET VPN", 28f).apply {
            setTextColor(Color.rgb(255, 205, 112))
            setTypeface(null, Typeface.BOLD)
        }

        val username = EditText(this).apply {
            hint = "Username"
            setSingleLine()
        }

        val password = EditText(this).apply {
            hint = "Password"
            inputType = 129
            setSingleLine()
        }

        root.addView(title)
        root.addView(text("WELCOME BACK", 18f))
        root.addView(username)
        root.addView(password)

        root.addView(button("LOGIN") {
            Toast.makeText(
                this,
                "Login API is not connected yet.",
                Toast.LENGTH_LONG
            ).show()
        })
    }

    private fun showHome() {
        setup()
        root.addView(text("♛ PRIME NET VPN", 28f))
        root.addView(text("HOME", 20f))
        root.addView(text("VPN: NOT CONNECTED", 17f))
        root.addView(button("CONNECT") {
            Toast.makeText(this, "VPN engine not connected yet.", Toast.LENGTH_SHORT).show()
        })
        root.addView(button("ACCOUNT") { showAccount() })
        root.addView(button("LOG OUT") { showLogin() })
    }

    private fun showAccount() {
        setup()
        root.addView(text("MY ACCOUNT", 24f))
        root.addView(text("Username: Not loaded", 16f))
        root.addView(text("Expiry: Not loaded", 16f))
        root.addView(text("Status: Not loaded", 16f))
        root.addView(text("Data used: Not measured", 16f))
        root.addView(text("Password: ••••••••", 16f))
        root.addView(button("PING TEST") {
            Toast.makeText(this, "Ping service not connected yet.", Toast.LENGTH_SHORT).show()
        })
        root.addView(button("BACK") { showHome() })
    }
}
