package com.superddaiupay.account_details

import java.io.Serializable

class ChartData : Serializable {
    var label: String = ""
    var value: Number = 0

    constructor(label: String, value: Number) {
        this.label = label
        this.value = value
    }
}