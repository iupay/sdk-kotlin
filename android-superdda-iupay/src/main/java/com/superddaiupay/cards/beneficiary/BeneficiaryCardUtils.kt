@file:Suppress("SpellCheckingInspection")

package com.superddaiupay.cards.beneficiary

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
import androidx.appcompat.widget.SwitchCompat
import com.superddaiupay.R
import com.superddaiupay.cards.CardType
import java.text.NumberFormat
import java.util.*


class BeneficiaryCardUtils {
    @SuppressLint("SetTextI18n")
    fun buildCard(context: Context, view: View, params: BeneficiaryCardParams?): View {
        val cardParams = params ?: BeneficiaryCardParams()

        val background = view.background as LayerDrawable
        val tvCnpjLabel = view.findViewById<TextView>(R.id.beneficiaryCardTvCnpjLabel)
        val tvTitle = view.findViewById<TextView>(R.id.beneficiaryCardTvTitle)
        val tvCnpjValue = view.findViewById<TextView>(R.id.beneficiaryCardTvCnpjValue)
        val tvLimitValueText = view.findViewById<TextView>(R.id.beneficiaryCardTvLimitValueText)
        val tvLimitValue = view.findViewById<TextView>(R.id.beneficiaryCardTvLimitValue)
        val moneySymbol = view.findViewById<TextView>(R.id.beneficiaryCardTvMoneySymbol)
        val tvType = view.findViewById<TextView>(R.id.beneficiaryCardTvType)
        val ivLogo = view.findViewById<ImageView>(R.id.beneficiaryCardIvLogo)
        val tvText =  view.findViewById<TextView>(R.id.beneficiaryCardTvText)
        val swCardActive = view.findViewById<SwitchCompat>(R.id.beneficiaryCardActive)

        // CARD TYPES
        if (CardType.NETFLIX == cardParams.type) {
            if (cardParams.logo == null) {
                ivLogo.setImageResource(R.drawable.ic_netflix)
            }
            if (cardParams.barColor.isNullOrEmpty()) {
                cardParams.barColor = "#D71921"
            }
        }

        if (CardType.LIGHTBILL == cardParams.type) {
            if (cardParams.logo == null) {
                ivLogo.setImageResource(R.drawable.ic_lightbill)
            }
            if (cardParams.barColor.isNullOrEmpty()) {
                cardParams.barColor = "#8aa626"
            }
        }

        if (CardType.NUBANK == cardParams.type) {
            if (cardParams.logo == null) {
                ivLogo.setImageBitmap(BitmapFactory.decodeResource(context.resources, R.raw.nubank))
                cardParams.imageWidth = 250
                cardParams.imageHeight = 100
            }
            if (cardParams.barColor.isNullOrEmpty()) {
                cardParams.barColor = "#8605b8"
            }
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
                PorterDuffColorFilter(
                    Color.parseColor(cardParams.barColor),
                    PorterDuff.Mode.SRC_ATOP
                )
        }

        // CNPJ
        if (!cardParams.cnpj.isNullOrEmpty()) {
            tvCnpjValue.text = cardParams.cnpj
        }

        //  CREDIT CARD
        if (!cardParams.creditCardText.isNullOrEmpty()) {
            tvCnpjValue.visibility = View.GONE
            tvCnpjLabel.text = cardParams.creditCardText
        }

        // TITLE
        if (!cardParams.cardTitle.isNullOrEmpty()) {
            tvTitle.text = cardParams.cardTitle
            tvTitle.visibility = View.VISIBLE
        }

        // LIMIT VALUE TEXT
        if (!cardParams.limitValueText.isNullOrEmpty()) {
            tvLimitValueText.text = "${cardParams.limitValueText}: "
        }

        // LIMIT VALUE
        if (cardParams.limitValue != null) {
            moneySymbol.visibility = View.VISIBLE
            val nf = NumberFormat.getInstance(Locale("pt", "BR"))
            nf.minimumFractionDigits = 2
            tvLimitValue.text = nf.format(cardParams.limitValue?.toDouble() ?: 0)
        }

        // BENEFICIARY TYPE
        if (!cardParams.beneficiaryType.isNullOrEmpty()) {
            tvType.text = cardParams.beneficiaryType
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

        // TEXT
        if (!cardParams.text.isNullOrEmpty()) {
            tvText.text = cardParams.text
        }

        // TEXT COLOR
        if (cardParams.textColor != null) {
            val color = Color.parseColor(cardParams.textColor)
            tvText.setTextColor(color)
        }

        // IS ACTIVE
        swCardActive.isChecked = cardParams.isActive

        // ACTIVE SWITCH CHANGES
        swCardActive.setOnCheckedChangeListener { buttonView, isChecked ->  cardParams.onActiveChange?.onCheckedChanged(buttonView, isChecked)}

        // FUNCAO DE CALLBACK AO CLICAR NO CARD
        view.setOnClickListener { v -> cardParams.onClickCard?.onClick(v) }
        return view
    }

    companion object {
        fun buildCard(context: Context, view: View, cardParams: BeneficiaryCardParams?): View =
            BeneficiaryCardUtils().buildCard(context, view, cardParams)
    }
}