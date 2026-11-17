
package com.primenet.vpn

import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor

class PrimeVpnService : VpnService() {

    private var vpnInterface: ParcelFileDescriptor? = null

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {
        when (intent?.action) {
            ACTION_STOP -> stopVpn()
            ACTION_START -> startVpn()
        }

        return START_NOT_STICKY
    }

    private fun startVpn() {
        if (!VpnConfig.isReady()) {
            VpnState.update(
                VpnConnectionState.FAILED,
                "VPN configuration unavailable"
            )
            stopSelf()
            return
        }

        VpnState.update(
            VpnConnectionState.FAILED,
            "VPN engine integration pending"
        )
        stopSelf()
    }

    private fun stopVpn() {
        try {
            vpnInterface?.close()
        } catch (_: Exception) {
        }

        vpnInterface = null
        VpnState.reset()
        stopSelf()
    }

    override fun onDestroy() {
        try {
            vpnInterface?.close()
        } catch (_: Exception) {
        }

        vpnInterface = null
        super.onDestroy()
    }

    companion object {
        const val ACTION_START =
            "com.primenet.vpn.START"

        const val ACTION_STOP =
            "com.primenet.vpn.STOP"
    }
}
