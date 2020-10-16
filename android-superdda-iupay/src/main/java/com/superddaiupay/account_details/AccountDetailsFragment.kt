package com.superddaiupay.account_details

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.superddaiupay.R
import com.superddaiupay.Utils
import com.superddaiupay.popups.AccountPopupFragment
import com.superddaiupay.popups.PopupParams
import kotlinx.android.synthetic.main.fragment_account_details.*
import java.util.logging.Logger


private const val ARG_PARAMS = "params"

class AccountDetailsFragment : Fragment() {
    private lateinit var params: AccountDetailsParams
    private lateinit var chart: LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it.getSerializable(ARG_PARAMS) as AccountDetailsParams
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account_details, container, false)
        chart = view.findViewById(R.id.accountChart)
        chart.viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    chart.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    initLineChart()
                }
            }
        )
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val baseColor = Color.parseColor(params.baseColor ?: "#F78C49")
        val baseColorFilter = Utils.parseColorFilter(baseColor)
        accountIvLogo.visibility = View.INVISIBLE
        params.data?.companyLogo.let {
            accountIvLogo.setImageBitmap(it)
            accountIvLogo.visibility = View.VISIBLE
            val logoLayout = accountIvLogo.layoutParams as ConstraintLayout.LayoutParams
            logoLayout.matchConstraintMaxWidth = 200
            logoLayout.matchConstraintMaxHeight = 150
        }
        accountTvCompanyName.text = params.data?.companyName
        accountTvCnpj.text = "CNPJ: " + params.data?.cnpj
        accountTvCartao.text = "Cartão " + params.data?.cardNumber
        accountTvMes.text = params.data?.billDetails?.billDate
        accountTvValor.text = "R$ " + Utils.formatMoney(params.data?.billDetails?.value)
        accountTvPagamnetoMinimo.text =
            "R$ " + Utils.formatMoney(params.data?.billDetails?.minimumPaymentValue)
        accountTvVencimento.text =
            Utils.formatDate(params.data?.billDetails?.dueDate, "dd MMM yyyy")
        accountTvCodigoDeBarras.text = params.data?.billDetails?.barCode
        if (params.data?.isAutomaticDebit!!) {
            accountSwDebitoAutomatico.visibility = View.INVISIBLE
            accountTvDebitoAutomatico.text =
                "Conta em Débito automático no " + params.data?.automaticDebitBankName
        } else {
            accountSwDebitoAutomatico.visibility = View.VISIBLE
            accountTvDebitoAutomatico.text = "Pagamento automático no dia do vencimento"
        }
        accountClChartBottom.background?.colorFilter =
            Utils.parseColorFilter(Utils.getColorWithAlpha(baseColor, 0.3f))
        accountChartDataText.setTextColor(baseColor)
        accountChartDataValue.setTextColor(baseColor)
        accountTvCompanyName.setTextColor(baseColor)
        accountClTitle.background?.colorFilter = baseColorFilter
        accountChartDataText.text = params.chartDataText ?: ""
        accountChartDataValue.text = params.chartDataValue ?: ""
        accountBtnPdf.setTextColor(ColorStateList.valueOf(Color.parseColor(params.baseColor)))
        accountBtnPdf.background?.colorFilter = baseColorFilter
        accountBtnVerDetalhes.setTextColor(ColorStateList.valueOf(Color.parseColor(params.baseColor)))
        accountBtnVerDetalhes.background?.colorFilter = baseColorFilter
        accountBtnRecusar.setTextColor(ColorStateList.valueOf(Color.parseColor(params.baseColor)))
        accountBtnRecusar.background?.colorFilter = baseColorFilter
        if (!params.pdfAvailable) {
            accountBtnPdf.isEnabled = false
            accountBtnPdf.text = "PDF da conta não disponível"
            accountBtnPdf.background?.colorFilter = Utils.parseColorFilter("#e8e8e8")
            accountBtnPdf.setTextColor(Color.WHITE)
        }
        // ACTIVE SWITCH COLORS
        Utils.setSwitchColor(accountSwDebitoAutomatico, baseColor)
        accountBtnBack.setOnClickListener(params.onClickBack)
        accountBtnOptions.setOnClickListener(params.onClickOptions)
        accountIvCodigoCopy.setOnClickListener(params.onClickCopyBarcode)
        accountBtnPdf.setOnClickListener(params.onClickViewPDF)
        accountSwDebitoAutomatico.setOnCheckedChangeListener(params.onSwitchAutoPaymentChange)
        accountPaymentScheduleButton.setOnClickListener(params.onClickPaymentScheduleButton)
        accountPaymentScheduleButton.setTextColor(Color.WHITE)
        accountPaymentScheduleButton.background?.colorFilter = baseColorFilter
        if (params.data?.isAutomaticDebit!!) {
            accountPaymentScheduleButton.visibility = View.GONE
        }
        accountBtnRecusar.setOnClickListener(params.onClickRejectAccount)
        accountTvChartTitle.text = params.chartLegend ?: "Resumo das Faturas Anteriores"
        accountChart.layoutParams.width = params.chartWidth ?: 0
        accountSwDebitoAutomatico.isChecked = params.data?.autoPayment!!
        if (params.data?.isFromIuPay == false) {
            accountIvIuPay.visibility = View.GONE
        }
        if (params.data?.isUserAdded!!) {
            accountIvUser.setImageResource(R.drawable.ic_user)
        } else {
            accountIvUser.setImageResource(R.drawable.ic_user_false)
            accountIvUser.colorFilter = Utils.parseColorFilter("#c1272d")
        }
        accountBtnVerDetalhes.setOnClickListener {
            val popupParams = PopupParams()
            popupParams.title = "Detalhes da conta"
            popupParams.onClickClose = object : PopupParams.OnClickClose {
                override fun onClickClose() {
                    Logger.getLogger("AccountPopupFragment").info("Closed Click")
                }
            }
            val popupFragment = AccountPopupFragment.newInstance(popupParams, params)
            popupFragment.show(requireFragmentManager(), "missiles")
            params.onClickViewAccountDetails?.onClick(it)
        }
        accountTvHistoricoPagamentos.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    fun initLineChart() {
        if (params.chartData != null) {
            val chartColor = Color.parseColor(params.baseColor ?: "#8f06c3")
            val colorWhite = Color.parseColor("#FFFFFF")
            val labels = params.chartData!!.map(ChartData::label)
            val entries = ArrayList<Entry>()
            for (i in 0 until params.chartData!!.size) {
                entries.add(Entry(i.toFloat(), params.chartData!![i].value.toFloat()))
            }
            val dataSet = LineDataSet(entries, null)
            dataSet.color = colorWhite
            dataSet.setCircleColor(colorWhite)
            dataSet.circleHoleColor = chartColor
            dataSet.circleHoleRadius = 2f
            dataSet.lineWidth = 1.5f
            dataSet.circleRadius = 4f
            dataSet.setDrawCircleHole(true)
            dataSet.setDrawValues(false)
            dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER // smooth curves for lines
            dataSet.isHighlightEnabled = false

            val xAxis: XAxis = chart.xAxis
            xAxis.valueFormatter = IndexAxisValueFormatter(labels)
            xAxis.textColor = colorWhite
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.isGranularityEnabled = true
            xAxis.granularity = 1f
            xAxis.textSize = 14f
            xAxis.spaceMin = 0.2f
            xAxis.spaceMax = 0.2f
            xAxis.yOffset = -5f
            xAxis.setDrawGridLines(false)
            xAxis.setDrawAxisLine(false)
            xAxis.setDrawLabels(true)

            val axisRight: YAxis = chart.axisRight
            axisRight.isEnabled = false

            val axisLeft: YAxis = chart.axisLeft
            axisLeft.isEnabled = false
            axisLeft.setDrawGridLines(false)

            chart.setDrawGridBackground(false)
            chart.setTouchEnabled(true)
            chart.isClickable = false
            chart.isDoubleTapToZoomEnabled = false

            chart.setBackgroundColor(chartColor)
            chart.setGridBackgroundColor(chartColor)
            chart.description.text = ""
            chart.legend.isEnabled = false // remove lineDataSet label
            chart.legend.yEntrySpace = 4f
            chart.data = LineData(dataSet)
            chart.invalidate()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(params: AccountDetailsParams) =
            AccountDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAMS, params)
                }
            }
    }
}
