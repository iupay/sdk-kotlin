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