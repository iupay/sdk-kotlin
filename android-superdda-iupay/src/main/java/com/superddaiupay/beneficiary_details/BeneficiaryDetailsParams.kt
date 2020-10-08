package com.superddaiupay.beneficiary_details

import android.view.View
import com.superddaiupay.account_details.PaymentHistory
import java.io.Serializable
import java.util.*

class BeneficiaryDetailsParams : Serializable {

    var historyReverse: Boolean = false
    var data: BeneficiaryDetailsInfo? = null
    var baseColor: String? = null
    var onClickBack: View.OnClickListener? = null
    var onClickOptions: View.OnClickListener? = null
    var onClickViewCard: View.OnClickListener? = null
    var onClickViewBeneficiaryDetails: View.OnClickListener? = null

    companion object {
        @JvmStatic
        fun example(): BeneficiaryDetailsParams {
            val params = BeneficiaryDetailsParams()
            params.data = BeneficiaryDetailsInfo()
            params.data!!.companyName = "Nu Pagamentos S.A."
            params.data!!.cnpj = "18.236.120/0001-58"
            params.data!!.cardNumber = "5162 **** **** 9090"
            params.data!!.authorizedLimit = false
            params.data!!.autoPayment = false
            params.data!!.cardHolderName = "Roberto de Oliveira Santos"

            params.data!!.paymentHistory = listOf(
                PaymentHistory("01/06/2020", 1286.55, true),
                PaymentHistory("01/05/2020", 1174.13),
                PaymentHistory("01/04/2020", 1660.89),
                PaymentHistory("01/03/2020", 998.22),
                PaymentHistory("01/02/2020", 1330.45),
                PaymentHistory("01/01/2020", 2589.99)
            )
            return params
        }
    }
}