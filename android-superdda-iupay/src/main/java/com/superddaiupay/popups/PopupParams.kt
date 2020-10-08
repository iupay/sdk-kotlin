package com.superddaiupay.popups

import com.superddaiupay.receipt.ReceiptParams
import java.io.Serializable
import java.util.*

class PopupParams : Serializable {
    var title: String? = null
    var onClickClose: OnClickClose? = null

    interface OnClickClose {
        fun onClickClose()
    }

}