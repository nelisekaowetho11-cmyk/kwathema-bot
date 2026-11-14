
package com.primenet.vpn

import android.app.Activity
import android.os.Bundle
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.content.res.ColorStateList
import android.text.InputType
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
        radius: Int = 20
    ): GradientDrawable {
        return GradientDrawable().apply {
            setColor(color)
            cornerRadius = dp(radius).toFloat()
            setStroke(dp(1), Color.rgb(230, 233, 238))
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

    private fun makeButton(
        value: String,
        action: () -> Unit
    ): Button {
        return Button(this).apply {
            text = value
            textSize = 16f
            setTextColor(Color.WHITE)
            backgroundTintList = ColorStateList.valueOf(blue)
            setOnClickListener {
                runSafely(action)
            }
        }
    }

    private fun runSafely(action: () -> Unit) {
        try {
            action()
        } catch (error: Throwable) {
            showError(error)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runSafely {
            showLogin()
        }
    }

    private fun showError(error: Throwable) {
        try {
            val message = TextView(this).apply {
                text = "PRIME NET STARTUP ERROR\n\n" +
                    error.javaClass.simpleName +
                    "\n" + (error.message ?: "No error details")
                textSize = 16f
                setTextColor(Color.RED)
                setPadding(dp(20), dp(20), dp(20), dp(20))
            }

            setContentView(ScrollView(this).apply {
                addView(message)
            })
        } catch (_: Throwable) {
            // Avoid another crash while displaying an error.
        }
    }

    private fun setup(showNavigation: Boolean = false) {
        root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.rgb(249, 250, 252))
        }

        val scroll = ScrollView(this)

        content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(20), dp(18), dp(20), dp(18))
        }

        scroll.addView(content)

        root.addView(
            scroll,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        setContentView(root)

        if (showNavigation) {
            addNavigation()
        }
    }

    private fun gap(height: Int) {
        content.addView(
            View(this),
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(height)
            )
        )
    }

    private fun card(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(16), dp(14), dp(16), dp(14))
            background = rounded(Color.WHITE)
        }
    }

    private fun addCard(view: View) {
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, dp(7), 0, dp(7))
        content.addView(view, params)
    }

    private fun addLogo() {
        try {
            val resourceId = resources.getIdentifier(
                "prime_logo",
                "drawable",
                packageName
            )

            if (resourceId != 0) {
                val bounds = BitmapFactory.Options().apply {
                    inJustDecodeBounds = true
                }

                BitmapFactory.decodeResource(
                    resources,
                    resourceId,
                    bounds
                )

                val target = dp(180).coerceAtLeast(180)
                var sample = 1

                while (
                    bounds.outWidth / sample > target * 2 ||
                    bounds.outHeight / sample > target * 2
                ) {
                    sample *= 2
                }

                val options = BitmapFactory.Options().apply {
                    inSampleSize = sample
                }

                val bitmap = BitmapFactory.decodeResource(
                    resources,
                    resourceId,
                    options
                )

                if (bitmap != null) {
                    val image = ImageView(this).apply {
                        setImageBitmap(bitmap)
                        scaleType = ImageView.ScaleType.FIT_CENTER
                    }

                    content.addView(
                        image,
                        LinearLayout.LayoutParams(
                            dp(130),
                            dp(120)
                        ).apply {
                            gravity = Gravity.CENTER
                        }
                    )
                } else {
                    content.addView(
                        label("PRIME NET VPN", 25f, gold, true)
                    )
                }
            } else {
                content.addView(
                    label("PRIME NET VPN", 25f, gold, true)
                )
            }
        } catch (_: Throwable) {
            content.addView(
                label("PRIME NET VPN", 25f, gold, true)
            )
        }

        content.addView(label("PRIME VPN", 22f, gold, true))
    }

    private fun addNavigation() {
        val nav = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            background = rounded(Color.WHITE)
            setPadding(dp(6), dp(5), dp(6), dp(5))
        }

        val home = label("⌂\nHome", 17f, blue, true)
        val account = label("●\nAccount", 17f, grey, true)

        home.setOnClickListener {
            runSafely { showHome() }
        }

        account.setOnClickListener {
            runSafely { showAccount() }
        }

        nav.addView(
            home,
            LinearLayout.LayoutParams(0, dp(65), 1f)
        )

        nav.addView(
            account,
            LinearLayout.LayoutParams(0, dp(65), 1f)
        )

        root.addView(
            nav,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(78)
            ).apply {
                setMargins(dp(16), dp(5), dp(16), dp(10))
            }
        )
    }

    private fun showLogin() {
        setup()
        addLogo()

        gap(15)
        content.addView(label("LOGIN", 25f, dark, true))
        content.addView(
            label("Sign in to continue", 16f, grey)
        )

        val username = EditText(this).apply {
            hint = "Enter your Username"
            singleLine = true
            textSize = 16f
            background = rounded(Color.WHITE)
            setPadding(dp(15), dp(14), dp(15), dp(14))
        }

        val password = EditText(this).apply {
            hint = "Enter your password"
            singleLine = true
            inputType = InputType.TYPE_CLASS_TEXT or
                InputType.TYPE_TEXT_VARIATION_PASSWORD
            textSize = 16f
            background = rounded(Color.WHITE)
            setPadding(dp(15), dp(14), dp(15), dp(14))
        }

        gap(12)
        addCard(username)
        addCard(password)

        gap(12)

        content.addView(makeButton("LOGIN") {
            if (
                username.text.isNullOrBlank() ||
                password.text.isNullOrBlank()
            ) {
                Toast.makeText(
                    this,
                    "Enter your username and password.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Admin login API is not connected yet.",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        gap(10)

        content.addView(
            label("Forgot Password?", 15f, blue)
        )

        content.addView(
            label(
                "Don't have an account?\nContact Admin",
                16f,
                blue
            )
        )
    }

    private fun showHome() {
        setup(true)
        addLogo()
        gap(15)

        val connected =
            VpnState.state == VpnConnectionState.CONNECTED

        val circle = TextView(this).apply {
            text = "P"
            textSize = 90f
            gravity = Gravity.CENTER
            setTextColor(blue)
            background = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(Color.WHITE)
                setStroke(dp(4), blue)
            }
        }

        content.addView(
            circle,
            LinearLayout.LayoutParams(
                dp(210),
                dp(210)
            ).apply {
                gravity = Gravity.CENTER
            }
        )

        gap(10)

        content.addView(
            label(
                if (connected) "Connected" else "Not Connected",
                25f,
                blue,
                true
            )
        )

        content.addView(
            label(VpnState.message, 16f, dark)
        )

        gap(15)

        val config = card()
        config.addView(
            label("Active Configuration", 16f, grey)
        )
        config.addView(
            label("Unavailable", 19f, dark, true)
        )
        addCard(config)

        val usage = card()
        usage.addView(
            label("Data Usage", 18f, dark, true)
        )
        usage.addView(
            label("Uploaded: Unavailable", 16f)
        )
        usage.addView(
            label("Downloaded: Unavailable", 16f)
        )
        addCard(usage)

        gap(10)

        content.addView(
            makeButton(
                if (connected) "DISCONNECT" else "CONNECT"
            ) {
                Toast.makeText(
                    this,
                    "VPN engine is not implemented yet.",
                    Toast.LENGTH_LONG
                ).show()
            }
        )
    }

    private fun showAccount() {
        setup(true)
        addLogo()
        gap(10)

        content.addView(
            label("MY ACCOUNT", 24f, dark, true)
        )

        val account = AccountManager.account
        val details = card()

        details.addView(label("Username", 16f, grey))
        details.addView(
            label(account?.username ?: "Not available", 19f, dark, true)
        )

        details.addView(label("Password", 16f, grey))
        details.addView(label("••••••••", 19f, dark, true))

        details.addView(label("Expiry Date", 16f, grey))
        details.addView(
            label(account?.expiryDate ?: "Not available", 18f, dark, true)
        )

        details.addView(label("Account Status", 16f, grey))
        details.addView(
            label(
                account?.let {
                    if (it.active) "Active" else "Inactive"
                } ?: "Awaiting server data",
                18f,
                dark,
                true
            )
        )

        addCard(details)

        val usage = card()
        usage.addView(label("Data Usage", 18f, dark, true))
        usage.addView(label("Uploaded: Unavailable", 16f))
        usage.addView(label("Downloaded: Unavailable", 16f))
        addCard(usage)

        gap(10)

        content.addView(makeButton("PING TEST") {
            Toast.makeText(
                this,
                "Ping needs the assigned server address.",
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
