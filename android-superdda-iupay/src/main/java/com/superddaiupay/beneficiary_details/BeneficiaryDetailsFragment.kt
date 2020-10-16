package com.superddaiupay.beneficiary_details

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.superddaiupay.R
import com.superddaiupay.Utils
import com.superddaiupay.payment_history.PaymentHistoryAdapter
import com.superddaiupay.popups.BeneficiaryPopupFragment
import com.superddaiupay.popups.PopupParams
import kotlinx.android.synthetic.main.fragment_beneficiary_details.*
import java.util.logging.Logger

private const val ARG_PARAMS = "params"

class BeneficiaryDetailsFragment : Fragment() {
    private lateinit var params: BeneficiaryDetailsParams

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it.getSerializable(ARG_PARAMS) as BeneficiaryDetailsParams
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beneficiary_details, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val baseColor = Color.parseColor(params.baseColor ?: "#F78C49")
        val baseColorFilter = Utils.parseColorFilter(baseColor)
        beneficiaryDetailsBtnBack.setOnClickListener(params.onClickBack)
        beneficiaryDetailsBtnOptions.setOnClickListener(params.onClickOptions)
        beneficiaryDetailsAccessCard.setOnClickListener(params.onClickViewCard)
        val states = arrayOf(
            intArrayOf(android.R.attr.state_pressed),
            intArrayOf(android.R.attr.state_enabled)
        )
        val colors = intArrayOf(Utils.getColorWithAlpha(baseColor, 0.4f), baseColor)
        beneficiaryDetailsAccessCard.setTextColor(ColorStateList(states, colors))
        beneficiaryDetailsIvLogo.visibility = View.INVISIBLE
        params.data?.companyLogo.let {
            beneficiaryDetailsIvLogo.setImageBitmap(it)
            beneficiaryDetailsIvLogo.visibility = View.VISIBLE
            val logoLayout =
                beneficiaryDetailsIvLogo.layoutParams as ConstraintLayout.LayoutParams
            logoLayout.matchConstraintMaxWidth = 200
            logoLayout.matchConstraintMaxHeight = 150
        }
        if (params.data?.isFromIuPay == false) {
            beneficiaryDetailsIvIuPay.visibility = View.GONE
        }
        if (params.data?.isUserAdded!!) {
            beneficiaryDetailsIvUser.setImageResource(R.drawable.ic_user)
        } else {
            beneficiaryDetailsIvUser.setImageResource(R.drawable.ic_user_false)
            beneficiaryDetailsIvUser.colorFilter = Utils.parseColorFilter("#c1272d")
        }
        beneficiaryDetailsClFrame.setBackgroundColor(baseColor)
        beneficiaryDetailsTvLimiteAutorizacao.setTextColor(baseColor)
        beneficiaryDetailsTvLimiteAutorizacao.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        beneficiaryDetailsTvPagamentoAutomatico.setTextColor(baseColor)
        beneficiaryDetailsTvPagamentoAutomatico.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        beneficiaryDetailsTvCompanyName.text = params.data?.companyName
        beneficiaryDetailsTvCompanyName.setTextColor(baseColor)
        beneficiaryDetailsTvCnpj.text = params.data?.cnpj
        beneficiaryDetailsTvCartao.text = params.data?.cardNumber
        if (params.data?.autoPayment!!) {
            beneficiaryDetailsTvPagamentoAutomatico.text = "Ativado"
        } else {
            beneficiaryDetailsTvPagamentoAutomatico.text = "Desativado"
        }
        if (params.data?.authorizedLimit!!) {
            beneficiaryDetailsTvLimiteAutorizacao.text = "Ativado"
        } else {
            beneficiaryDetailsTvLimiteAutorizacao.text = "Desativado"
        }
        beneficiaryDetailsNome.text = params.data?.cardHolderName
        beneficiaryDetailsBtnDetails.setTextColor(baseColor)
        beneficiaryDetailsBtnDetails.background?.colorFilter = baseColorFilter
        beneficiaryDetailsBtnDetails.setOnClickListener {
            val popupParams = PopupParams()
            popupParams.title = "Detalhes do Beneficiário"
            popupParams.onClickClose = object : PopupParams.OnClickClose {
                override fun onClickClose() {
                    Logger.getLogger("BeneficiaryPopupFragment").info("Closed Click")
                }
            }
            val popupFragment =
                BeneficiaryPopupFragment.newInstance(popupParams, params)
            popupFragment.show(requireFragmentManager(), "missiles")
            params.onClickViewBeneficiaryDetails?.onClick(it)
        }
        beneficiaryDetailsRvHistory.layoutManager = LinearLayoutManager(requireContext())
        beneficiaryDetailsRvHistory.adapter = PaymentHistoryAdapter(
            requireContext(), params.data?.paymentHistory ?: ArrayList(),
            5,
            5
        )
        beneficiaryDetailsRvHistory.adapter?.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance(params: BeneficiaryDetailsParams) =
            BeneficiaryDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAMS, params)
                }
            }
    }
}