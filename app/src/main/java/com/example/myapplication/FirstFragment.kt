package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.superddaiupay.cards.CardFragment
import com.superddaiupay.cards.CardParams
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
        params.cardTitle = "Teste Titulo"
        params.cnpj = "99.9999.999.0001-99"
        params.barColor = "#D71921"
        params.cardColor = "#FFFFFF"
        params.onClickCard = View.OnClickListener { v ->
            Logger.getLogger("FistFragment").info("Card Click!!!")
            Toast.makeText(
                context,
                "CNPJ: " + v.findViewById<TextView>(com.superddaiupay.R.id.cnpj).text,
                Toast.LENGTH_LONG
            ).show()
        }
        val fragment = CardFragment.newInstance(params)
//        fragment.setOnClickListener()
        fragmentTransaction.add(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}