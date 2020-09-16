package com.superddaiupay.cards

import android.view.View
import java.io.Serializable
import java.time.LocalDateTime

class CardParams : Serializable {
    var cardColor: String? = null
    var dueDate: LocalDateTime? = null
    var barColor: String? = null
    var cnpj: String? = null
    var cardTitle: String? = null
    var text: String? = null
    var value: Number? = null
    var isFromMail: Boolean? = null
    var isUserAdded: Boolean? = null
    var type: CardType? = null
    var logo: String? = null
    var textColor: String? = null
    var isDue: Boolean? = null
    var isDueText: String? = null
//   Nao eh possivel sobreescrer estilo
//  var containerStyle: String? = null
    var isPaid: Boolean? = null
    var lightBillFlagStatus: LightBillFlagStatus? = null
    var imageWidth: Number? = null
    var imageHeight: Number? = null
    var isLocked: Boolean? = null
    var onClickCard: View.OnClickListener? = null

    enum class CardType {
        netflix, nubank, lightBill, default
    }

    enum class LightBillFlagStatus {
        green, yellow, red
    }
}