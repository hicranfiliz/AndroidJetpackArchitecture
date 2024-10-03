package com.example.navigationarchitecture

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.navigationarchitecture.databinding.FragmentSWelcomeBinding

class SWelcomeFragment : Fragment() {

    private lateinit var binding: FragmentSWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_s_welcome, container, false)

        var inputName : String? = requireArguments().getString("userName")
        var inputEmail : String? = requireArguments().getString("userEmail")
        binding.tvName.text = inputName.toString()
        binding.tvEmail.text = inputEmail.toString()

        binding.btnViewTerms.setOnClickListener {
            it.findNavController().navigate(R.id.action_SWelcomeFragment_to_STermsFragment)
        }

        return binding.root
    }
}