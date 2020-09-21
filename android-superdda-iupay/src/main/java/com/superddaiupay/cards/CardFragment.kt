package com.superddaiupay.cards

import android.graphics.Color
import android.graphics.Color.parseColor
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import com.superddaiupay.R
import com.superddaiupay.R.layout
import java.io.Serializable
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Logger

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
        Logger.getLogger(javaClass.toString()).info("Loading Card Fragment...")
        // Inflate the layout for this fragment
        val view = inflater.inflate(layout.fragment_card, container, false)

        val background = view.background as LayerDrawable
        val ivLogo = view.findViewById<ImageView>(R.id.ivLogo)
        val cardTitle = view.findViewById<TextView>(R.id.cardTitle)
        val cnpjLabel = view.findViewById<TextView>(R.id.cnpjLabel)
        val cnpj = view.findViewById<TextView>(R.id.cnpj)
        val textBody = view.findViewById<TextView>(R.id.textBody)
        val ivIcon1 = view.findViewById<ImageView>(R.id.ivIcon1)
        val ivIcon2 = view.findViewById<ImageView>(R.id.ivIcon2)
        val dueDate = view.findViewById<TextView>(R.id.dueDate)
        val isPaid = view.findViewById<TextView>(R.id.isPaid)
        val moneySymbol = view.findViewById<TextView>(R.id.moneySymbol)
        val value = view.findViewById<TextView>(R.id.value)

        // CARD TYPES
        if (CardParams.CardType.NETFLIX == this.params?.type) {
            if (this.params?.logo == null) {
                this.params?.logo = R.drawable.ic_netflix
            }
            if (this.params?.barColor.isNullOrEmpty()) {
                this.params?.barColor = "#D71921"
            }
        }


        // COR DE FUNDO DO CARD
        if (!this.params?.cardColor.isNullOrEmpty()) {
            background.findDrawableByLayerId(R.id.card_center).colorFilter =
                PorterDuffColorFilter(parseColor(this.params?.cardColor), PorterDuff.Mode.SRC_ATOP)
        }

        // DUE DATE
        dueDate.text =
            if (this.params?.dueDate == null) {
                ""
            } else {
                SimpleDateFormat(
                    "dd MMM",
                    Locale.getDefault()
                ).format(this.params!!.dueDate as Date).toUpperCase()
            }


        // COR DA BORDA DO CARD
        if (!this.params?.barColor.isNullOrEmpty()) {
            background.findDrawableByLayerId(R.id.card_left_border).colorFilter =
                PorterDuffColorFilter(parseColor(this.params?.barColor), PorterDuff.Mode.SRC_ATOP);
        }

        // CNPJ
        cnpj.text = this.params?.cnpj

        // TITLE
        if (!this.params?.cardTitle.isNullOrEmpty()) {
            cardTitle.text = this.params?.cardTitle
            cardTitle.visibility = View.VISIBLE
        }

        // TEXT
        textBody.text = this.params?.text

        // VALUE
        val nf = NumberFormat.getInstance(Locale("pt", "BR"))
        nf.minimumFractionDigits = 2
        value.text = nf.format(this.params?.value?.toDouble() ?: 0)

        // ICONS EMAIL AND USER
        ivIcon1.visibility = View.VISIBLE
        ivIcon2.visibility = View.INVISIBLE
        val email = R.drawable.ic_mail
        val user = R.drawable.ic_user
        val userFalse = R.drawable.ic_user_false
        if (this.params?.isFromMail == true) {
            ivIcon1.setImageResource(email)
            ivIcon2.visibility = View.VISIBLE
            if (this.params?.isUserAdded == true) {
                ivIcon2.setImageResource(user)
            } else {
                ivIcon2.setImageResource(userFalse)
            }
        } else {
            if (this.params?.isUserAdded == true) {
                ivIcon1.setImageResource(user)
            } else {
                ivIcon1.setImageResource(userFalse)
            }
        }

        // LOGO, IMAGE WIDTH AND IMAGE HEIGHT
        if (this.params?.logo != null) {
            ivLogo.setImageResource(this.params?.logo!!)
            ivLogo.visibility = View.VISIBLE
        }
        if (ivLogo.visibility == View.VISIBLE) {
            if (this.params?.imageWidth != null) {
                ivLogo.layoutParams.width = this.params?.imageWidth!!
            }
            if (this.params?.imageHeight != null) {
                ivLogo.layoutParams.height = this.params?.imageHeight!!
            }
        }

        // TEXT COLOR
        if (this.params?.textColor != null) {
            val color = parseColor(this.params?.textColor)
            textBody.setTextColor(color)
        }

        // IS DUE TEXT AND IS DUE
        if (this.params?.isDue == true) {
            val color = Color.rgb(227, 6, 19)
            dueDate.setTextColor(color)
            dueDate.text = this.params?.isDueText + ", " + dueDate.text
        }

        // IS PAID
        isPaid.visibility = if (this.params?.isPaid == true) View.VISIBLE else View.INVISIBLE

        // LIGHT BILL FLAG
        if (CardParams.CardType.LIGHTBILL == this.params?.type
            && this.params?.lightBillFlagStatus != null
        ) {
            val color = parseColor(this.params?.lightBillFlagStatus?.color)
            textBody.setTextColor(color)
        }

        // IS LOCKED
        if (this.params?.isLocked == true) {
            ivLogo.visibility = View.VISIBLE
            ivLogo.setImageResource(R.drawable.ic_lock)
            if (this.params?.imageWidth == null) {
                ivLogo.layoutParams.width = 120
            }
            if (this.params?.imageHeight == null) {
                ivLogo.layoutParams.height = 120
            }

            cardTitle.visibility = View.VISIBLE
            cardTitle.text = CardParams.LOCKED_TEXT
            cardTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.card_lock_text))
            val cardTitleParams = (cardTitle.layoutParams as ConstraintLayout.LayoutParams)
            cardTitleParams.topMargin = 80
            cardTitleParams.leftMargin = 30

            cnpjLabel.visibility = View.VISIBLE
            val cardLockedFieldsColor =
                ContextCompat.getColor(requireContext(), R.color.card_locked_fields)
            cnpjLabel.setBackgroundColor(cardLockedFieldsColor)
            cnpjLabel.width = 200
            cnpjLabel.text = ""

            textBody.visibility = View.VISIBLE
            textBody.setBackgroundColor(cardLockedFieldsColor)
            textBody.width = 250
            textBody.text = ""

            ivIcon1.visibility = View.VISIBLE
            ivIcon1.setBackgroundColor(cardLockedFieldsColor)
            ivIcon1.setImageDrawable(null)
            ivIcon1.layoutParams.width = 150

            dueDate.visibility = View.VISIBLE
            dueDate.setBackgroundColor(cardLockedFieldsColor)
            dueDate.width = 120
            dueDate.text = ""

            value.visibility = View.VISIBLE
            value.setBackgroundColor(cardLockedFieldsColor)
            value.width = 90
            value.text = ""

            cnpj.visibility = View.GONE
            ivIcon2.visibility = View.GONE
            isPaid.visibility = View.GONE
            moneySymbol.visibility = View.GONE
        }

        // FUNCAO DE CALLBACK AO CLICAR NO CARD
        view.setOnClickListener { v -> this.params?.onClickCard?.onClick(v) }
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