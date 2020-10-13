package com.superddaiupay.card_list

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.superddaiupay.R
import com.superddaiupay.Utils
import com.superddaiupay.cards.CardParams
import java.util.logging.Logger


class CardListAdapter(
    private val context: Context,
    private val cardParamsList: List<CardParams>
) : RecyclerView.Adapter<CardListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.fragment_card_list_item
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Logger.getLogger(javaClass.toString()).info("Building Card List Item Adapter...")
        val cardParams: CardParams = cardParamsList[position]
        val view = holder.itemView

        val cardCL: ConstraintLayout = view.findViewById(R.id.cardCL)
        val background = cardCL.background as LayerDrawable
        val ivLogo = view.findViewById<ImageView>(R.id.cardIvLogo)
        val cardTitle = view.findViewById<TextView>(R.id.cardTvTitle)
        val dueDate = view.findViewById<TextView>(R.id.cardTvDueDate)
        val isPaid = view.findViewById<TextView>(R.id.cardTvIsPaid)
        val moneySymbol = view.findViewById<TextView>(R.id.cardTvMoneySymbol)
        val value = view.findViewById<TextView>(R.id.cardTvValue)

        // MARGINS
        if (position > 0) {
            val layoutParams = (view.layoutParams as RecyclerView.LayoutParams)
            layoutParams.topMargin = -30
        }

        // COR DE FUNDO DO CARD
        if (!cardParams.cardColor.isNullOrEmpty()) {
            background.findDrawableByLayerId(R.id.card_center).colorFilter =
                PorterDuffColorFilter(
                    Color.parseColor(cardParams.cardColor),
                    PorterDuff.Mode.SRC_ATOP
                )
        }

        // COR DA BORDA DO CARD
        if (!cardParams.barColor.isNullOrEmpty()) {
            background.findDrawableByLayerId(R.id.card_left_border).colorFilter =
                Utils.parseColorFilter(cardParams.barColor!!)
        }

        // LOGO
        if (cardParams.logo != null) {
            ivLogo.setImageBitmap(cardParams.logo!!)
            ivLogo.visibility = View.VISIBLE
        }
        if (ivLogo.visibility == View.VISIBLE) {
            var logoLayout = ivLogo.layoutParams as ConstraintLayout.LayoutParams
            if (cardParams.imageWidth != null) {
                ivLogo.layoutParams.width = cardParams.imageWidth!!
            } else {
                logoLayout.matchConstraintMaxWidth = 200
            }
            if (cardParams.imageHeight != null) {
                ivLogo.layoutParams.height = cardParams.imageHeight!!
            } else {
                logoLayout.matchConstraintMaxHeight = 150
            }
        }

        // TITLE
        if (!cardParams.cardTitle.isNullOrEmpty()) {
            cardTitle.text = cardParams.cardTitle
            cardTitle.visibility = View.VISIBLE
        } else {
            cardTitle.visibility = View.INVISIBLE
        }

        // DUE DATE
        dueDate.text = Utils.formatDate(cardParams.dueDate, "EEEE").toLowerCase()
            .capitalize() + ", " + Utils.formatDate(cardParams.dueDate, "dd MMM")

        // VALUE
        value.text = Utils.formatMoney(cardParams.value)

        // IS PAID
        if (cardParams.isPaid) {
            isPaid.text = "PAGO"
            isPaid.setTextColor(ContextCompat.getColor(context, R.color.card_paid))
        } else {
            isPaid.text = "NÃO PAGO"
            isPaid.setTextColor(ContextCompat.getColor(context, R.color.card_not_paid))
        }

        // IS LOCKED
        if (cardParams.isLocked) {
            val cardLockedFieldsColor =
                ContextCompat.getColor(context, R.color.card_locked_fields)
            ivLogo.visibility = View.VISIBLE
            ivLogo.setImageResource(R.drawable.ic_lock)
            if (cardParams.imageWidth == null) {
                ivLogo.layoutParams.width = 120
            }
            if (cardParams.imageHeight == null) {
                ivLogo.layoutParams.height = 120
            }
            cardTitle.visibility = View.VISIBLE
            cardTitle.text = CardParams.LOCKED_TEXT
            cardTitle.setTextColor(ContextCompat.getColor(context, R.color.card_lock_text))
            val cardTitleParams = (cardTitle.layoutParams as ConstraintLayout.LayoutParams)
            cardTitleParams.topMargin = 80
            cardTitleParams.leftMargin = 30

            dueDate.visibility = View.VISIBLE
            dueDate.setBackgroundColor(cardLockedFieldsColor)
            dueDate.width = 160
            dueDate.text = ""

            isPaid.visibility = View.VISIBLE
            isPaid.setBackgroundColor(cardLockedFieldsColor)
            isPaid.width = 100
            isPaid.text = ""

            value.visibility = View.VISIBLE
            value.setBackgroundColor(cardLockedFieldsColor)
            value.width = 200
            value.height = 50
            value.text = ""

            moneySymbol.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = cardParamsList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}