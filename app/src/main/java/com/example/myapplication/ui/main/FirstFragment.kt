package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.superddaiupay.cards.CardParams
import com.superddaiupay.cards.CardType
import com.superddaiupay.cards.list.CardsFragment
import java.util.*
import java.util.logging.Logger

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val featuredCard = CardParams.featuredCard()
        featuredCard.onClickCard = onClickCard()

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
        customCard.cardTitle = "Title Test"
        customCard.text = "Text test"
        customCard.value = 1500.5
        customCard.isFromMail = true
        customCard.isUserAdded = false
        customCard.type = CardType.DEFAULT
        customCard.isLocked = false
        customCard.onClickCard = onClickCard()

        val nubankCard = CardParams()
        nubankCard.dueDate = Calendar.getInstance().time
        nubankCard.creditCardText = "Cartão 5162 **** **** 9090"
        nubankCard.value = 1376.70
        nubankCard.isFromMail = true
        nubankCard.isUserAdded = false
        nubankCard.type = CardType.NUBANK
        nubankCard.onClickCard = onClickNubankCard()

        val cards: List<CardParams> =
            listOf(featuredCard, customCard, defaultCard, netflixCard, lightBillCard, lockedCard, nubankCard)
        val cardsListFragment = CardsFragment.newInstance(0, 0, cards)

        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.cardsListFrame, cardsListFragment)
        fragmentTransaction.commit()
    }

    private fun onClickCard() = View.OnClickListener { v ->
        Logger.getLogger("FistFragment").info("Card Click!!!")
        Toast.makeText(
            context,
            "CNPJ: " + v.findViewById<TextView>(com.superddaiupay.R.id.cardTvCnpjValue).text,
            LENGTH_SHORT
        ).show()
    }

    private fun onClickNubankCard() = View.OnClickListener { v ->
        Logger.getLogger("FistFragment").info("Card Click!!!")
        Toast.makeText(
            context,
            v.findViewById<TextView>(com.superddaiupay.R.id.cardTvCnpjLabel).text,
            LENGTH_SHORT
        ).show()
    }
}