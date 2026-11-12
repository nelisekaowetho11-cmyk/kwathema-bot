
package com.primenet.vpn

import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.*
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val navy = Color.rgb(4, 16, 39)
    private val gold = Color.rgb(255, 205, 112)
    private val cyan = Color.rgb(40, 195, 255)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLogin()
    }

    private fun showLogin() {
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(28, 30, 28, 30)
            background = GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                intArrayOf(Color.rgb(2, 8, 24), navy, Color.rgb(8, 35, 70))
            )
        }

        val crown = TextView(this).apply {
            text = "♛"
            textSize = 65f
            setTextColor(gold)
            gravity = Gravity.CENTER
        }

        val title = TextView(this).apply {
            text = "PRIME NET VPN"
            textSize = 27f
            setTextColor(gold)
            gravity = Gravity.CENTER
            setTypeface(null, Typeface.BOLD)
        }

        val subtitle = TextView(this).apply {
            text = "WELCOME BACK"
            textSize = 17f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val username = EditText(this).apply {
            hint = "Username"
            setSingleLine()
            setTextColor(Color.WHITE)
            setHintTextColor(Color.LTGRAY)
            setPadding(20, 15, 20, 15)
        }

        val password = EditText(this).apply {
            hint = "Password"
            inputType = 129
            setSingleLine()
            setTextColor(Color.WHITE)
            setHintTextColor(Color.LTGRAY)
            setPadding(20, 15, 20, 15)
        }

        val login = Button(this).apply {
            text = "LOGIN"
            textSize = 17f
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.rgb(20, 91, 180))
        }

        login.setOnClickListener {
            Toast.makeText(
                this,
                "Admin API connection is our next step.",
                Toast.LENGTH_LONG
            ).show()
        }

        layout.addView(crown)
        layout.addView(title)
        layout.addView(subtitle)

        layout.addView(username, fieldParams())
        layout.addView(password, fieldParams())
        layout.addView(login, fieldParams())

        setContentView(layout)
    }

    private fun fieldParams(): LinearLayout.LayoutParams {
        return LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            58
        ).apply {
            topMargin = 18
        }
    }
}
