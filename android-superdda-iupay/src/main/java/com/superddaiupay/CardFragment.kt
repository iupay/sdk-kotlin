package com.superddaiupay

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
import com.superddaiupay.R.*

// TODO: Rename parameter arguments, choose names that match
private const val ARG_DUE_DATE = "dueDate"
private const val ARG_BAR_COLOR = "barColor"
private const val ARG_CNPJ = "cnpj"
private const val ARG_CARD_TITLE = "cardTitle"
private const val ARG_CARD_COLOR = "cardColor"

private const val ARG_TEXT = "text"
private const val ARG_VALUE = "value"
private const val ARG_IS_FROM_EMAIL = "isFromEmail"

/**
 * A simple [Fragment] subclass.
 * Use the [CardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CardFragment : Fragment() {
    private var cardTitle: String? = null
    private var cnpj: String? = null
    private var barColor: String? = null
    private var cardColor: String? = null

    private lateinit var callback: View.OnClickListener

    fun setOnClickListener(callback: View.OnClickListener) {
        this.callback = callback
    }
    interface OnClickCardListener {
        fun onClickCard(position: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cardTitle = it.getString(ARG_CARD_TITLE)
            cnpj = it.getString(ARG_CNPJ)
            barColor = it.getString(ARG_BAR_COLOR)
            cardColor = it.getString(ARG_CARD_COLOR)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(layout.fragment_card, container, false)

        Logger.getLogger(javaClass.toString()).info("Loading Card Fragment...")
        view.findViewById<TextView>(R.id.cardTitle).text = this.cardTitle
        view.findViewById<TextView>(R.id.cnpj).text = this.cnpj
        val drawable = (view.background as LayerDrawable)
        if (!this.barColor.isNullOrEmpty()) {
            drawable.findDrawableByLayerId(R.id.card_left_border).colorFilter =
                PorterDuffColorFilter(parseColor(this.barColor), PorterDuff.Mode.SRC_ATOP);
        }
        if (!this.cardColor.isNullOrEmpty()) {
            drawable.findDrawableByLayerId(R.id.card_center).colorFilter =
                PorterDuffColorFilter(parseColor(this.cardColor), PorterDuff.Mode.SRC_ATOP)
        }

        view.setOnClickListener { v ->
            this.callback.onClick(v);
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CardFragment.
         */
        @JvmStatic
        fun newInstance(cardTitle: String, cnpj: String, barColor: String, cardColor: String) =
            CardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CARD_TITLE, cardTitle)
                    putString(ARG_CNPJ, cnpj)
                    putString(ARG_BAR_COLOR, barColor)
                    putString(ARG_CARD_COLOR, cardColor)
                }
            }
    }
}