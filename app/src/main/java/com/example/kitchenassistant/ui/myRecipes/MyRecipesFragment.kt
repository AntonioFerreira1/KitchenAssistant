package com.example.kitchenassistant.ui.myRecipes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kitchenassistant.databinding.FragmentMyRecipesBinding
import com.example.kitchenassistant.BacalhauClickActivity
import com.example.kitchenassistant.PizzaActivity
import com.example.kitchenassistant.carbonaraActivity
import com.example.kitchenassistant.tiramisuActivity


class MyRecipesFragment : Fragment() {

    private var _binding: FragmentMyRecipesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val myRecipesViewModel =
            ViewModelProvider(this).get(MyRecipesViewModel::class.java)

        _binding = FragmentMyRecipesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*//*

        binding.profilePic.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    activity,
                    BacalhauClickActivity::class.java
                )
            )
        })

        binding.profilePic1.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    activity,
                    carbonaraActivity::class.java
                )
            )
        })

        binding.profilePic2.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    activity,
                    PizzaActivity::class.java
                )
            )
        })

        binding.profilePic3.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    activity,
                    tiramisuActivity::class.java
                )
            )
        })*/

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}