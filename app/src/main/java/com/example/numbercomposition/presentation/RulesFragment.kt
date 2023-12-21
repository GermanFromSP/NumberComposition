package com.example.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.numbercomposition.databinding.FragmentChooseBinding
import com.example.numbercomposition.databinding.FragmentRulesBinding

class RulesFragment : Fragment() {
    private var _binding: FragmentRulesBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentRulesBinding == null")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRulesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnOK.setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): RulesFragment {
            return RulesFragment()
        }
    }
}