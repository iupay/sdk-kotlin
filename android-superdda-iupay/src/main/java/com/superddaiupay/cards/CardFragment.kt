package com.superddaiupay.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.superddaiupay.R.layout
import java.io.Serializable
import java.util.logging.Logger

private const val ARG_PARAMS = "params"

class CardFragment : Fragment() {
    private var params: CardParams? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it.getSerializable(ARG_PARAMS) as CardParams?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Logger.getLogger(javaClass.toString()).info("Building Card Fragment...")
        val view = inflater.inflate(layout.fragment_card, container, false)
        CardUtils.buildCard(requireContext(), view, this.params)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(cardParams: CardParams) =
            CardFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAMS, cardParams as Serializable)
                }
            }
    }
}