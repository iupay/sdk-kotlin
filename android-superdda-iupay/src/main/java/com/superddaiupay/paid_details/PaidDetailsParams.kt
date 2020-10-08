package com.superddaiupay.paid_details

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.superddaiupay.R
import java.io.Serializable
import java.util.*

class PaidDetailsParams : Serializable {
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

    companion object {
        fun example(context: Context): PaidDetailsParams {
            val params = PaidDetailsParams()

            params.beneficiaryName = "COMPANHIA DE ELETRICIDADE DO RIO DE JANEIRO"
            params.paidDate = Calendar.getInstance().time
            params.screenTitle = "CERJ"
            params.value = 223.24
            params.dueDate = Calendar.getInstance().time
            params.paymentMessage = "Sua conta está paga"
            params.baseColor = "#FF0000"
            params.screenImage =
                BitmapFactory.decodeResource(context.resources, R.drawable.ic_lightbill)
            params.receiptAvailable = false

            return params
        }
    }

}