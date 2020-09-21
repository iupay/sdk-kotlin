package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.superddaiupay.cards.CardFragment
import com.superddaiupay.cards.CardParams
import java.util.*
import java.util.logging.Logger

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val params = CardParams()
        params.cardColor = "#FFFFFF"
        params.dueDate = Calendar.getInstance().time
        params.barColor = "#D71921"
        params.cnpj = "99.9999.999.0001-99"
        params.cardTitle = "Teste Titulo"
        params.text = "Teste Texto"
        params.value = 1500.5
        params.isFromMail = true
        params.isUserAdded = false
        params.type = CardParams.CardType.NETFLIX
        params.isLocked = true

        val testFrameFragment = CardFragment.newInstance(params)
        params.onClickCard = onClickCard()
        fragmentTransaction.add(R.id.frameLayout, testFrameFragment)

        val defaultCard = CardParams.defaultCard()
        defaultCard.onClickCard = onClickCard()
        fragmentTransaction.replace(R.id.fragment1, CardFragment.newInstance(defaultCard))

        val netflixCard = CardParams.netflixCard()
        netflixCard.onClickCard = onClickCard()
        fragmentTransaction.replace(R.id.fragment2, CardFragment.newInstance(netflixCard))

        val lightBillCard = CardParams.lightBillCard()
        lightBillCard.onClickCard = onClickCard()
        fragmentTransaction.replace(R.id.fragment3, CardFragment.newInstance(lightBillCard))

        fragmentTransaction.commit()
    }

    fun onClickCard() = View.OnClickListener { v ->
        Logger.getLogger("FistFragment").info("Card Click!!!")
        Toast.makeText(
            context,
            "CNPJ: " + v.findViewById<TextView>(com.superddaiupay.R.id.cnpj).text,
            LENGTH_SHORT
        ).show()
    }
}