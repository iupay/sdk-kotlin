package com.superddaiupay.account_details

import android.content.Context
import android.graphics.BitmapFactory
import android.view.View
import com.superddaiupay.R
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
        fun example(context: Context): AccountDetailsParams {
            val params = AccountDetailsParams()
            params.baseColor = "#8f06c3" // "#FF5555"
            params.chartDataText = "JUNHO"
            params.chartDataValue = "R$ 1.983,36"
            params.chartData = ArrayList()
            params.chartData!!.add(ChartData("Fev", 950))
            params.chartData!!.add(ChartData("Mar", 1050))
            params.chartData!!.add(ChartData("Abr", 800))
            params.chartData!!.add(ChartData("Mai", 970))
            params.chartData!!.add(ChartData("Jun", 1300))
            params.chartData!!.add(ChartData("Jul", 1500))
            params.data = AccountDetailsInfo()
            params.data!!.companyLogo =
                BitmapFactory.decodeResource(context.resources, R.raw.nubank)
            params.data!!.companyName = "Nu Pagamentos S.A."
            params.data!!.cnpj = "18.236.120/0001-58"
            params.data!!.cardNumber = "5162 **** **** 9090"
            params.data!!.billDetails = BillDetails()
            params.data!!.billDetails!!.billDate = "JUN 2020"
            params.data!!.billDetails!!.value = 1230.89
            params.data!!.billDetails!!.minimumPaymentValue = 400
            params.data!!.billDetails!!.dueDate = Calendar.getInstance().time
            params.data!!.billDetails!!.barCode =
                "34191.09065 44830. 1285 40141.906 8 00001.83120.59475"
            params.data!!.billDetails?.totalLimitValue = 1.200
            params.data!!.billDetails?.totalWithdrawLimitValue = 600
            params.data!!.billDetails?.interestRate = 14
            params.data!!.billDetails?.interestRateCET = 385.17
            params.data!!.billDetails?.interestInstallmentFine = 22
            params.data!!.billDetails?.interestInstallmentRate = 12
            params.data!!.billDetails?.interestInstallmentRateCET = 15
            return params
        }
    }


}