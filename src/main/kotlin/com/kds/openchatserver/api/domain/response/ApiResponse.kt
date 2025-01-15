package com.kds.openchatserver.api.domain.response

import org.springframework.data.domain.Page
import org.springframework.messaging.support.ErrorMessage

open class ApiResponse(val code: Int, val message: String)

class ApiSuccessResponse<T>(code: Int, message: String, val data: T) : ApiResponse(code, message)

class ApiErrorResponse(code: Int, message: String) : ApiResponse(code, message) {
    constructor(message: ErrorMessage) : this(0, message.toString())
}


class PageResponse<T>(page: Page<T>) {
    val list: List<T> = page.content
    val totalCount: Long = page.totalElements
    val totalPages: Int = page.totalPages
}