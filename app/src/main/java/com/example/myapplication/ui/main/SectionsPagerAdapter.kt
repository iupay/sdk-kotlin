package com.example.myapplication.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.R
import com.superddaiupay.account_details.AccountDetailsFragment
import com.superddaiupay.account_details.AccountDetailsParams
import com.superddaiupay.beneficiary_details.BeneficiaryDetailsFragment
import com.superddaiupay.beneficiary_details.BeneficiaryDetailsParams
import com.superddaiupay.card_list.CardListFragment
import com.superddaiupay.card_list.CardListParams
import com.superddaiupay.filter_searches.FilterSearchesFragment
import com.superddaiupay.filter_searches.FilterSearchesParams
import com.superddaiupay.paid_details.PaidDetailsFragment
import com.superddaiupay.paid_details.PaidDetailsParams
import com.superddaiupay.payment_details.PaymentDetailsFragment
import com.superddaiupay.payment_details.PaymentDetailsParams
import com.superddaiupay.receipt.ReceiptFragment
import com.superddaiupay.receipt.ReceiptParams

private val TAB_TITLES = arrayOf(
    R.string.tab_account_details,
    R.string.tab_beneficiary_details,
    R.string.tab_card_list,
    R.string.tab_cards,
    R.string.tab_beneficiaries,
    R.string.tab_filter_searches,
    R.string.tab_paid_details,
    R.string.tab_payment_details,
    R.string.tab_receipt
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AccountDetailsFragment.newInstance(AccountDetailsParams.example(context))
            1 -> BeneficiaryDetailsFragment.newInstance(BeneficiaryDetailsParams.example())
            2 -> CardListFragment.newInstance(CardListParams.example(context))
            3 -> FirstFragment()
            4 -> SecondFragment()
            5 -> FilterSearchesFragment.newInstance(FilterSearchesParams.example())
            6 -> PaidDetailsFragment.newInstance(PaidDetailsParams.example(context))
            7 -> PaymentDetailsFragment.newInstance(PaymentDetailsParams.example())
            8 -> ReceiptFragment.newInstance(ReceiptParams.example())
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