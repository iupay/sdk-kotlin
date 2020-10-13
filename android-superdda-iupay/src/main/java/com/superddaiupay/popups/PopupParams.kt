package com.superddaiupay.popups

import java.io.Serializable

class PopupParams : Serializable {
    var title: String? = null
    @Transient
    var onClickClose: OnClickClose? = null

    interface OnClickClose {
        fun onClickClose()
    }

}