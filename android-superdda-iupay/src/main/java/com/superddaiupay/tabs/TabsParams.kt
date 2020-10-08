package com.superddaiupay.tabs

import java.io.Serializable

class TabsParams : Serializable {
    var tabs: ArrayList<Tab>? = null
    var onClickTab: OnClickTab? = null

    interface OnClickTab {
        fun onClickTab()
    }
}