package com.superddaiupay.payment_details

import java.io.Serializable
import java.util.*

class PaymentDetailsParams: Serializable {
    var beneficiaryName: String? = null
    var bankName: String? = null
    var payerName: String? = null
    var barCode: String? = null
    var value: Number? = null
    var currentBalance: Number? = null
    var payWithType: String? = null
    var dueDate: Date? = null
    var scheduledDueDate: Date? = null
    var baseColor: String? = null
    var type: PaymentDetailsType = PaymentDetailsType.PAYMENT
    var onClickBack: OnClickBack? = null
    var onConfirmPaymentSchedule: OnConfirmPaymentSchedule? = null

    enum class PaymentDetailsType {
        PAYMENT, SCHEDULE
    }

    interface OnClickBack {
        fun onClickBack()
    }

    interface OnConfirmPaymentSchedule {
        fun onConfirmPaymentSchedule()
    }
}