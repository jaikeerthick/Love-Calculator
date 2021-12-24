package com.jaikeerthick.lovecalculator.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jaikeerthick.lovecalculator.R
import com.jaikeerthick.lovecalculator.databinding.FragmentResultBinding
import com.jaikeerthick.lovecalculator.utils.viewBinding

class ResultFragment: Fragment(R.layout.fragment_result) {

    val binding: FragmentResultBinding by viewBinding(FragmentResultBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI(){

        binding.firstName.text = arguments?.getString("firstName")
        binding.secondName.text = arguments?.getString("secondName")

        arguments?.getString("percentage").let {
            binding.progressText.text = "$it%"
            binding.progressIndicator.setProgressCompat(it!!.toInt(), true)
        }

        binding.resultText.text = arguments?.getString("result")

        binding.againBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

    }
}