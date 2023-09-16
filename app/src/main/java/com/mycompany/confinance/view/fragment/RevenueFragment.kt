package com.mycompany.confinance.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.mycompany.confinance.databinding.FragmentRenevueBinding
import com.mycompany.confinance.viewmodel.RevenueViewModel

class RevenueFragment : Fragment() {

    private lateinit var binding : FragmentRenevueBinding
    private val viewModel : RevenueViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this)[RevenueViewModel::class.java]

        binding = FragmentRenevueBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}