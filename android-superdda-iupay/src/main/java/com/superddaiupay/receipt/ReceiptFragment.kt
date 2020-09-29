package com.superddaiupay.receipt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.superddaiupay.R

private const val ARG_PARAMS = "params"

class ReceiptFragment : Fragment() {
    private var params: ReceiptParams? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it.getSerializable(ARG_PARAMS) as ReceiptParams?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_receipt, container, false)

        val cnpj = view.findViewById<TextView>(R.id.receiptTvCnpjValue)

        cnpj.text = params?.cnpj

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(params: ReceiptParams) =
            ReceiptFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAMS, params)
                }
            }
    }
}