package com.example.numbercomposition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.numbercomposition.R
import com.example.numbercomposition.domain.entity.GameResult

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}

@BindingAdapter("resultImage")
fun bindResultImage(imageView: ImageView, winner: Boolean) {
    if (winner) {
        imageView.setImageResource(R.drawable.happy_svgrepo_com)
    } else {
        imageView.setImageResource(R.drawable.sad_svgrepo_com)
    }
}

@BindingAdapter("resultCongratulation")
fun bindResultCongratulation(textView: TextView, winner: Boolean) {
    val context = textView.context
    if (winner) {
        textView.text = context.getString(R.string.congratulation_happy)
    } else {
        textView.text = context.getString(R.string.congratulation_sad)
    }
}

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.min_right_answers),
        count
    )
}

@BindingAdapter("userAnswers")
fun bindUserAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.users_right_answers),
        count
    )
}

@BindingAdapter("requiredPercent")
fun bindRequiredPercent(textView: TextView, percent: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.min_right_percent),
        percent
    )
}


@BindingAdapter("progressBarAnswers")
fun bindProgressBarAnswers(progressBar: ProgressBar, count: Int) {
    progressBar.setProgress(count, true)
}

@BindingAdapter("progressBarColor")
fun bindProgressBarColor(progressBar: ProgressBar, percentOfRightAnswers: Boolean) {
    progressBar.progressTintList = ColorStateList.valueOf(
        getColorState(percentOfRightAnswers, progressBar.context)
    )
}

@BindingAdapter("progressTextColor")
fun bindProgressTextColor(textView: TextView, enoughCountOfRightAnswers: Boolean) {
    textView.setTextColor(getColorState(enoughCountOfRightAnswers, textView.context))
}


@BindingAdapter("userPercent")
fun bindUserPercent(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.users_right_percent),
        usersPercent(gameResult)
    )
}
@BindingAdapter("numberToString")
fun bindNumberToString(textView: TextView, number:Int){
    textView.text = number.toString()
}
@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener){
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}

private fun usersPercent(gameResult: GameResult) = with(gameResult) {
    if (countOfQuestions == 0) {
        0
    } else {
        ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }
}

private fun getColorState(goodState: Boolean, context: Context): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}