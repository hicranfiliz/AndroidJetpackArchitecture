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
import com.example.navigationarchitecture.databinding.FragmentSEmailBinding

class SEmailFragment : Fragment() {

    private lateinit var binding: FragmentSEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_s_email, container, false)
        var inputName : String? = requireArguments().getString("userName")
        binding.btnSubmit.setOnClickListener {
            if (!TextUtils.isEmpty(binding.edtEmail.text.toString())){
                val bundle: Bundle = bundleOf("userEmail"  to binding.edtEmail.text.toString(),
                    "userName" to inputName)
                it.findNavController().navigate(R.id.action_SEmailFragment_to_SWelcomeFragment, bundle)
            }else{
                Toast.makeText(activity, "Please enter your email", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

}