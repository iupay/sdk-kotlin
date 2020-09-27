package com.superddaiupay.beneficiary_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.superddaiupay.R
import com.superddaiupay.cards.beneficiary.BeneficiaryCardParams
import com.superddaiupay.receipt.ReceiptFragment

private const val ARG_PARAMS = "params"

class BeneficiaryDetailsFragment : Fragment() {
    private var params: BeneficiaryCardParams? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it.getSerializable(ARG_PARAMS) as BeneficiaryCardParams?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beneficiary_details, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(params: BeneficiaryCardParams) =
            ReceiptFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAMS, params)
                }
            }
    }
}