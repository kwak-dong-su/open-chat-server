package com.kds.openchatserver.api.domain.vo

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatVO(
    @JsonProperty("userName") var userName: String,
    @JsonProperty("message") var message: String
)

data class TranslationVO(
    @JsonProperty("userName") var userName: String?,
    @JsonProperty("origin") var origin: String,
    @JsonProperty("korean") var korean: String,
    @JsonProperty("japanese") var japanese: String,
    @JsonProperty("chinese") var chinese: String,
    @JsonProperty("english") var english: String,
    @JsonProperty("spanish") var spanish: String
)