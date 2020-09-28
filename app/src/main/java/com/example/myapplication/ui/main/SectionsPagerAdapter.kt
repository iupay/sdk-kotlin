package com.example.myapplication.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.R
import com.superddaiupay.payment_details.PaymentDetailsFragment
import com.superddaiupay.payment_details.PaymentDetailsParams
import com.superddaiupay.receipt.ReceiptFragment
import com.superddaiupay.receipt.ReceiptParams

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3,
    R.string.tab_text_4
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            2 -> ReceiptFragment.newInstance(ReceiptParams())
            3 -> PaymentDetailsFragment.newInstance(PaymentDetailsParams())
            else -> FirstFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}