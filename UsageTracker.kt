
package com.primenet.vpn

import android.net.TrafficStats

object UsageTracker {

    fun getUploadedBytes(): Long {
        return TrafficStats.getTotalTxBytes()
    }

    fun getDownloadedBytes(): Long {
        return TrafficStats.getTotalRxBytes()
    }

    fun getUsage(): Pair<Long, Long> {
        return Pair(
            getUploadedBytes(),
            getDownloadedBytes()
        )
    }
}
