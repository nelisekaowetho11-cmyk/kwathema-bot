
package com.primenet.vpn

data class AccountInfo(
    val username: String,
    val expiryDate: String,
    val active: Boolean,
    val uploadedBytes: Long,
    val downloadedBytes: Long
)

object AccountManager {

    var account: AccountInfo? = null

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
