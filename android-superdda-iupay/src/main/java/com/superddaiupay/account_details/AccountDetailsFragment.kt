package com.superddaiupay.account_details

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
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
import com.superddaiupay.popups.AccountPopupFragment
import com.superddaiupay.popups.PopupParams
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Logger
import kotlin.collections.ArrayList
import kotlin.math.roundToInt


private const val ARG_PARAMS = "params"

class AccountDetailsFragment : Fragment() {
    private var params: AccountDetailsParams? = null
    private lateinit var chart: LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it.getSerializable(ARG_PARAMS) as AccountDetailsParams?
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account_details, container, false)
        chart = view.findViewById(R.id.accountChart)
        chart.viewTreeObserver.addOnGlobalLayoutListener(
            object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    chart.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    initLineChart()
                }
            },
        )
        val companyName = view.findViewById<TextView>(R.id.accountTvCompanyName)
        val cnpj = view.findViewById<TextView>(R.id.accountTvCnpj)
        val cardNumber = view.findViewById<TextView>(R.id.accountTvCartao)
        val billDate = view.findViewById<TextView>(R.id.accountTvMes)
        val value = view.findViewById<TextView>(R.id.accountTvValor)
        val minimumPaymentValue = view.findViewById<TextView>(R.id.accountTvPagamnetoMinimo)
        val dueDate = view.findViewById<TextView>(R.id.accountTvVencimento)
        val barCode = view.findViewById<TextView>(R.id.accountTvCodigoDeBarras)
        val accountTvDebitoAutomatico = view.findViewById<TextView>(R.id.accountTvDebitoAutomatico)
        val accountSwDebitoAutomatico =
            view.findViewById<SwitchCompat>(R.id.accountSwDebitoAutomatico)
        val accountClChartBottom = view.findViewById<ConstraintLayout>(R.id.accountClChartBottom)
        val accountChartDataText = view.findViewById<TextView>(R.id.accountChartDataText)
        val accountChartDataValue = view.findViewById<TextView>(R.id.accountChartDataValue)
        val accountClTitle = view.findViewById<ConstraintLayout>(R.id.accountClTitle)
        val accountBtnPdf = view.findViewById<Button>(R.id.accountBtnPdf)
        val accountBtnRecusar = view.findViewById<Button>(R.id.accountBtnRecusar)
        val accountBtnVerDetalhes = view.findViewById<Button>(R.id.accountBtnVerDetalhes)

        val baseColor = Color.parseColor(params?.baseColor ?: "#8f06c3")
        companyName.text = params?.data?.companyName
        cnpj.text = params?.data?.cnpj
        cardNumber.text = params?.data?.cardNumber
        billDate.text = params?.data?.billDetails?.billDate
        val nf = NumberFormat.getInstance(Locale("pt", "BR"))
        nf.minimumFractionDigits = 2
        value.text = nf.format(params?.data?.billDetails?.value?.toDouble() ?: 0)
        minimumPaymentValue.text = nf.format(
            params?.data?.billDetails?.minimumPaymentValue?.toDouble() ?: 0
        )

        val dueDateVal = params?.data?.billDetails?.dueDate
        if (dueDateVal != null) {
            dueDate.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                .format(dueDateVal).toUpperCase(Locale.ROOT)
        } else {
            dueDate.text = ""
        }
        barCode.text = params?.data?.billDetails?.barCode

        if (params?.data?.isAutomaticDebit!!) {
            accountSwDebitoAutomatico.visibility = View.INVISIBLE
            accountTvDebitoAutomatico.text =
                "Conta em Débito automático no " + params?.data?.automaticDebitBankName
        } else {
            accountSwDebitoAutomatico.visibility = View.VISIBLE
            accountTvDebitoAutomatico.text = "Pagamento automático no dia do vencimento"
        }

        accountClChartBottom.background?.colorFilter = PorterDuffColorFilter(
            this.getColorWithAlpha(baseColor, 0.3f), PorterDuff.Mode.SRC_ATOP
        )
        accountChartDataText.setTextColor(baseColor)
        accountChartDataValue.setTextColor(baseColor)
        companyName.setTextColor(baseColor)
        accountClTitle.background?.colorFilter = PorterDuffColorFilter(
            baseColor,
            PorterDuff.Mode.SRC_ATOP
        )
        accountChartDataText.text = params?.chartDataText ?: ""
        accountChartDataValue.text = params?.chartDataValue ?: ""
        accountBtnPdf.setTextColor(ColorStateList.valueOf(Color.parseColor(params?.baseColor)))
        accountBtnPdf.background?.colorFilter = PorterDuffColorFilter(
            Color.parseColor(params!!.baseColor), PorterDuff.Mode.SRC_ATOP
        )
        accountBtnVerDetalhes.setTextColor(ColorStateList.valueOf(Color.parseColor(params?.baseColor)))
        accountBtnVerDetalhes.background?.colorFilter = PorterDuffColorFilter(
            Color.parseColor(params!!.baseColor), PorterDuff.Mode.SRC_ATOP
        )
        accountBtnRecusar.setTextColor(ColorStateList.valueOf(Color.parseColor(params?.baseColor)))
        accountBtnRecusar.background?.colorFilter = PorterDuffColorFilter(
            Color.parseColor(params!!.baseColor), PorterDuff.Mode.SRC_ATOP
        )

        accountBtnVerDetalhes.setOnClickListener { v ->
            val popupParams = PopupParams()
            popupParams.title = "Detalhes da conta"
            popupParams.onClickClose = object : PopupParams.OnClickClose {
                override fun onClickClose() {
                    Logger.getLogger("AccountPopupFragment").info("Closed Click")
                }
            }
            val popupFragment = AccountPopupFragment.newInstance(popupParams, params!!)
            popupFragment.show(requireFragmentManager(), "missiles")
        }

        return view
    }

    private fun getColorWithAlpha(color: Int, ratio: Float): Int {
        val alpha = (Color.alpha(color) * ratio).roundToInt()
        val r = Color.red(color)
        val g = Color.green(color)
        val b = Color.blue(color)
        return Color.argb(alpha, r, g, b)
    }

    fun initLineChart() {
        if (params?.chartData != null) {
            val chartColor = Color.parseColor(params?.baseColor ?: "#8f06c3")
            val colorWhite = Color.parseColor("#FFFFFF")
            val labels = params!!.chartData!!.map(ChartData::label)
            val entries = ArrayList<Entry>()
            for (i in 0 until params!!.chartData!!.size) {
                entries.add(Entry(i.toFloat(), params!!.chartData!![i].value.toFloat()))
            }
            val dataSet = LineDataSet(entries, null)
            dataSet.color = colorWhite
            dataSet.setCircleColor(colorWhite)
            dataSet.circleHoleColor = chartColor
            dataSet.circleHoleRadius = 2f
            dataSet.lineWidth = 1f
            dataSet.circleRadius = 4f
            dataSet.setDrawCircleHole(true)
            dataSet.setDrawValues(false)
            dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER // smooth curves for lines

            val xAxis: XAxis = chart.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.granularity = 1f
            xAxis.isGranularityEnabled = true
            xAxis.valueFormatter = IndexAxisValueFormatter(labels)
            xAxis.textColor = colorWhite
            xAxis.setDrawGridLines(false)
            xAxis.setDrawAxisLine(false)
            xAxis.textSize = 14f
            xAxis.spaceMin = 0.2f
            xAxis.spaceMax = 0.2f
            xAxis.setDrawLabels(true)
            xAxis.yOffset = -5f

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
            chart.description.text = ""
            chart.setGridBackgroundColor(chartColor)
            chart.data = LineData(dataSet)
            chart.legend.isEnabled = false // remove lineDataSet label
            chart.legend.yEntrySpace = 4f
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