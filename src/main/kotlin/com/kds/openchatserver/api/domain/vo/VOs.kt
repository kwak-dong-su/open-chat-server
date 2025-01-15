package com.kds.openchatserver.api.domain.vo

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatVO(
    @JsonProperty("userName") val userName: String,
    @JsonProperty("message") val message: String
)