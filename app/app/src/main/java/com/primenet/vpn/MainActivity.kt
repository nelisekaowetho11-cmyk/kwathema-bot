
package com.primenet.vpn

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.content.res.ColorStateList
import android.view.Gravity
import android.view.View
import android.widget.*

class MainActivity : Activity() {

    private lateinit var root: LinearLayout
    private lateinit var content: LinearLayout

    private val blue = Color.rgb(0, 125, 215)
    private val gold = Color.rgb(211, 171, 75)
    private val dark = Color.rgb(25, 35, 50)
    private val grey = Color.rgb(110, 120, 135)

    private fun dp(value: Int): Int =
        (value * resources.displayMetrics.density).toInt()

    private fun rounded(
        color: Int,
        radius: Int = 22,
        stroke: Int = Color.TRANSPARENT
    ): GradientDrawable {
        return GradientDrawable().apply {
            setColor(color)
            cornerRadius = dp(radius).toFloat()
            if (stroke != Color.TRANSPARENT) {
                setStroke(dp(1), stroke)
            }
        }
    }

    private fun label(
        value: String,
        size: Float,
        color: Int = dark,
        bold: Boolean = false
    ): TextView {
        return TextView(this).apply {
            text = value
            textSize = size
            setTextColor(color)
            gravity = Gravity.CENTER
            if (bold) setTypeface(null, Typeface.BOLD)
            setPadding(dp(8), dp(8), dp(8), dp(8))
        }
    }

    private fun makeButton(value: String, action: () -> Unit): Button {
        return Button(this).apply {
            text = value
            textSize = 16f
            setTextColor(Color.WHITE)
            backgroundTintList = ColorStateList.valueOf(blue)
            setOnClickListener { action() }
        }
    }

    private fun gap(height: Int) {
        content.addView(View(this), LinearLayout.LayoutParams(
            1, dp(height)
        ))
    }

