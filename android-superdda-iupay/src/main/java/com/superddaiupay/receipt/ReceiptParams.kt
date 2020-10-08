package com.superddaiupay.receipt

import java.io.Serializable
import java.util.*

class ReceiptParams : Serializable {
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

    companion object {

        fun example(): ReceiptParams {
            val params = ReceiptParams()
            params.cedentName = "COMPANHIA DE ELETRICIDADE DO RIO DE JANEIRO"
            params.cnpj = "15.139.629/0001-99"
            params.payerName = "ROBERTO DE OLIVEIRA SANTOS"
            params.barCode = "34191.09065 44830. 1285 40141.906 8 00001.83120.59475"
            params.dueDate = Calendar.getInstance().time
            params.paidDate = Calendar.getInstance().time
            params.value = 223.24
            params.discount = 0.0
            params.interest = 0.0
            params.fine = 0.0
            params.chargedValue = 223.24
            params.authenticationCode = "A.6DE.DF4.75E.DBB,128"
            params.baseColor = "#FF0000"

            return params
        }
    }

}