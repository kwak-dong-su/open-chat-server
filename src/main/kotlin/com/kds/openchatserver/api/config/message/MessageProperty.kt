package com.kds.openchatserver.api.config.message

import dev.akkinoc.util.YamlResourceBundle
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.MessageSourceAccessor
import org.springframework.context.support.ResourceBundleMessageSource
import java.util.*

@Configuration
@ConfigurationProperties(prefix = "spring.messages")
class MessageProperty {
    var basename: String? = null
    var encoding: String? = null
    var cacheDuration: Int = 3
    var alwaysUseMessageFormat: Boolean = true
    var useCodeAsDefaultMessage: Boolean = true
    var fallbackToSystemLocale: Boolean = true

    @Bean
    fun messageSource(): MessageSource = YamlMessageSource().apply {
        basename?.apply { setBasename(this) }
        encoding?.apply { setDefaultEncoding(this) }
        setCacheSeconds(cacheDuration)
        setAlwaysUseMessageFormat(alwaysUseMessageFormat)
        setUseCodeAsDefaultMessage(useCodeAsDefaultMessage)
        setFallbackToSystemLocale(fallbackToSystemLocale)
    }

    @Bean
    fun messageSourceAccessor() = MessageSourceAccessor(messageSource())
}

private class YamlMessageSource : ResourceBundleMessageSource() {
    override fun doGetBundle(basename: String, locale: Locale): ResourceBundle =
        ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control)
}