package com.superddaiupay.paid_details

import android.graphics.Bitmap
import com.superddaiupay.receipt.ReceiptParams
import java.io.Serializable
import java.util.*

class PaidDetailsParams: Serializable {
    var beneficiaryName: String? = null
    var paidDate: Date? = null
    var screenTitle: String? = null
    var screenImage: Bitmap? = null
    var value: Number? = null
    var dueDate: Date? = null
    var baseColor: String? = null
    var receiptAvailable: Boolean? = null
    var paymentMessage: String? = null
    var onClickBack: OnClickBack? = null
    var onClickViewReceipt: OnClickViewReceipt? = null

    interface OnClickBack {
        fun onClickBack()
    }

    interface OnClickViewReceipt {
        fun onClickViewReceipt()
    }

    fun example(): PaidDetailsParams {
        val params = PaidDetailsParams()

        params.beneficiaryName = "COMPANHIA DE ELETRICIDADE DO RIO DE JANEIRO"
        params.paidDate = Calendar.getInstance().time
        params.screenTitle = "CERJ"
        params.value = 223.24
        params.dueDate = Calendar.getInstance().time
        params.paymentMessage = "Sua conta está paga"

        return params
    }
}