package com.superddaiupay.payment_history

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.superddaiupay.R
import com.superddaiupay.account_details.PaymentHistory
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Logger

class PaymentHistoryAdapter(
    private val context: Context,
    private val paymentHistory: List<PaymentHistory>,
    private val itemTopMargin: Int,
    private val itemBottomMargin: Int
) : RecyclerView.Adapter<PaymentHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_payment_history_item, parent, false)
        Logger.getLogger(javaClass.toString()).info("Building Payment History Adapter...")
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Logger.getLogger(javaClass.toString()).info("Building Payment History Item Adapter...")
        val item: PaymentHistory = paymentHistory[position]
        val view = holder.itemView
        val layoutParams = (view.layoutParams as RecyclerView.LayoutParams)
        layoutParams.topMargin = itemTopMargin
        layoutParams.bottomMargin = itemBottomMargin

        val phCl: ConstraintLayout = view.findViewById(R.id.phCl)
        val phDate: TextView = view.findViewById(R.id.phDate)
        val phIsOpen: TextView = view.findViewById(R.id.phIsOpen)
        val phValue: TextView = view.findViewById(R.id.phValue)

        phCl.background?.colorFilter =  PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
        if (item.date != null) {
            phDate.text = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
                .format(item.date!!).toUpperCase(Locale.ROOT)
        } else {
            phDate.text = ""
        }
        phIsOpen.visibility = if (item.isOpen) View.VISIBLE else View.GONE
        val nf = NumberFormat.getInstance(Locale("pt", "BR"))
        nf.minimumFractionDigits = 2
        phValue.text = nf.format(item.value ?: 0)
    }

    override fun getItemCount(): Int = paymentHistory.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}