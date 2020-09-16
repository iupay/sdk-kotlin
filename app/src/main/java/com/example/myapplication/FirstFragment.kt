package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.superddaiupay.CardFragment
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
        val fragment = CardFragment.newInstance("Teste Titulo", "10.9999.999.0001-99", "#D71921", "#FFFFFF")

        fragment.setOnClickListener(View.OnClickListener { v ->
            Logger.getLogger("TESTE").info("Clicou!!!" + v.contentDescription?.toString())
        })
        fragmentTransaction.add(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}