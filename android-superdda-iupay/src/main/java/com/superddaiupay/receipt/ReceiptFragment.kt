package com.superddaiupay.receipt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.superddaiupay.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAMS = "params"

class ReceiptFragment : Fragment() {
    private var params: ReceiptParams? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it.getSerializable(ARG_PARAMS) as ReceiptParams?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_receipt, container, false)

        val cedentName =view.findViewById<TextView>(R.id.receiptTvCedenteValue)
        val cnpj = view.findViewById<TextView>(R.id.receiptTvCnpjValue)
        val payerName = view.findViewById<TextView>(R.id.receiptTvPagadorValue)
        val barCode = view.findViewById<TextView>(R.id.receiptTvCodigoBarrasValue)
        val dueDate = view.findViewById<TextView>(R.id.receiptTvDataVencimentoValue)
        val paidDate = view.findViewById<TextView>(R.id.receiptTvDataPagamnetoValue)
        val value = view.findViewById<TextView>(R.id.receiptTvValorCobradoValue)
        val discount = view.findViewById<TextView>(R.id.receiptTvDescontosValue)
        val interest = view.findViewById<TextView>(R.id.receiptTvJurosValue)
        val fine = view.findViewById<TextView>(R.id.receiptTvMultaValue)
        val chargedValue = view.findViewById<TextView>(R.id.receiptTvValorDocumentoValue)
        val authenticationCode = view.findViewById<TextView>(R.id.receiptTvCodigoAutenticacaoValue)
        //val baseColor = view.findViewById<ImageView>(R.id.receipt)

        cedentName.text = params?.cedentName
        cnpj.text = params?.cnpj
        payerName.text = params?.payerName
        barCode.text = params?.barCode
        dueDate.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            .format(params?.dueDate as Date).toUpperCase(Locale.ROOT)
        paidDate.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            .format(params?.paidDate as Date).toUpperCase(Locale.ROOT)
        val nf = NumberFormat.getInstance(Locale("pt", "BR"))
        nf.minimumFractionDigits = 2
        value.text = nf.format(params?.value?.toDouble() ?: 0)
        discount.text = nf.format(params?.discount?.toDouble() ?: 0)
        interest.text = nf.format(params?.interest?.toDouble() ?: 0)
        fine.text = nf.format(params?.fine?.toDouble() ?: 0)
        chargedValue.text = nf.format(params?.chargedValue?.toDouble() ?: 0)
        authenticationCode.text = params?.authenticationCode

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(params: ReceiptParams) =
            ReceiptFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAMS, params)
                }
            }
    }
}