package com.example.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
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
    companion object{
        const val FRAGMENT_NAME = "ChooseFragment"
        fun newInstance():ChooseLevelFragment{
            return ChooseLevelFragment()
        }
    }
}