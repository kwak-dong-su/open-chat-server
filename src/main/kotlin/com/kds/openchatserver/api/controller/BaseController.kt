package com.kds.openchatserver.api.controller

import com.kds.openchatserver.api.constants.messages.SuccessMessage
import com.kds.openchatserver.api.domain.response.ApiSuccessResponse
import com.kds.openchatserver.api.domain.response.PageResponse
import com.kds.openchatserver.utils.getMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class BaseController {

    @Autowired
    private lateinit var messageSource: MessageSource

    fun <T> wrap(data: T): ApiSuccessResponse<T> {
        var success = SuccessMessage.EMPTY
        if (data is PageResponse<*> && data.totalCount > 0) {
            success = SuccessMessage.RESULT
        } else if (data is List<*> && data.size > 0) {
            success = SuccessMessage.RESULT
        } else if (data != null) {
            success = SuccessMessage.RESULT
        }
        val message = messageSource.getMessage(success.resName())
        return ApiSuccessResponse(success.code.toInt(), message, data)
    }

    fun <T> page(data: Page<T>): ApiSuccessResponse<PageResponse<T>> = wrap(PageResponse(data))
}