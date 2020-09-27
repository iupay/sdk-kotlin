package com.superddaiupay.receipt

import java.io.Serializable
import java.util.*

class ReceiptParams: Serializable {
    var cedentName: String? = null
    var cnpj: String? = null
    var payerName: String? = null
    var barCode: String? = null
    var dueDate: Date? = null
    var paidDate: Date? = null
    var value: Number? = null
    var discount: Number? = null
    var interest: Number? = null
    var fine: Number? = null
    var chargedValue: Number? = null
    var authenticationCode: String? = null
    var baseColor: String? = null
    var onClickBack: OnClickBack? = null
    var onClickOptions: OnClickOptions? = null
    var onClickShareReceipt: OnClickShareReceipt? = null

    interface OnClickBack {
        fun onClickBack()
    }

    interface OnClickOptions {
        fun onClickOptions()
    }

    interface OnClickShareReceipt {
        fun onClickShareReceipt()
    }
}