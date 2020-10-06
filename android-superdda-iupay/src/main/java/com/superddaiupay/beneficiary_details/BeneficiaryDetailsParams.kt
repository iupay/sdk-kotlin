package com.superddaiupay.beneficiary_details

import android.view.View
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
            return params
        }
    }
}