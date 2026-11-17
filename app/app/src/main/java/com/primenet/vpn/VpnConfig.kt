
package com.primenet.vpn

object VpnConfig {

    var configuration: String? = null
        private set

    var protocol: String = ""
        private set

    var serverAddress: String = ""
        private set

    var serverPort: Int = 0
        private set

    fun set(value: String) {
        require(value.isNotBlank()) {
            "VPN configuration is empty"
        }
        configuration = value
    }

    fun setAccountConfig(
        value: String,
        protocolName: String,
        server: String,
        port: Int
    ) {
        require(value.isNotBlank()) { "Configuration is empty" }
        require(protocolName.equals("VLESS", true) ||
                protocolName.equals("TROJAN", true)) {
            "Unsupported VPN protocol"
        }
        require(server.isNotBlank()) { "Server address is empty" }
        require(port in 1..65535) { "Invalid server port" }

        configuration = value
        protocol = protocolName.uppercase()
        serverAddress = server
        serverPort = port
    }

    fun clear() {
        configuration = null
        protocol = ""
        serverAddress = ""
        serverPort = 0
    }

    fun isReady(): Boolean {
        return !configuration.isNullOrBlank()
    }
}
