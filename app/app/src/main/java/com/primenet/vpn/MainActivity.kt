
package com.primenet.vpn

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.*
import android.graphics.drawable.GradientDrawable

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER
                setPadding(28, 30, 28, 30)
                background = GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    intArrayOf(
                        Color.rgb(2, 8, 24),
                        Color.rgb(4, 16, 39),
                        Color.rgb(8, 35, 70)
                    )
                )
            }

            val title = TextView(this).apply {
                text = "♛ PRIME NET VPN"
                textSize = 28f
                gravity = Gravity.CENTER
                setTextColor(Color.rgb(255, 205, 112))
                setTypeface(null, Typeface.BOLD)
            }

            val subtitle = TextView(this).apply {
                text = "WELCOME BACK"
                textSize = 18f
                gravity = Gravity.CENTER
                setTextColor(Color.WHITE)
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

            val login = Button(this).apply {
                text = "LOGIN"
                setOnClickListener {
                    Toast.makeText(
                        this@MainActivity,
                        "Login service is not connected yet.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            layout.addView(title)
            layout.addView(subtitle)
            layout.addView(username)
            layout.addView(password)
            layout.addView(login)

            setContentView(layout)

        } catch (e: Exception) {
            val error = TextView(this).apply {
                text = "Startup error: ${e.javaClass.simpleName}\n${e.message}"
                setTextColor(Color.RED)
                textSize = 16f
                setPadding(20, 20, 20, 20)
            }
            setContentView(error)
        }
    }
}
