package com.superddaiupay.account_details

import android.view.View
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class AccountDetailsParams : Serializable {
    var chartDataText: String? = null
    var chartDataValue: String? = null
    var chartLegend: String? = null
    var chartWidth: String? = null
    var chartData: ArrayList<ChartData>? = null
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

    companion object {
        @JvmStatic
        fun example(): AccountDetailsParams {
            val params = AccountDetailsParams()
            params.chartData = ArrayList()
            params.chartData!!.add(ChartData("Fev", 950))
            params.chartData!!.add(ChartData("Mar", 1050))
            params.chartData!!.add(ChartData("Abr", 800))
            params.chartData!!.add(ChartData("Mai", 970))
            params.chartData!!.add(ChartData("Jun", 1300))
            params.chartData!!.add(ChartData("Jul", 1500))
            params.data?.companyName = "Nu Pagamentos S.A."
            params.data?.cnpj = "18.236.120/0001-58"
            params.data?.cardNumber = "5162 **** **** 9090"
            params.data?.billDetails?.billDate = "JUN 2020"
            params.data?.billDetails?.value = 1230.89
            params.data?.billDetails?.minimumPaymentValue = 400
            params.data?.billDetails?.dueDate = Calendar.getInstance().time
            params.data?.billDetails?.barCode = "34191.09065 44830. 1285 40141.906 8 00001.83120.59475"

            return params
        }
    }


}