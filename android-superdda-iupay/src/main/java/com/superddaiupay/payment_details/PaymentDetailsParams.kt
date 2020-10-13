package com.superddaiupay.payment_details

import java.io.Serializable
import java.util.*

class PaymentDetailsParams : Serializable {
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

    companion object {
        fun example(): PaymentDetailsParams {
            val params = PaymentDetailsParams()

            params.beneficiaryName = "COMPANHIA DE ELETRICIDADE DO RIO DE JANEIRO"
            params.bankName = "ITAÚ"
            params.payerName = "ROBERTO DE OLIVEIRA SANTOS"
            params.barCode = "34191.09065 44830. 1285 40141.906 8 00001.83120.59475"
            params.value = 223.24
            params.currentBalance = 3250
            params.payWithType = "My Bank"
            params.dueDate = Calendar.getInstance().time
            params.scheduledDueDate = Calendar.getInstance().time
            params.type = PaymentDetailsType.PAYMENT
            params.baseColor = "#f78c49"

            return params
        }
    }

}