package com.superddaiupay.month_selector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.superddaiupay.R
import com.superddaiupay.receipt.ReceiptFragment

private const val ARG_PARAMS = "params"

class MonthSelectorFragment : Fragment() {
    private var params: MonthSelectorParams? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it.getSerializable(ARG_PARAMS) as MonthSelectorParams?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_month_selector, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(params: MonthSelectorParams) =
            ReceiptFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAMS, params)
                }
            }
    }
}