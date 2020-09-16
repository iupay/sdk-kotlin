package com.superddaiupay.cards

import android.graphics.*
import android.graphics.Color.parseColor
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.logging.Logger
import android.widget.TextView
import com.superddaiupay.R
import com.superddaiupay.R.*
import java.io.Serializable

private const val ARG_PARAMS = "params"

/**
 * A simple [Fragment] subclass.
 * Use the [CardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
        // Inflate the layout for this fragment
        val view = inflater.inflate(layout.fragment_card, container, false)

        Logger.getLogger(javaClass.toString()).info("Loading Card Fragment...")
        view.findViewById<TextView>(R.id.cardTitle).text = this.params?.cardTitle
        view.findViewById<TextView>(R.id.cnpj).text = this.params?.cnpj

        val drawable = (view.background as LayerDrawable)
        // COR DA BORDA DO CARD
        if (!this.params?.barColor.isNullOrEmpty()) {
            drawable.findDrawableByLayerId(R.id.card_left_border).colorFilter =
                PorterDuffColorFilter(parseColor(this.params?.barColor), PorterDuff.Mode.SRC_ATOP);
        }
        // COR DE FUNDO DO CARD
        if (!this.params?.cardColor.isNullOrEmpty()) {
            drawable.findDrawableByLayerId(R.id.card_center).colorFilter =
                PorterDuffColorFilter(parseColor(this.params?.cardColor), PorterDuff.Mode.SRC_ATOP)
        }

        // FUNCAO DE CALLBACK AO CLICAR NO CARD
        view.setOnClickListener { v -> this.params?.onClickCard?.onClick(v) }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param cardParams CardParams.
         * @return A new instance of fragment CardFragment.
         */
        @JvmStatic
        fun newInstance(cardParams: CardParams) =
            CardFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAMS, cardParams as Serializable)
                }
            }
    }
}