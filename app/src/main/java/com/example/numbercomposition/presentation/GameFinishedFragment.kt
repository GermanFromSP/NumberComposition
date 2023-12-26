package com.example.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.numbercomposition.R
import com.example.numbercomposition.databinding.FragmentGameFinishedBinding
import com.example.numbercomposition.domain.entity.GameResult

class GameFinishedFragment : Fragment() {
    private var _binding: FragmentGameFinishedBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")
    private val args by navArgs<GameFinishedFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnClickListeners()
        setupResults()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupOnClickListeners() {
        binding.btnRestart.setOnClickListener {
            restartGame()
        }
    }

    private fun setupResults() {
        with(binding) {
            if (args.gameResult.winner) {
                ivResult.setImageResource(R.drawable.happy_svgrepo_com)
                tvCongratulations.setText(R.string.congratulation_happy)
            } else {
                ivResult.setImageResource(R.drawable.sad_svgrepo_com)
                tvCongratulations.setText(R.string.congratulation_sad)
            }
            with(args.gameResult) {
                tvUsersAnswers.text = String.format(
                    getString(R.string.users_right_answers),
                    countOfRightAnswers
                )
                tvMinRightAnswers.text = String.format(
                    getString(R.string.min_right_answers),
                    gameSettings.minCountOfRightAnswers
                )
                tvMinPercentRightAnswers.text = String.format(
                    getString(R.string.min_right_percent),
                    gameSettings.minPercentOfRightAnswers
                )
                tvUsersPercentAnswers.text = String.format(
                    getString(R.string.users_right_percent),
                    usersPercent()
                )
            }

        }
    }

    private fun usersPercent() = with(args.gameResult) {
            if (countOfQuestions == 0) {
                0
            } else {
                ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
            }
        }

    private fun restartGame() {
        findNavController().popBackStack()
    }

}