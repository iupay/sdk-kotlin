package com.superddaiupay.payment_details

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.superddaiupay.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAMS = "params"

class PaymentDetailsFragment : Fragment() {
    private var params: PaymentDetailsParams? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it.getSerializable(ARG_PARAMS) as PaymentDetailsParams?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_payment_details, container, false)

        val beneficiaryName = view.findViewById<TextView>(R.id.paymentDetailsTvBeneficiarioValue)
        val bankName = view.findViewById<TextView>(R.id.paymentDetailsTvBancoValue)
        val payerName = view.findViewById<TextView>(R.id.paymentDetailsTvPagadorValue)
        val barCode = view.findViewById<TextView>(R.id.paymentDetailsTvCodigoBarrasValue)
        val value = view.findViewById<TextView>(R.id.paymentDetailsTvValorValue)
        val labelValue = view.findViewById<TextView>(R.id.paymentDetailsTvValorLabel)
        val currentBalance = view.findViewById<TextView>(R.id.paymentDetailsTvSaldoDisponivelValue)
        val payWithType = view.findViewById<TextView>(R.id.paymentDetailsTvPagarComValue)
        val dueDate = view.findViewById<TextView>(R.id.paymentDetailsTvVencimentoValue)
        val scheduledDueDate = view.findViewById<TextView>(R.id.paymentDetailsTvAgendadoParaValue)
        val btnConfirm = view.findViewById<Button>(R.id.paymentDetailsBtnConfirm)
        val clValor = view.findViewById<ConstraintLayout>(R.id.paymentDetailsCLValor)

        beneficiaryName.text = params?.beneficiaryName
        bankName.text = params?.bankName
        payerName.text = params?.payerName
        barCode.text = params?.barCode
        val nf = NumberFormat.getInstance(Locale("pt", "BR"))
        nf.minimumFractionDigits = 2
        value.text = "R$ " + nf.format(params?.value?.toDouble() ?: 0)
        currentBalance.text = nf.format(params?.currentBalance?.toDouble() ?: 0)
        payWithType.text = params?.payWithType
        dueDate.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            .format(params?.dueDate as Date).toUpperCase(Locale.ROOT)
        scheduledDueDate.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            .format(params?.scheduledDueDate as Date).toUpperCase(Locale.ROOT)
        if (params?.type == PaymentDetailsParams.PaymentDetailsType.SCHEDULE) {
            btnConfirm.text =
                requireContext().getString(R.string.payment_details_confirmar_agendamento)
        }
        if (params?.baseColor != null) {
            val color = ColorStateList.valueOf(Color.parseColor(params?.baseColor))
            labelValue.setTextColor(color)
            value.setTextColor(color)
            payWithType.setTextColor(color)
            scheduledDueDate.setTextColor(color)
            btnConfirm.background?.colorFilter = PorterDuffColorFilter(
                Color.parseColor(params!!.baseColor), PorterDuff.Mode.SRC_ATOP
            )

            clValor.background?.colorFilter = PorterDuffColorFilter(
                Color.parseColor(params!!.baseColor), PorterDuff.Mode.SRC_ATOP
            )
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(params: PaymentDetailsParams) =
            PaymentDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAMS, params)
                }
            }
    }
}