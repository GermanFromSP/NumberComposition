package com.example.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.numbercomposition.R
import com.example.numbercomposition.databinding.FragmentChooseBinding
import com.example.numbercomposition.databinding.FragmentWelcomeBinding
import com.example.numbercomposition.domain.entity.Level

class ChooseLevelFragment : Fragment() {
    private var _binding: FragmentChooseBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentChooseBinding == null")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun launchGameFragment(level: Level){
        findNavController().navigate(
            ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(level)
        )
    }
    private fun setupOnClickListeners(){
        with(binding){
            btnTestLevel.setOnClickListener {
                launchGameFragment(Level.TEST)
            }
            btnLightLevel.setOnClickListener {
                launchGameFragment(Level.EASY)
            }
            btnNormalLevel.setOnClickListener {
                launchGameFragment(Level.NORMAL)
            }
            btnHardLevel.setOnClickListener {
                launchGameFragment(Level.HARD)
            }
        }
    }

}