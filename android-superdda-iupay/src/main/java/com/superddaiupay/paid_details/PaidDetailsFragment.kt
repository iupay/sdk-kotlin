package com.superddaiupay.paid_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.superddaiupay.R
import com.superddaiupay.receipt.ReceiptFragment
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAMS = "params"

class PaidDetailsFragment : Fragment() {
    private var params: PaidDetailsParams? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it.getSerializable(ARG_PARAMS) as PaidDetailsParams?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_paid_details, container, false)

        val beneficiaryName = view.findViewById<TextView>(R.id.paidTvBeneficiary)
        val paidDate = view.findViewById<TextView>(R.id.paidTvDate)
        val screenTitle = view.findViewById<TextView>(R.id.paidTvTitle)
        //val screenImage = view.findViewById<ImageView>(R.id.paidIvLogo)
        val value = view.findViewById<TextView>(R.id.paidTvValue)
        val dueDate = view.findViewById<TextView>(R.id.paidTvDueDate)
        //val baseColor = view.findViewById<ImageView>(R.id.paid
        //val receiptAvailable = view.findViewById<TextView>(R.id.paid
        val paymentMessage= view.findViewById<TextView>(R.id.paidTvPaymentMessage)

        beneficiaryName.text = params?.beneficiaryName
        paidDate.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            .format(params?.paidDate as Date).toUpperCase(Locale.ROOT)
        screenTitle.text = params?.screenTitle
        val nf = NumberFormat.getInstance(Locale("pt", "BR"))
        nf.minimumFractionDigits = 2
        value.text = nf.format(params?.value?.toDouble() ?: 0)
        dueDate.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                .format(params?.dueDate as Date).toUpperCase(Locale.ROOT)
        paymentMessage.text = params?.paymentMessage

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(params: PaidDetailsParams) =
            PaidDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAMS, params)
                }
            }
    }
}