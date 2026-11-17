
package com.primenet.vpn

enum class VpnConnectionState {
    DISCONNECTED,
    CONNECTING,
    CONNECTED,
    FAILED
}

object VpnState {

    var state = VpnConnectionState.DISCONNECTED
        private set

    var message = "Not connected"
        private set

    var connectedSince: Long = 0L
        private set

    fun update(newState: VpnConnectionState, detail: String) {
        state = newState
        message = detail

        connectedSince = if (newState == VpnConnectionState.CONNECTED) {
            if (connectedSince == 0L) System.currentTimeMillis()
            else connectedSince
        } else {
            0L
        }
    }

    fun reset() {
        state = VpnConnectionState.DISCONNECTED
        message = "Not connected"
        connectedSince = 0L
    }
}
