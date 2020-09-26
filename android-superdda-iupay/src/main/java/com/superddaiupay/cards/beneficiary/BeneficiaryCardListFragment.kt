package com.superddaiupay.cards.beneficiary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.superddaiupay.R
import java.io.Serializable
import java.util.*
import java.util.logging.Logger

class BeneficiaryCardListFragment : Fragment() {

    private var beneficiaryCardParamsList: List<BeneficiaryCardParams> = ArrayList()
    private var itemTopMargin: Int = 0
    private var itemBottomMargin: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            @Suppress("UNCHECKED_CAST")
            this.beneficiaryCardParamsList = it.getSerializable(ARG_CARD_PARAMS_LIST) as List<BeneficiaryCardParams>
            this.itemTopMargin = it.getInt(ARG_ITEM_TOP_MARGIN)
            this.itemBottomMargin = it.getInt(ARE_ITEM_BOTTOM_MARGIN)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Logger.getLogger(javaClass.toString()).info("Building Beneficiary Card List Fragment...")
        val view = inflater.inflate(R.layout.fragment_beneficiary_card_list, container, false)
        view.findViewById<RecyclerView>(R.id.beneficiaryCardList).adapter = BeneficiaryCardListAdapter(
            requireContext(),
            beneficiaryCardParamsList,
            itemTopMargin,
            itemBottomMargin
        )
        return view
    }

    companion object {
        const val ARG_CARD_PARAMS_LIST = "beneficiaryCardParamsList"
        const val ARG_ITEM_TOP_MARGIN = "itemTopMargin"
        const val ARE_ITEM_BOTTOM_MARGIN = "itemBottomMargin"

        @JvmStatic
        fun newInstance(
            itemTopMargin: Int, itemBottomMargin: Int, beneficiaryCardParamsList: List<BeneficiaryCardParams>
        ) =
            BeneficiaryCardListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CARD_PARAMS_LIST, beneficiaryCardParamsList as Serializable)
                    putInt(ARG_ITEM_TOP_MARGIN, itemTopMargin)
                    putInt(ARE_ITEM_BOTTOM_MARGIN, itemBottomMargin)
                }
            }
    }
}