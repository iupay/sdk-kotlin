package com.superddaiupay.account_details

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class PaymentHistory : Serializable {
    var date: Date? = null
    var value: Number? = null
    var isOpen: Boolean = false

    constructor()

    constructor(date: String, value: Number, isOpen: Boolean) {
        this.date = SimpleDateFormat("dd/MM/yyyy").parse(date)
        this.value = value
        this.isOpen = isOpen
    }

    constructor(date: String, value: Number) {
        this.date = SimpleDateFormat("dd/MM/yyyy").parse(date)
        this.value = value
        this.isOpen = false
    }
}
