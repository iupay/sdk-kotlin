@file:Suppress("unused", "SpellCheckingInspection")

package com.superddaiupay.cards

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import com.superddaiupay.R
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Logger

class CardParams : Serializable {
    var featured: Boolean = false
    var type: CardType? = CardType.DEFAULT
    var cardColor: String? = "#FFFFFF"
    var dueDate: Date? = null
    var barColor: String? = null
    var cnpj: String? = null
    var cardTitle: String? = null
    var text: String? = null
    var value: Number? = null
    var isFromMail: Boolean = false
    var isUserAdded: Boolean = false
    @Transient var logo: Bitmap? = null
    var textColor: String? = null
    var isDue: Boolean = false
    var isDueText: String? = DUE_TEXT

    //   Nao eh possivel sobreescrer estilo var containerStyle: String? = null
    var isPaid: Boolean = false
    var lightBillFlagStatus: LightBillFlagStatus? = null
    var imageWidth: Int? = null
    var imageHeight: Int? = null
    var isLocked: Boolean = false
    @Transient var onClickCard: View.OnClickListener? = null

    var creditCardText: String? = null

    enum class LightBillFlagStatus(val color: String) {
        GREEN("#8aa626"), YELLOW("#ebbf10"), RED("#e30613")
    }

    companion object {
        const val DUE_TEXT = "Vencendo hoje"
        const val LOCKED_TEXT: String = "Boleto protegido por senha"

        fun featuredCard(): CardParams {
            val params = CardParams()
            params.featured = true
            params.barColor = "#1dd15d"
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
            params.barColor = "#e30613"
            params.onClickCard = View.OnClickListener {
                Logger.getLogger("CardParams").info("LockedCard Click!!!")
            }
            return params
        }

        fun embasaCard(context: Context): CardParams {
            val params = CardParams()
            params.barColor = "#00529a"
            params.logo =  BitmapFactory.decodeResource(context.resources, R.raw.embasa)
            params.cardTitle = ""
            params.dueDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse("15/08/2020")
            params.isPaid = false
            params.value = 90.12
            params.isLocked = false
            return params
        }

        fun bmwCard(context: Context): CardParams {
            val params = CardParams()
            params.barColor = "#0d56f3"
            params.logo =  BitmapFactory.decodeResource(context.resources, R.raw.bmw)
            params.cardTitle = ""
            params.dueDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse("28/07/2020")
            params.isPaid = true
            params.value = 2550
            params.isLocked = false
            return params
        }

        fun rennerCard(context: Context): CardParams {
            val params = CardParams()
            params.barColor = "#e30613"
            params.logo =  BitmapFactory.decodeResource(context.resources, R.raw.renner)
            params.cardTitle = ""
            params.dueDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse("04/08/2020")
            params.isPaid = true
            params.value = 812.99
            params.isLocked = false
            return params
        }

        fun customCard(context: Context): CardParams {
            val params = CardParams()
            params.barColor = "#999999"
            params.logo =  BitmapFactory.decodeResource(context.resources, R.raw.profile_icon)
            params.cardTitle = "ARNALDO PESSOA LEAL"
            params.dueDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse("04/08/2020")
            params.isPaid = false
            params.value = 11000
            params.isLocked = false
            params.imageWidth = 150
            return params
        }
    }
}