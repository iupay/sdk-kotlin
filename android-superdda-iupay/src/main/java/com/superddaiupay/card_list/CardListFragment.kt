package com.superddaiupay.card_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.superddaiupay.R
import com.superddaiupay.Utils
import java.io.Serializable
import java.util.logging.Logger

class CardListFragment : Fragment() {
    private lateinit var cardListParams: CardListParams

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            this.cardListParams = it.getSerializable(ARG_CARD_LIST_PARAMS) as CardListParams
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.getLogger(javaClass.toString()).info("Building Card List Fragment...")

        val cardList: RecyclerView = view.findViewById(R.id.cardList)
        val cardListCL: ConstraintLayout = view.findViewById(R.id.cardListCL)
        val cardListTotalVal: TextView = view.findViewById(R.id.cardListTotalVal)
        val cardListTotalPaymentText: TextView = view.findViewById(R.id.cardListTotalPaymentText)
        val layoutParams = cardList.layoutParams as ConstraintLayout.LayoutParams

        layoutParams.topMargin = 10

        cardList.adapter = CardListAdapter(
            requireContext(),
            cardListParams.cards
        )

        if (cardListParams.featured) {
            cardListCL.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.card_list_featured
                )
            )
        }
        cardListTotalPaymentText.text = cardListParams.totalPaymentText + ": R$ "

        if (cardListParams.totalDueOnly) {
            cardListTotalVal.text = Utils.formatMoney(cardListParams.getTotalDueOnlyValue())
        } else {
            cardListTotalVal.text = Utils.formatMoney(cardListParams.getTotalValue())
        }
    }

    companion object {
        const val ARG_CARD_LIST_PARAMS = "cardListParams"

        @JvmStatic
        fun newInstance(
            cardListParams: CardListParams
        ) =
            CardListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CARD_LIST_PARAMS, cardListParams as Serializable)
                }
            }
    }
}