package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        val defaultCard = BeneficiaryCardParams.example(requireContext())
        val beneficiaryCards: List<BeneficiaryCardParams> = listOf(defaultCard)
        val beneficiaryCardListFragment = BeneficiaryCardListFragment.newInstance(12, 12, beneficiaryCards)

        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.beneficiaryFrameLayout, beneficiaryCardListFragment)
        fragmentTransaction.commit()
    }
}