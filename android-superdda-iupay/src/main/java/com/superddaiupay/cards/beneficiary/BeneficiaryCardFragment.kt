@file:Suppress("unused")

package com.superddaiupay.cards.beneficiary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.superddaiupay.R
import java.io.Serializable
import java.util.logging.Logger

private const val ARG_PARAMS = "params"

class BeneficiaryCardFragment : Fragment() {
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
        Logger.getLogger(javaClass.toString()).info("Building Beneficiary Card Fragment...")
        val view = inflater.inflate(R.layout.fragment_beneficiary_card, container, false)
        BeneficiaryCardUtils.buildCard(requireContext(), view, this.params)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(beneficiaryCardParams: BeneficiaryCardParams) =
            BeneficiaryCardFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAMS, beneficiaryCardParams as Serializable)
                }
            }
    }
}