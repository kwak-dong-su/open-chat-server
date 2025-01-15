package com.kds.openchatserver.api.constants.messages

enum class SuccessMessage(val code: String) {
    RESULT("0"),
    EMPTY("1"),
    ;

    fun resName() = "response.success.${name.lowercase().replace("_", ".")}.message"
}