package com.superddaiupay.popup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.superddaiupay.R

private const val ARG_PARAMS = "params"

class PopupFragment : Fragment() {
    private var params: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it.getString(ARG_PARAMS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popup, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(params: String) =
            PopupFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAMS, params)
                }
            }
    }
}