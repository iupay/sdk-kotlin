package com.superddaiupay.filter_searches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.superddaiupay.R
import com.superddaiupay.receipt.ReceiptFragment

private const val ARG_PARAMS = "params"

class FilterSearchesFragment : Fragment() {
    private var params: FilterSearchesParams? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it.getSerializable(ARG_PARAMS) as FilterSearchesParams?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filter_searches, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(params: FilterSearchesParams) =
            FilterSearchesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAMS, params)
                }
            }
    }
}