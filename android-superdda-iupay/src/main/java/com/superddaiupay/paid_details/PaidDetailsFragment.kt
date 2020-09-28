package com.superddaiupay.paid_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.superddaiupay.R
import com.superddaiupay.receipt.ReceiptFragment

private const val ARG_PARAMS = "params"

class PaidDetailsFragment : Fragment() {
    private var params: PaidDetailsParams? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it.getSerializable(ARG_PARAMS) as PaidDetailsParams?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_paid_details, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(params: PaidDetailsParams) =
            PaidDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAMS, params)
                }
            }
    }
}