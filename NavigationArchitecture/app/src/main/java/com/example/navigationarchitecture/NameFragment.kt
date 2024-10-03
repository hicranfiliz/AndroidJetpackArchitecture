package com.example.navigationarchitecture

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.navigationarchitecture.databinding.FragmentNameBinding

class NameFragment : Fragment() {
    private lateinit var binding: FragmentNameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_name, container, false)

        binding.btnNext.setOnClickListener {
            if (!TextUtils.isEmpty(binding.edtName.text.toString())){
                val bundle: Bundle = bundleOf("userName" to binding.edtName.text.toString())
                it.findNavController().navigate(R.id.action_nameFragment_to_SEmailFragment, bundle)
            }else{
                Toast.makeText(activity, "Please enter your name", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

}