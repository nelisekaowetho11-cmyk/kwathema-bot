
package com.primenet.vpn

object VpnConfig {
    var configuration: String? = null
        private set

    fun set(value: String) {
        require(value.isNotBlank()) {
            "VPN configuration is empty"
        }
        configuration = value
    }

    fun clear() {
        configuration = null
    }

    fun isReady(): Boolean {
        return !configuration.isNullOrBlank()
    }
}
