package com.superddaiupay.account_details

import java.io.Serializable
import java.util.*

class PaymentHistory : Serializable {
    var date: Date? = null
    var value: Number? = null
    var isOpen: Boolean = false
}