@file:Suppress("SpellCheckingInspection")

package com.superddaiupay.cards

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.LayerDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.superddaiupay.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


class CardUtils {
    @SuppressLint("SetTextI18n")
    fun buildCard(context: Context, view: View, params: CardParams?): View {
        val cardParams = params ?: CardParams()

        val background = view.background as LayerDrawable
        val ivLogo = view.findViewById<ImageView>(R.id.cardIvLogo)
        val cardTitle = view.findViewById<TextView>(R.id.cardTvTitle)
        val cnpjLabel = view.findViewById<TextView>(R.id.cardTvCnpjLabel)
        val cnpj = view.findViewById<TextView>(R.id.cardTvCnpjValue)
        val textBody = view.findViewById<TextView>(R.id.cardTvTextBody)
        val ivIcon1 = view.findViewById<ImageView>(R.id.cardIvIcon1)
        val ivIcon2 = view.findViewById<ImageView>(R.id.cardIvIcon2)
        val dueDate = view.findViewById<TextView>(R.id.cardTvDueDate)
        val isPaid = view.findViewById<TextView>(R.id.cardTvIsPaid)
        val moneySymbol = view.findViewById<TextView>(R.id.cardTvMoneySymbol)
        val value = view.findViewById<TextView>(R.id.cardTvValue)

        // CARD TYPES
        if (CardParams.CardType.NETFLIX == cardParams.type) {
            if (cardParams.logo == null) {
                ivLogo.setImageResource(R.drawable.ic_netflix)
            }
            if (cardParams.barColor.isNullOrEmpty()) {
                cardParams.barColor = "#D71921"
            }
        }

        if (CardParams.CardType.LIGHTBILL == cardParams.type) {
            if (cardParams.logo == null) {
                ivLogo.setImageResource(R.drawable.ic_lightbill)
            }
            if (cardParams.barColor.isNullOrEmpty()) {
                cardParams.barColor = "#8aa626"
            }
        }

        if (CardParams.CardType.NUBANK == cardParams.type) {
            if (cardParams.logo == null) {
                ivLogo.setImageBitmap(BitmapFactory.decodeResource(context.resources, R.raw.nubank))
                cardParams.imageWidth = 250
                cardParams.imageHeight = 100
            }
            if (cardParams.barColor.isNullOrEmpty()) {
                cardParams.barColor = "#8605b8"
            }
        }

        if (!cardParams.creditCardText.isNullOrEmpty()) {
            cnpj.visibility = View.GONE
            cnpjLabel.text = cardParams.creditCardText
        }

        // COR DE FUNDO DO CARD
        if (!cardParams.cardColor.isNullOrEmpty()) {
            background.findDrawableByLayerId(R.id.card_center).colorFilter =
                PorterDuffColorFilter(
                    Color.parseColor(cardParams.cardColor),
                    PorterDuff.Mode.SRC_ATOP
                )
        }

        // DUE DATE
        dueDate.text =
            if (cardParams.dueDate == null) {
                ""
            } else {
                SimpleDateFormat(
                    "dd MMM",
                    Locale.getDefault()
                ).format(cardParams.dueDate as Date).toUpperCase(Locale.ROOT)
            }


        // COR DA BORDA DO CARD
        if (!cardParams.barColor.isNullOrEmpty()) {
            background.findDrawableByLayerId(R.id.card_left_border).colorFilter =
                PorterDuffColorFilter(
                    Color.parseColor(cardParams.barColor),
                    PorterDuff.Mode.SRC_ATOP
                )
        }

        // CNPJ
        cnpj.text = cardParams.cnpj

        // TITLE
        if (!cardParams.cardTitle.isNullOrEmpty()) {
            cardTitle.text = cardParams.cardTitle
            cardTitle.visibility = View.VISIBLE
        }

        // TEXT
        textBody.text = cardParams.text

        // VALUE
        val nf = NumberFormat.getInstance(Locale("pt", "BR"))
        nf.minimumFractionDigits = 2
        value.text = nf.format(cardParams.value?.toDouble() ?: 0)

        // ICONS EMAIL AND USER
        ivIcon1.visibility = View.VISIBLE
        ivIcon2.visibility = View.INVISIBLE
        val email = R.drawable.ic_mail
        val user = R.drawable.ic_user
        val userFalse = R.drawable.ic_user_false
        if (cardParams.isFromMail) {
            ivIcon1.setImageResource(email)
            ivIcon2.visibility = View.VISIBLE
            if (cardParams.isUserAdded) {
                ivIcon2.setImageResource(user)
            } else {
                ivIcon2.setImageResource(userFalse)
            }
        } else {
            if (cardParams.isUserAdded) {
                ivIcon1.setImageResource(user)
            } else {
                ivIcon1.setImageResource(userFalse)
            }
        }

        // LOGO, IMAGE WIDTH AND IMAGE HEIGHT
        if (cardParams.logo != null) {
            ivLogo.setImageBitmap(cardParams.logo!!)
            ivLogo.visibility = View.VISIBLE
        }
        if (ivLogo.visibility == View.VISIBLE) {
            if (cardParams.imageWidth != null) {
                ivLogo.layoutParams.width = cardParams.imageWidth!!
            }
            if (cardParams.imageHeight != null) {
                ivLogo.layoutParams.height = cardParams.imageHeight!!
            }
        }

        // TEXT COLOR
        if (cardParams.textColor != null) {
            val color = Color.parseColor(cardParams.textColor)
            textBody.setTextColor(color)
        }

        // IS DUE TEXT AND IS DUE
        if (cardParams.isDue) {
            val color = Color.rgb(227, 6, 19)
            dueDate.setTextColor(color)
            dueDate.text = cardParams.isDueText.toString() + ", " + dueDate.text
        }

        // IS PAID
        isPaid.visibility = if (cardParams.isPaid) View.VISIBLE else View.INVISIBLE

        // LIGHT BILL FLAG
        if (CardParams.CardType.LIGHTBILL == cardParams.type
            && cardParams.lightBillFlagStatus != null
        ) {
            val color = Color.parseColor(cardParams.lightBillFlagStatus?.color)
            textBody.setTextColor(color)
        }

        // IS LOCKED
        if (cardParams.isLocked) {
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

            cnpjLabel.visibility = View.VISIBLE
            val cardLockedFieldsColor =
                ContextCompat.getColor(context, R.color.card_locked_fields)
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
        view.setOnClickListener { v -> cardParams.onClickCard?.onClick(v) }
        return view
    }

    companion object {
        fun buildCard(context: Context, view: View, cardParams: CardParams?): View =
            CardUtils().buildCard(context, view, cardParams)
    }
}