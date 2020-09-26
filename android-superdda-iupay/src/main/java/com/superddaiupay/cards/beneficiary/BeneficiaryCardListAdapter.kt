package com.superddaiupay.cards.beneficiary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.superddaiupay.R
import java.util.logging.Logger

class BeneficiaryCardListAdapter(
    private val context: Context,
    private val cardParamsList: List<BeneficiaryCardParams>,
    private val itemTopMargin: Int,
    private val itemBottomMargin: Int
) : RecyclerView.Adapter<BeneficiaryCardListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_beneficiary_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Logger.getLogger(javaClass.toString()).info("Building Card List Item Adapter...")
        val cardParams: BeneficiaryCardParams = cardParamsList[position]
        val view = BeneficiaryCardUtils.buildCard(context, holder.itemView, cardParams)
        val layoutParams = (view.layoutParams as RecyclerView.LayoutParams)
        layoutParams.topMargin = itemTopMargin
        layoutParams.bottomMargin = itemBottomMargin
    }

    override fun getItemCount(): Int = cardParamsList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}