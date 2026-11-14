
package com.primenet.vpn

import android.os.SystemClock
import java.net.InetSocketAddress
import java.net.Socket

object PingTester {

    fun test(host: String, port: Int = 443): Long {
        require(host.isNotBlank()) { "Server address is missing" }

        val socket = Socket()
        val start = SystemClock.elapsedRealtime()

        try {
            socket.connect(
                InetSocketAddress(host, port),
                3000
            )

            return SystemClock.elapsedRealtime() - start
        } finally {
            socket.close()
        }
    }
}