    private fun card(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(18), dp(15), dp(18), dp(15))
            background = rounded(
                Color.WHITE,
                22,
                Color.rgb(230, 233, 238)
            )
        }
    }

    private fun addCard(view: View) {
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, dp(8), 0, dp(8))
        content.addView(view, params)
    }

    private fun setup() {
        root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.rgb(249, 250, 252))
        }

        val scroll = ScrollView(this)
        content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(22), dp(20), dp(22), dp(20))
        }

        scroll.addView(content)

        root.addView(scroll, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            0,
            1f
        ))

        setContentView(root)
        addNavigation()
    }

    private fun addLogo() {
        val image = ImageView(this).apply {
            setImageResource(R.drawable.prime_logo)
            scaleType = ImageView.ScaleType.FIT_CENTER
        }

        content.addView(image, LinearLayout.LayoutParams(
            dp(115), dp(115)
        ).apply {
            gravity = Gravity.CENTER
        })

        content.addView(label("PRIME VPN", 27f, gold, true))
    }

    private fun addNavigation() {
        val nav = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            background = rounded(Color.WHITE, 20)
            setPadding(dp(8), dp(8), dp(8), dp(8))
        }

        val home = label("⌂\nHome", 18f, blue, true)
        val account = label("●\nAccount", 18f, grey, true)

        home.setOnClickListener { showHome() }
        account.setOnClickListener { showAccount() }

        nav.addView(home, LinearLayout.LayoutParams(0, dp(75), 1f))
        nav.addView(account, LinearLayout.LayoutParams(0, dp(75), 1f))

        root.addView(nav, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dp(85)
        ).apply {
            setMargins(dp(18), dp(6), dp(18), dp(12))
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLogin()
    }

    private fun showLogin() {
        setup()
        addLogo()

        gap(18)
        content.addView(label("LOGIN", 25f, dark, true))
        content.addView(label("Sign in to continue", 16f, grey))

        val username = EditText(this).apply {
            hint = "Enter your Username"
            singleLine = true
            textSize = 16f
            background = rounded(Color.WHITE, 18,
                Color.rgb(220, 224, 230))
            setPadding(dp(16), dp(15), dp(16), dp(15))
        }

        val password = EditText(this).apply {
            hint = "Enter your password"
            inputType = 129
            singleLine = true
            textSize = 16f
            background = rounded(Color.WHITE, 18,
                Color.rgb(220, 224, 230))
            setPadding(dp(16), dp(15), dp(16), dp(15))
        }

        gap(16)
        addCard(username)
        addCard(password)

        gap(12)
        content.addView(makeButton("LOGIN") {
            if (username.text.isNullOrBlank() ||
                password.text.isNullOrBlank()) {
                Toast.makeText(
                    this,
                    "Enter your username and password.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Login API is not connected yet.",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        gap(12)
        content.addView(label("Forgot Password?", 15f, blue))
        content.addView(label(
            "Don't have an account?\nContact Admin",
            16f,
            blue
        ))
    }

    private fun showHome() {
        setup()
        addLogo()
        gap(20)

        val connected =
            VpnState.state == VpnConnectionState.CONNECTED

        val circle = TextView(this).apply {
            text = "P"
            textSize = 100f
            gravity = Gravity.CENTER
            setTextColor(blue)
            background = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(Color.WHITE)
                setStroke(dp(4), blue)
            }
        }

        content.addView(circle, LinearLayout.LayoutParams(
            dp(220), dp(220)
        ).apply {
            gravity = Gravity.CENTER
        })

        gap(12)

        content.addView(label(
            if (connected) "Connected" else "Tap to connect",
            27f,
            blue,
            true
        ))

        content.addView(label(
            if (connected) "VPN connection active"
            else "Not Connected",
            17f,
            dark
        ))

        gap(18)

        val config = card()
        config.addView(label("Active Configuration", 16f, grey))
        config.addView(label(
            "Not available yet",
            20f,
            dark,
            true
        ))
        addCard(config)

        val usage = card()
        val usageRow = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
        }

        usageRow.addView(label(
            "Uploaded\nUnavailable",
            18f,
            dark,
            true
        ), LinearLayout.LayoutParams(0, dp(90), 1f))

        usageRow.addView(label(
            "Downloaded\nUnavailable",
            18f,
            dark,
            true
        ), LinearLayout.LayoutParams(0, dp(90), 1f))

        usage.addView(usageRow)
        addCard(usage)

        gap(10)

        content.addView(makeButton(
            if (connected) "DISCONNECT" else "CONNECT"
        ) {
            Toast.makeText(
                this,
                "VPN engine is not connected yet. No tunnel was started.",
                Toast.LENGTH_LONG
            ).show()
        })
    }

    private fun showAccount() {
        setup()
        addLogo()
        gap(16)

        content.addView(label("MY ACCOUNT", 24f, dark, true))

        val account = AccountManager.account

        val details = card()

        details.addView(label(
            "Username",
            16f,
            grey
        ))

        details.addView(label(
            account?.username ?: "Not available",
            20f,
            dark,
            true
        ))

        details.addView(label("Password", 16f, grey))
        details.addView(label("••••••••", 20f, dark, true))

        details.addView(label("Expiry Date", 16f, grey))
        details.addView(label(
            account?.expiryDate ?: "Not available",
            19f,
            dark,
            true
        ))

        details.addView(label("Account Status", 16f, grey))
        details.addView(label(
            account?.let {
                if (it.active) "Active" else "Inactive"
            } ?: "Awaiting server data",
            19f,
            dark,
            true
        ))

        addCard(details)

        val usage = card()
        usage.addView(label("Data Usage", 19f, dark, true))
        usage.addView(label(
            "Uploaded: Unavailable",
            16f,
            dark
        ))
        usage.addView(label(
            "Downloaded: Unavailable",
            16f,
            dark
        ))
        addCard(usage)

        gap(10)

        content.addView(makeButton("PING TEST") {
            Toast.makeText(
                this,
                "Ping requires your assigned VPN server address.",
                Toast.LENGTH_LONG
            ).show()
        })

        gap(8)

        content.addView(makeButton("LOG OUT") {
            VpnConfig.clear()
            AccountManager.account = null
            VpnState.update(
                VpnConnectionState.DISCONNECTED,
                "Not connected"
            )
            showLogin()
        })
    }
}
