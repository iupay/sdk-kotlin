package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.superddaiupay.cards.beneficiary.BeneficiaryCardListFragment
import com.superddaiupay.cards.beneficiary.BeneficiaryCardParams

class SecondFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val defaultCard = BeneficiaryCardParams.example(requireContext())
        val beneficiaryCards: List<BeneficiaryCardParams> = listOf(defaultCard)
        val beneficiaryCardListFragment = BeneficiaryCardListFragment.newInstance(12, 12, beneficiaryCards)

        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.beneficiaryFrameLayout, beneficiaryCardListFragment)
        fragmentTransaction.commit()
    }
}