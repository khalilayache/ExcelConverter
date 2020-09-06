package exceptions

import java.lang.Exception

class AccountNotFound (override val message: String = "Account not found") : Exception(message)
