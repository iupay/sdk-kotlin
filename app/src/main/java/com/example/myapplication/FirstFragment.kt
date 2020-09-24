package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.superddaiupay.cards.CardListFragment
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

        val defaultCard = CardParams.defaultCard()
        defaultCard.onClickCard = onClickCard()

        val netflixCard = CardParams.netflixCard()
        netflixCard.onClickCard = onClickCard()

        val lightBillCard = CardParams.lightBillCard()
        lightBillCard.onClickCard = onClickCard()

        val lockedCard = CardParams.lockedCard()
        lockedCard.onClickCard = onClickCard()

        val customCard = CardParams()
        customCard.cardColor = "#FFFFFF"
        customCard.dueDate = Calendar.getInstance().time
        customCard.barColor = "blue"
        customCard.cnpj = "10.0000.999.0001-99"
        customCard.cardTitle = "Teste Titulo"
        customCard.text = "Teste Texto"
        customCard.value = 1500.5
        customCard.isFromMail = true
        customCard.isUserAdded = false
        customCard.type = CardParams.CardType.DEFAULT
        customCard.isLocked = false
        customCard.onClickCard = onClickCard()

        val cards: List<CardParams> = listOf(customCard, defaultCard, netflixCard, lightBillCard, lockedCard)
        val cardsListFragment = CardListFragment.newInstance(12, 12, cards)

        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.cardsListFrame, cardsListFragment)
        fragmentTransaction.commit()
    }

    private fun onClickCard() = View.OnClickListener { v ->
        Logger.getLogger("FistFragment").info("Card Click!!!")
        Toast.makeText(
            context,
            "CNPJ: " + v.findViewById<TextView>(com.superddaiupay.R.id.cnpj).text,
            LENGTH_SHORT
        ).show()
    }
}