package com.felipeschoffen.onbudget.domain.auth

import java.security.MessageDigest

class PinHashing {
    fun hashPin(pin: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(pin.toByteArray())
        return hashBytes.joinToString("") { "%202x".format(it) }
    }
}