package com.kds.openchatserver.api.service.webclient

import com.kds.openchatserver.api.domain.vo.TranslationVO

interface TranslationService {
    fun translate(text: String): TranslationVO
}