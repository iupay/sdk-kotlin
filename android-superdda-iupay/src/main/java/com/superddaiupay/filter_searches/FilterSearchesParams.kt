package com.superddaiupay.filter_searches

import android.view.View
import java.io.Serializable

class FilterSearchesParams: Serializable {

    var orderText: String? = null
    var disabled: Boolean = false
    var searchValue: String? = null
    var onClickAsc: View.OnClickListener? = null
    var onClickDesc: View.OnClickListener? = null
    var onSearchIconClick: View.OnClickListener? = null
    var onSearch: SearchClickListener? = null
    var onChangeSearchValue: ChangeSearchValue? = null

    interface SearchClickListener {
        fun onCellClickListener(text: String)
    }

    interface ChangeSearchValue {
        fun onChangeSearchValue(text: String)
    }
}
