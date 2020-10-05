package com.superddaiupay.filter_searches

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.superddaiupay.R

private const val ARG_PARAMS = "params"

class FilterSearchesFragment : Fragment() {
    private lateinit var params: FilterSearchesParams

    private lateinit var filterTvLabel: TextView
    private lateinit var filterBtnAsc: Button
    private lateinit var filterBtnDesc: Button
    private lateinit var filterBtnSearch: ImageButton
    private lateinit var filterEtValue: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params = it.getSerializable(ARG_PARAMS) as FilterSearchesParams
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filter_searches, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filterTvLabel = view.findViewById(R.id.filterTvLabel)
        filterBtnAsc = view.findViewById(R.id.filterBtnAsc)
        filterBtnDesc = view.findViewById(R.id.filterBtnDesc)
        filterBtnSearch = view.findViewById(R.id.filterBtnSearch)
        filterEtValue = view.findViewById(R.id.filterEtValue)

        filterTvLabel.text =
            params.orderText ?: requireContext().getText(R.string.filter_ordenar_por)
        filterEtValue.setText(params.searchValue ?: "", TextView.BufferType.EDITABLE)
        filterBtnAsc.setOnClickListener { v -> params.onClickAsc?.onClick(v) }
        filterBtnDesc.setOnClickListener { v -> params.onClickDesc?.onClick(v) }
        filterBtnSearch.setOnClickListener { v ->
            filterEtValue.visibility =
                if (View.INVISIBLE == filterEtValue.visibility) View.VISIBLE else View.INVISIBLE
            params.onSearchIconClick?.onClick(v)
        }
        filterEtValue.visibility = View.INVISIBLE
        filterEtValue.hint = ""
        filterEtValue.isEnabled = !params.disabled
        filterEtValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                params.onChangeSearchValue?.onChangeSearchValue(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        filterEtValue.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                params.onSearch?.onSearch(filterEtValue.text.toString())
                true
            } else false
        }

        onPressButton(filterBtnAsc)
        onPressButton(filterBtnDesc)
    }

    @SuppressLint("ClickableViewAccessibility")
    fun onPressButton(button: Button) {
        button.setOnTouchListener { _, e ->
            button.setTextColor(Color.parseColor(if (MotionEvent.ACTION_DOWN == e.action) "#90707070" else "#707070"))
            false
        }
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