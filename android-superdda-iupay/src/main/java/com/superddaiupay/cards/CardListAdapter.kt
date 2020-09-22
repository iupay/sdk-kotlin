package com.superddaiupay.cards

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.superddaiupay.R
import java.util.logging.Logger

class CardListAdapter(
    private val context: Context,
    private val cardParamsList: List<CardParams>,
    private val itemTopMargin: Int,
    private val itemBottomMargin: Int
) : RecyclerView.Adapter<CardListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Logger.getLogger(javaClass.toString()).info("Building Card List Item Adapter...")
        val cardParams: CardParams = cardParamsList[position]
        val view = CardUtils.buildCard(context, holder.itemView, cardParams)
        val layoutParams = (view.layoutParams as RecyclerView.LayoutParams)
        layoutParams.topMargin = itemTopMargin
        layoutParams.bottomMargin = itemBottomMargin
    }

    override fun getItemCount(): Int = cardParamsList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}