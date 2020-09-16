package com.superddaiupay.cards

import android.view.View
import java.io.Serializable

class CardParams : Serializable {
    var cardTitle: String? = null
    var cnpj: String? = null
    var barColor: String? = null
    var cardColor: String? = null
    var onClickCard: View.OnClickListener? = null
}