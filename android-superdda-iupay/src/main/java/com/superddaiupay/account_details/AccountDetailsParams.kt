package com.superddaiupay.account_details

import android.view.View
import java.io.Serializable

class AccountDetailsParams : Serializable {
    var chartDataText: String? = null
    var chartDataValue: String? = null
    var chartLegend: String? = null
    var chartWidth: String? = null
    var chartData: String? = null
    var data: AccountDetailsInfo? = null
    var baseColor: String? = null
    var pdfAvailable: String? = null
    var onClickBack: View.OnClickListener? = null
    var onClickOptions: View.OnClickListener? = null
    var onClickViewAccountDetails: View.OnClickListener? = null
    var onClickViewPDF: View.OnClickListener? = null
    var onClickRejectAccount: View.OnClickListener? = null
    var onClickCopyBarcode: View.OnClickListener? = null
    var onSwitchAutoPaymentChange: View.OnClickListener? = null
    var onClickPaymentScheduleButton: View.OnClickListener? = null
}