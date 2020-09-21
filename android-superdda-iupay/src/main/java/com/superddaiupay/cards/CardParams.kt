package com.superddaiupay.cards

import android.view.View
import android.widget.TextView
import android.widget.Toast
import java.io.Serializable
import java.util.*
import java.util.logging.Logger

class CardParams : Serializable {
    var cardColor: String? = "#FFFFFF"
    var dueDate: Date? = null
    var barColor: String? = null
    var cnpj: String? = null
    var cardTitle: String? = null
    var text: String? = null
    var value: Number? = null
    var isFromMail: Boolean = false
    var isUserAdded: Boolean = false
    var type: CardType? = CardType.DEFAULT
    var logo: Int? = null
    var textColor: String? = null
    var isDue: Boolean = false
    var isDueText: String? = DUE_TEXT

    //   Nao eh possivel sobreescrer estilo var containerStyle: String? = null
    var isPaid: Boolean = false
    var lightBillFlagStatus: LightBillFlagStatus? = null
    var imageWidth: Int? = null
    var imageHeight: Int? = null
    var isLocked: Boolean = false
    var onClickCard: View.OnClickListener? = null

    enum class CardType {
        NETFLIX, NUBANK, LIGHTBILL, DEFAULT
    }

    enum class LightBillFlagStatus(val color: String) {
        GREEN("#8aa626"), YELLOW("#ebbf10"), RED("#e30613")
    }

    companion object {
        const val DUE_TEXT = "Vencendo hoje"
        const val LOCKED_TEXT: String = "Boleto protegido por senha"

        fun defaultCard(): CardParams {
            val params = CardParams()
            params.barColor = "#ff9000"
            params.cardTitle = "CERJ"
            params.cnpj = "99.9999.999.0001-99"
            params.dueDate = Calendar.getInstance().time
            params.isFromMail = true
            params.isUserAdded = true
            params.text = "Hello Storybook"
            params.value = 500.0
            params.onClickCard = View.OnClickListener {
                Logger.getLogger("CardParams").info("Default Card Click!!!")
            }
            return params
        }

        fun netflixCard(): CardParams {
            val params = CardParams()
            params.cnpj = "99.9999.999.0001-99"
            params.dueDate = Calendar.getInstance().time
            params.isFromMail = true
            params.text = "Assinatura Netflix"
            params.type = CardType.NETFLIX
            params.value = 50
            params.onClickCard = View.OnClickListener {
                Logger.getLogger("CardParams").info("Netflix Card Click!!!")
            }
            return params
        }

        fun lightBillCard(): CardParams {
            val params = CardParams()
            params.cnpj = "99.9999.999.0001-99"
            params.dueDate = Calendar.getInstance().time
            params.isFromMail = true
            params.lightBillFlagStatus = LightBillFlagStatus.YELLOW
            params.text = "Cor da Bandeira"
            params.type = CardType.LIGHTBILL
            params.value = 50
            params.onClickCard = View.OnClickListener {
                Logger.getLogger("CardParams").info("Light Bill Card Click!!!")
            }
            return params
        }

        fun lockedCard(): CardParams {
            val params = CardParams()
            params.isLocked = true
            params.onClickCard = View.OnClickListener {
                Logger.getLogger("CardParams").info("LockedCard Click!!!")
            }
            return params
        }
    }
}