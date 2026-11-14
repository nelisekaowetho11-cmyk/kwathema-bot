
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

    fun update(newState: VpnConnectionState, detail: String) {
        state = newState
        message = detail
    }
}
