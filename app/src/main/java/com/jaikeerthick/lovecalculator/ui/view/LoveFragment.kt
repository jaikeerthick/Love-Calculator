package com.jaikeerthick.lovecalculator.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.jaikeerthick.lovecalculator.R
import com.jaikeerthick.lovecalculator.databinding.FragmentLoveBinding
import com.jaikeerthick.lovecalculator.ui.viewmodel.LoveViewModel
import com.jaikeerthick.lovecalculator.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoveFragment : Fragment(R.layout.fragment_love) {

    val binding by viewBinding(FragmentLoveBinding::bind)
    private val viewModel: LoveViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI(){

        viewModel.response.observe(viewLifecycleOwner, {

            when (it?.percentage) {
                null -> {
                    // nothing
                }
                "Error" -> {
                    Toast.makeText(context, "Sorry.. try again later :(", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    //
                    val bundle = bundleOf(
                        "firstName" to binding.myName.text.toString().trim(),
                        "secondName" to binding.partnerName.text.toString().trim(),
                        "percentage" to it.percentage,
                        "result" to it.result
                    )

                    findNavController().navigate(R.id.action_loveFragment_to_resultFragment, bundle)
                    viewModel.response.postValue(null)

                }
            }
            binding.loaderLayout.visibility = View.GONE
        })

        binding.checkBtn.setOnClickListener {
            performCheck()
        }
        binding.clearText.setOnClickListener {
            binding.myName.text.clear()
        }
        binding.clearText2.setOnClickListener {
            binding.partnerName.text.clear()
        }

        binding.apiLink.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://rapidapi.com/ajith/api/love-calculator/")))
        }
        binding.repositoryLink.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/jaikeerthick")))
        }
    }

    private fun performCheck(){

        if (binding.myName.text.toString().trim().length>=2 && binding.partnerName.text.toString().trim().length>=2){

                binding.loaderLayout.visibility = View.VISIBLE

                viewModel.getPercentage(
                    binding.myName.text.toString().trim(),
                    binding.partnerName.text.toString().trim()
                )
            }else{
                Toast.makeText(context, "Please enter valid names !", Toast.LENGTH_SHORT).show()
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel
    }
}