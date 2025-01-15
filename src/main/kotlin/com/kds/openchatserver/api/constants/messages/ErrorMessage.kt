package com.kds.openchatserver.api.constants.messages

enum class ErrorMessage(val code: String) {
    UNKNOWN("9999")
    ;

    fun resName() = "response.error.${name.lowercase().replace("_", ".")}.message"
}