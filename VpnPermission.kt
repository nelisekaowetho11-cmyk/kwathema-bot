
package com.primenet.vpn

import android.app.Activity
import android.net.VpnService

object VpnPermission {

    const val REQUEST_CODE = 1001

    fun request(activity: Activity) {
        val intent = VpnService.prepare(activity)

        if (intent != null) {
            activity.startActivityForResult(
                intent,
                REQUEST_CODE
            )
        }
    }
}
