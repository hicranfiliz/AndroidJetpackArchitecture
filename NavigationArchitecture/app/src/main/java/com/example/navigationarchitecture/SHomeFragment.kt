package com.example.navigationarchitecture

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.navigationarchitecture.databinding.FragmentSHomeBinding

class SHomeFragment : Fragment() {

    private lateinit var binding: FragmentSHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_s_home, container, false)

        binding.btnTerms.setOnClickListener {
            it.findNavController().navigate(R.id.action_SHomeFragment_to_STermsFragment)
        }

        binding.btnSignup.setOnClickListener {
            it.findNavController().navigate(R.id.action_SHomeFragment_to_nameFragment)
        }
        return binding.root
    }

}