@file:Suppress("unused", "SpellCheckingInspection")

package com.superddaiupay.card_list

import android.content.Context
import com.superddaiupay.cards.CardParams
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class CardListParams : Serializable {
    var cards: ArrayList<CardParams> = ArrayList()
    var featured: Boolean = false
    var featuredBackgroundColor: String = "#f78c49"
    var showTotal: Boolean = true
    var totalPaymentText: String = "Valor total dos pagamentos que vencem hoje"
    var totalDueOnly: Boolean = false

    fun getTotalValue(): Number {
        return cards.map { it.value }.sumByDouble { it?.toDouble() ?: 0.0 }
    }

    fun getTotalDueOnlyValue(): Number {
        return cards.filter { it.dueDate != null && Calendar.getInstance().time == it.dueDate!! }
            .map { it.value }.sumByDouble { it?.toDouble() ?: 0.0 }
    }

    companion object {
        @JvmStatic
        fun example(context: Context): CardListParams {
            val params = CardListParams()
            params.cards = arrayListOf(
                CardParams.embasaCard(context),
                CardParams.bmwCard(context),
                CardParams.rennerCard(context),
                CardParams.customCard(context),
                CardParams.lockedCard()
            )
            params.featured = true
            params.featuredBackgroundColor = "#f78c49"
            params.showTotal = true
            params.totalPaymentText = "Valor total dos pagamentos que vencem hoje"
            params.totalDueOnly = false
            return params
        }
    }
}