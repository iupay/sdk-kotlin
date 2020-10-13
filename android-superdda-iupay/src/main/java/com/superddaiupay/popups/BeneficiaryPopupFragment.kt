package com.superddaiupay.popups

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.superddaiupay.R
import com.superddaiupay.beneficiary_details.BeneficiaryDetailsParams
import java.text.NumberFormat
import java.util.*

private const val ARG_PARAMS = "params"
private const val ARG_PARAMS2 = "beneficiaryDetailsParams"

class BeneficiaryPopupFragment : DialogFragment() {
    private lateinit var params: PopupParams
    private lateinit var beneficiaryDetailsParams: BeneficiaryDetailsParams
    private lateinit var title: TextView
    private lateinit var close: ImageButton
    private lateinit var beneficiary: TextView
    private lateinit var cnpj: TextView
    private lateinit var card: TextView
    private lateinit var name: TextView
    private lateinit var address: TextView
    private lateinit var rate1: TextView
    private lateinit var rate2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it.getSerializable(ARG_PARAMS) as PopupParams
            beneficiaryDetailsParams = it.getSerializable(ARG_PARAMS2) as BeneficiaryDetailsParams
        }
        setStyle(STYLE_NO_TITLE, R.style.my_dialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beneficiary_popup, container, false)
    }

    override fun onResume() {
        super.onResume()
        if (showsDialog) {
            dialog?.window?.apply {
                setBackgroundDrawable(ColorDrawable(Color.WHITE))
                attributes.gravity = Gravity.CENTER
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title = view.findViewById(R.id.beneficiaryPopupTvTitle)
        close = view.findViewById(R.id.beneficiaryPopupBtnClose)
        beneficiary = view.findViewById(R.id.beneficiaryPopupBeneficiary)
        cnpj = view.findViewById(R.id.beneficiaryPopupCnpj)
        name = view.findViewById(R.id.beneficiaryPopupName)
        card = view.findViewById(R.id.beneficiaryPopupCard)
        address = view.findViewById(R.id.beneficiaryPopupAddress)
        rate1 = view.findViewById(R.id.beneficiaryPopupRate)
        rate2 = view.findViewById(R.id.beneficiaryPopupRate2)

        title.text = params.title ?: ""
        close.setOnClickListener {
            dismiss()
            params.onClickClose?.onClickClose()
        }
        beneficiary.text = beneficiaryDetailsParams.data?.companyName
        beneficiary.setTextColor(Color.parseColor(beneficiaryDetailsParams.baseColor ?: "#727272"))
        cnpj.text = beneficiaryDetailsParams.data?.cnpj
        name.text = beneficiaryDetailsParams.data?.cardHolderName
        card.text = beneficiaryDetailsParams.data?.cardNumber
        address.text = beneficiaryDetailsParams.data?.cardHolderAddress
        val nf = NumberFormat.getInstance(Locale("pt", "BR"))
        nf.minimumFractionDigits = 2
        rate1.text = nf.format(
            beneficiaryDetailsParams.data?.billDetails?.interestRate?.toDouble() ?: 0
        ) + "% am CET: " +
                nf.format(
                    beneficiaryDetailsParams.data?.billDetails?.interestRateCET?.toDouble() ?: 0
                ) + "% aa"
        rate2.text = "consulte o app na contratação juros e mora em caso de atraso: " +
                nf.format(
                    beneficiaryDetailsParams.data?.billDetails?.interestInstallmentRate?.toDouble()
                        ?: 0
                ) + "% am +" +
                nf.format(
                    beneficiaryDetailsParams.data?.billDetails?.interestInstallmentFine?.toDouble()
                        ?: 0
                ) + "% multa CET:" +
                nf.format(
                    beneficiaryDetailsParams.data?.billDetails?.interestInstallmentRateCET?.toDouble()
                        ?: 0
                ) + "% aa"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    companion object {
        @JvmStatic
        fun newInstance(params: PopupParams, beneficiaryDetailsParams: BeneficiaryDetailsParams) =
            BeneficiaryPopupFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAMS, params)
                    putSerializable(ARG_PARAMS2, beneficiaryDetailsParams)
                }
            }
    }
}