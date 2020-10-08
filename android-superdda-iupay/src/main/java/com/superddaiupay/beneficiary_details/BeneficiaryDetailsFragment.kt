package com.superddaiupay.beneficiary_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.superddaiupay.R
import com.superddaiupay.popups.BeneficiaryPopupFragment
import com.superddaiupay.popups.PopupParams
import java.util.logging.Logger

private const val ARG_PARAMS = "params"

class BeneficiaryDetailsFragment : Fragment() {
    private var params: BeneficiaryDetailsParams? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it.getSerializable(ARG_PARAMS) as BeneficiaryDetailsParams?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beneficiary_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val companyName = view.findViewById<TextView>(R.id.beneficiaryDetailsTvCompanyName)
        val cnpj = view.findViewById<TextView>(R.id.beneficiaryDetailsTvCnpj)
        val cardNumber = view.findViewById<TextView>(R.id.beneficiaryDetailsTvCartao)
        val autoPayment = view.findViewById<TextView>(R.id.beneficiaryDetailsTvPagamentoAutomatico)
        val authorizedLimit =
            view.findViewById<TextView>(R.id.beneficiaryDetailsTvLimiteAutorizacao)
        val cardHolderName = view.findViewById<TextView>(R.id.beneficiaryDetailsNome)
        val btnDetails = view.findViewById<Button>(R.id.beneficiaryDetailsBtnDetails)
        val beneficiaryHistory = view.findViewById<RecyclerView>(R.id.beneficiaryDetailsRvHistory)

        companyName.text = params?.data?.companyName
        cnpj.text = params?.data?.cnpj
        cardNumber.text = params?.data?.cardNumber
        if (params?.data?.autoPayment!!) {
            autoPayment.text = "Ativado"
        } else {
            autoPayment.text = "Desativado"
        }
        if (params?.data?.authorizedLimit!!) {
            authorizedLimit.text = "Ativado"
        } else {
            authorizedLimit.text = "Desativado"
        }
        cardHolderName.text = params?.data?.cardHolderName

        btnDetails.setOnClickListener {
            val popupParams = PopupParams()
            popupParams.title = "Detalhes do Beneficiário"
            popupParams.onClickClose = object : PopupParams.OnClickClose {
                override fun onClickClose() {
                    Logger.getLogger("BeneficiaryPopupFragment").info("Closed Click")
                }
            }
            val popupFragment = BeneficiaryPopupFragment.newInstance(popupParams, params!!)
            popupFragment.show(requireFragmentManager(), "missiles")
        }
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