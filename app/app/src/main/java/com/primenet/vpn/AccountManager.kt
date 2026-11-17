
package com.primenet.vpn

data class AccountInfo(
    val accountId: String = "",
    val username: String,
    val expiryDate: String,
    val active: Boolean,
    val protocol: String = "",
    val configuration: String? = null,
    val uploadedBytes: Long = 0L,
    val downloadedBytes: Long = 0L
)

object AccountManager {

    var account: AccountInfo? = null
        private set

    fun setAccount(value: AccountInfo) {
        account = value
    }

    fun clear() {
        account = null
    }

    fun formatBytes(bytes: Long): String {
        if (bytes < 0) return "Unavailable"

        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        var size = bytes.toDouble()
        var index = 0

        while (size >= 1024 && index < units.size - 1) {
            size /= 1024
            index++
        }

        return "%.2f %s".format(size, units[index])
    }
}
