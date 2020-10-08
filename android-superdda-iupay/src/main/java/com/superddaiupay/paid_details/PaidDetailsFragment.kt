package com.superddaiupay.paid_details

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.superddaiupay.R
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
        val screenImage = view.findViewById<ImageView>(R.id.paidIvLogo)
        val value = view.findViewById<TextView>(R.id.paidTvValue)
        val dueDate = view.findViewById<TextView>(R.id.paidTvDueDate)
        val clMessage = view.findViewById<ConstraintLayout>(R.id.paidCLMessage)
        val btnViewReceipt = view.findViewById<Button>(R.id.paidBtnViewReceipt)
        val paymentMessage = view.findViewById<TextView>(R.id.paidTvPaymentMessage)

        beneficiaryName.text = params?.beneficiaryName
        paidDate.text = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
            .format(params?.paidDate as Date).toUpperCase(Locale.ROOT)
        screenTitle.text = params?.screenTitle
        if (params?.screenImage != null) {
            screenImage.setImageBitmap(params!!.screenImage)
            screenImage.layoutParams.height = 150
            screenImage.layoutParams.width = 150
        }
        val nf = NumberFormat.getInstance(Locale("pt", "BR"))
        nf.minimumFractionDigits = 2
        value.text = nf.format(params?.value?.toDouble() ?: 0)
        dueDate.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            .format(params?.dueDate as Date).toUpperCase(Locale.ROOT)
        paymentMessage.text = params?.paymentMessage

        if (params?.baseColor != null) {
            clMessage.background?.colorFilter = PorterDuffColorFilter(
                Color.parseColor(params!!.baseColor), PorterDuff.Mode.SRC_ATOP
            )
            paymentMessage.setTextColor(ColorStateList.valueOf(Color.parseColor(params?.baseColor)))
            btnViewReceipt.background?.colorFilter = PorterDuffColorFilter(
                Color.parseColor(params!!.baseColor), PorterDuff.Mode.SRC_ATOP
            )
            btnViewReceipt.setTextColor(ColorStateList.valueOf(Color.parseColor(params?.baseColor)))
        }

        if (!params?.receiptAvailable!!) {
            btnViewReceipt.isClickable = false
            btnViewReceipt.isEnabled = false
            btnViewReceipt.background?.colorFilter = PorterDuffColorFilter(
                Color.parseColor("#e8e8e8"), PorterDuff.Mode.SRC_ATOP
            )
            btnViewReceipt.setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")))
        }

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