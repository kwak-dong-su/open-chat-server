package com.kds.openchatserver.utils

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder

fun MessageSource.getMessage(message: String, vararg args: Any) =
    getMessage(message, args, LocaleContextHolder.getLocale())
