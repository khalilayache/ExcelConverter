package exceptions

import java.lang.Exception

class InvalidPlatformException(override val message: String = "Invalid platform") : Exception(message)
