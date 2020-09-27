package com.superddaiupay.beneficiary_details

import android.view.View
import java.io.Serializable

class BeneficiaryDetailsParams: Serializable {

    var historyReverse: Boolean = false
    var data: BeneficiaryDetailsInfo? = null
    var baseColor: String? = null
    var onClickBack: View.OnClickListener? = null
    var onClickOptions: View.OnClickListener? = null
    var onClickViewCard: View.OnClickListener? = null
    var onClickViewBeneficiaryDetails: View.OnClickListener? = null
}