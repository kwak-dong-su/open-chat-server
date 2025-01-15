package com.kds.openchatserver.api.exception

import com.kds.openchatserver.api.constants.messages.ErrorMessage

open class BaseException(private val error: ErrorMessage) : RuntimeException(error.code)

class AuthException(error: ErrorMessage) : BaseException(error)
class DuplicatedException(error: ErrorMessage) : BaseException(error)
class InvalidStateException(error: ErrorMessage) : BaseException(error)
class NotFoundException(error: ErrorMessage) : BaseException(error)