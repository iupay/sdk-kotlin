package com.superddaiupay.account_details

import java.io.Serializable
import java.util.*

class BillDetails : Serializable {
    var barCode: String? = null
    var billDate: String? = null
    var dueDate: Date? = null
    var emissionDate: Date? = null
    var interestInstallmentFine: Number? = null
    var interestInstallmentRate: Number? = null
    var interestInstallmentRateCET: Number? = null
    var interestRate: Number? = null
    var interestRateCET: Number? = null
    var minimumPaymentValue: Number? = null
    var totalLimitValue: Number? = null
    var totalWithdrawLimitValue: Number? = null
    var value: Number? = null
}