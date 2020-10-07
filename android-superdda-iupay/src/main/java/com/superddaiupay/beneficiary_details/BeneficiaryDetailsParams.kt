package com.superddaiupay.beneficiary_details

import android.view.View
import com.superddaiupay.account_details.PaymentHistory
import com.superddaiupay.filter_searches.FilterSearchesParams
import java.io.Serializable
import java.util.logging.Logger

class BeneficiaryDetailsParams: Serializable {

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

            return params
        }
    }
}