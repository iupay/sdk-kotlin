package com.superddaiupay.cards.beneficiary

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import com.superddaiupay.R
import com.superddaiupay.cards.CardType
import java.io.Serializable
import java.util.logging.Logger

class BeneficiaryCardParams : Serializable {
    var type: CardType? = CardType.DEFAULT
    var cardColor: String? = "#FFFFFF"
    var barColor: String? = null
    var cnpj: String? = null
    var creditCardText: String? = null
    var cardTitle: String? = null
    var limitValueText: String? = null
    var limitValue: Number? = null
    var beneficiaryType: String? = null
    @Transient var logo: Bitmap? = null
    var imageWidth: Int? = null
    var imageHeight: Int? = null
    var text: String? = null
    var textColor: String? = null
    var isActive: Boolean = false
    @Transient var onClickCard: View.OnClickListener? = null
    @Transient var onActiveChange: CompoundButton.OnCheckedChangeListener? = null
    var circleActiveColor: String? = null
    var circleInActiveColor: String? = null
    var backgroundActive: String? = null
    var backgroundInactive: String? = null

    companion object {
        @JvmStatic
        fun example(context: Context): BeneficiaryCardParams {
            val params = BeneficiaryCardParams()
            params.barColor = "#999999"
            params.textColor = "#727272"
            params.cardTitle = "CERJ"
            params.cnpj = "99.9999.999.0001-99"
            params.isActive = true
            params.limitValue = 300
            params.limitValueText = "Valor Limite"
            params.logo = BitmapFactory.decodeResource(context.resources, R.raw.profile_icon)
            params.imageWidth = 60
            params.imageHeight = 60
            params.text = "Pagamento automático no dia do vencimento"
            params.beneficiaryType = "Conta"
            params.onClickCard = View.OnClickListener {
                Logger.getLogger("BeneficiaryCardParams").info("Beneficiary Card Click!!!")
            }
            params.onActiveChange =
                CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                    val v = buttonView.parent as View
                    val cnpj = v.findViewById<TextView>(R.id.beneficiaryCardTvCnpjValue)
                    Logger.getLogger("BeneficiaryCardParams")
                        .info("Beneficiary Card SWITCH Change!!! CNPJ: " + cnpj?.text)
                    Toast.makeText(
                        context,
                        "SWITCH TO $isChecked | CNPJ: ${cnpj?.text}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            return params
        }
    }
}
