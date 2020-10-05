package com.superddaiupay.filter_searches

import android.view.View
import com.superddaiupay.account_details.AccountDetailsParams
import com.superddaiupay.account_details.ChartData
import java.io.Serializable
import java.util.logging.Logger

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
        fun onSearch(text: String)
    }

    interface ChangeSearchValue {
        fun onChangeSearchValue(text: String)
    }

    companion object {
        @JvmStatic
        fun example(): FilterSearchesParams {
            val params = FilterSearchesParams()
            params.orderText = "Ordernar por: "
            params.disabled = false
            params.searchValue = ""
            params.onClickAsc = View.OnClickListener { Logger.getLogger("FilterSearchesParams").info("On Click Asc!!!") }
            params.onClickDesc = View.OnClickListener { Logger.getLogger("FilterSearchesParams").info("On Click Desc!!!") }
            params.onSearchIconClick = View.OnClickListener { Logger.getLogger("FilterSearchesParams").info("On Search Icon Click!!!") }
            params.onSearch = object : SearchClickListener {
                override fun onSearch(text: String) {
                    Logger.getLogger("FilterSearchesParams").info("On Search: $text")
                }
            }
            params.onChangeSearchValue = object : ChangeSearchValue {
                override fun onChangeSearchValue(text: String) {
                    Logger.getLogger("FilterSearchesParams").info("On Change Search Value: $text")
                }
            }
            return params
        }
    }
}
