package com.superddaiupay.month_selector

import java.io.Serializable

class MonthSelectorParams : Serializable {
    var currentMonth: Number? = null
    var monthScrollCenterOffset: Number? = null
    var onSelectMonth: OnSelectMonth? = null

    interface OnSelectMonth {
        fun onSelectMonth(month: Number)
    }
}