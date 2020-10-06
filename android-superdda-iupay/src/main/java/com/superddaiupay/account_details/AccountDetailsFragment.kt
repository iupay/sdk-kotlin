package com.superddaiupay.account_details

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.superddaiupay.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


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
        val accountSwDebitoAutomatico = view.findViewById<SwitchCompat>(R.id.accountSwDebitoAutomatico)

        companyName.text = params?.data?.companyName
        cnpj.text = params?.data?.cnpj
        cardNumber.text = params?.data?.cardNumber
        billDate.text = params?.data?.billDetails?.billDate
        val nf = NumberFormat.getInstance(Locale("pt", "BR"))
        nf.minimumFractionDigits = 2
        value.text = nf.format(params?.data?.billDetails?.value?.toDouble() ?: 0)
        minimumPaymentValue.text =  nf.format(params?.data?.billDetails?.minimumPaymentValue?.toDouble() ?: 0)

        var dueDateVal = params?.data?.billDetails?.dueDate
        if (dueDateVal != null) {
            dueDate.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                .format(dueDateVal).toUpperCase(Locale.ROOT)
        } else {
            dueDate.text = ""
        }
        barCode.text = params?.data?.billDetails?.barCode

        if (params?.data?.isAutomaticDebit!!) {
            accountSwDebitoAutomatico.visibility = View.INVISIBLE
            accountTvDebitoAutomatico.text = "Conta em Débito automático no " + params?.data?.automaticDebitBankName
        } else {
            accountSwDebitoAutomatico.visibility = View.VISIBLE
            accountTvDebitoAutomatico.text = "Pagamento automático no dia do vencimento"
        }

        return view
    }

    fun initLineChart() {
        if (params?.chartData != null) {
            val nubankColor = Color.parseColor("#8e05c2")
            val colorWhite =  Color.parseColor("#FFFFFF")
            val labels = params!!.chartData!!.map(ChartData::label)
            val entries = ArrayList<Entry>()
            for (i in 0 until params!!.chartData!!.size) {
                entries.add(Entry(i.toFloat(), params!!.chartData!![i].value.toFloat()))
            }
            val dataSet = LineDataSet(entries, "Resumo das Faturas Anteriores")
            dataSet.color = colorWhite
            dataSet.setCircleColor(colorWhite)
            dataSet.circleHoleColor = nubankColor
            dataSet.circleHoleRadius = 2f
            dataSet.lineWidth = 1f
            dataSet.circleRadius = 4f
            dataSet.setDrawCircleHole(true)
            dataSet.setDrawValues(false)

            val xAxis: XAxis = chart.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.granularity = 1f
            xAxis.isGranularityEnabled = true
            xAxis.valueFormatter = IndexAxisValueFormatter(labels)
            xAxis.textColor = colorWhite

            chart.setBackgroundColor(nubankColor)
            chart.description.text = ""
            chart.setGridBackgroundColor(nubankColor)
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