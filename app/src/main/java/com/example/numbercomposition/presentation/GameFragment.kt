package com.example.numbercomposition.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.numbercomposition.R
import com.example.numbercomposition.databinding.FragmentGameBinding
import com.example.numbercomposition.domain.entity.GameResult
import com.example.numbercomposition.domain.entity.GameSettings
import com.example.numbercomposition.domain.entity.Level
import java.text.DecimalFormat

class GameFragment : Fragment() {
    private lateinit var gameSettings: GameSettings
    private lateinit var gameLevel: Level
    private val viewModelFactory by lazy {
        GameViewModelFactory(gameLevel, requireActivity().application)
    }
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnClickListeners()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        with(viewModel) {
            with(binding) {
                formattedTime.observe(viewLifecycleOwner) {
                    tvTimer.text = it
                }
                question.observe(viewLifecycleOwner) {
                    tvCircleNumber.text = it.sum.toString()
                    tvLeftNumber.text = it.visibleNumber.toString()
                    tvOption1.text = it.options[0].toString()
                    tvOption2.text = it.options[1].toString()
                    tvOption3.text = it.options[2].toString()
                    tvOption4.text = it.options[3].toString()
                    tvOption5.text = it.options[4].toString()
                    tvOption6.text = it.options[5].toString()
                }
                settings.observe(viewLifecycleOwner) {
                    gameSettings = it
                }
                progressAnswers.observe(viewLifecycleOwner) {
                    tvAnswersProgress.text = it
                }
                percentOfRightAnswers.observe(viewLifecycleOwner) {
                    progressBar.setProgress(it, true)
                }
                enoughCountOfRightAnswers.observe(viewLifecycleOwner) {
                    tvAnswersProgress.setTextColor(getColorState(it))
                }
                enoughPercentOfRightAnswers.observe(viewLifecycleOwner) {
                    progressBar.progressTintList = ColorStateList.valueOf(getColorState(it))
                }
                minPercent.observe(viewLifecycleOwner) {
                    progressBar.secondaryProgress = it
                }
                gameResult.observe(viewLifecycleOwner) {
                    launchGameResultFragment(it)
                }

            }
        }
    }

    private fun getColorState(goodState: Boolean): Int {
        val colorResId = if (goodState) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_light
        }
        return ContextCompat.getColor(requireContext(), colorResId)
    }

    private fun launchGameResultFragment(result: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(result))
            .addToBackStack(null)
            .commit()
    }

    private fun setupOnClickListeners() {
        with(binding) {
            with(viewModel) {
                tvOption1.setOnClickListener {
                    chooseAnswer(Integer.parseInt(tvOption1.text.toString()))
                }
                tvOption2.setOnClickListener {
                    chooseAnswer(Integer.parseInt(tvOption2.text.toString()))
                }
                tvOption3.setOnClickListener {
                    chooseAnswer(Integer.parseInt(tvOption3.text.toString()))
                }
                tvOption4.setOnClickListener {
                    chooseAnswer(Integer.parseInt(tvOption4.text.toString()))
                }
                tvOption5.setOnClickListener {
                    chooseAnswer(Integer.parseInt(tvOption5.text.toString()))
                }
                tvOption6.setOnClickListener {
                    chooseAnswer(Integer.parseInt(tvOption6.text.toString()))
                }
            }

        }
    }

    private fun parseArgs() {
        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            gameLevel = it
        }
    }

    companion object {
        const val NAME = "GameFragment"
        private const val KEY_LEVEL = "level"
        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }
}