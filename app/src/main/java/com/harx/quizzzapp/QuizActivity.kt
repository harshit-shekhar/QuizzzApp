package com.harx.quizzzapp

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.harx.quizzzapp.databinding.ActivityQuizBinding
import com.harx.quizzzapp.databinding.ScoreDialogBinding

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        var questionModelList: List<QuestionModel> = listOf()
        var time: String = ""
    }

    lateinit var binding: ActivityQuizBinding

    var currentQuestionIndex = 0

    var selectedAnswer = ""
    var score = 0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            button0.setOnClickListener(this@QuizActivity)
            button1.setOnClickListener(this@QuizActivity)
            button2.setOnClickListener(this@QuizActivity)
            button3.setOnClickListener(this@QuizActivity)
            nextButton.setOnClickListener(this@QuizActivity)

        }

        loadquestion()
        startTimer()


    }

    private fun startTimer() {

        val totalTimeinMili = time.toInt() * 60 * 1000
        object : CountDownTimer(totalTimeinMili.toLong(), 1000L) {
            override fun onTick(miliSecUntilFinished: Long) {

                val seconds = miliSecUntilFinished / 1000
                val minutes = seconds / 60
                val remainingSeconds = seconds % 60
                binding.timerIndicator.text = "$minutes:$remainingSeconds"
            }

            override fun onFinish() {
                TODO("Not yet implemented")
            }

        }.start()


    }


    private fun loadquestion() {

        selectedAnswer = ""

        if(currentQuestionIndex== questionModelList.size){
            finishedQuiz()
            return
        }
        binding.apply {
            questionIndicatorText.text =
                "Question ${currentQuestionIndex + 1}/ ${questionModelList.size} "

            questionProgressIndicator.progress =
                (currentQuestionIndex.toFloat() / questionModelList.size.toFloat() * 100).toInt()

            questionTextview.text = questionModelList[currentQuestionIndex].question

            button0.text = questionModelList[currentQuestionIndex].option[0]
            button1.text = questionModelList[currentQuestionIndex].option[1]
            button2.text = questionModelList[currentQuestionIndex].option[2]
            button3.text = questionModelList[currentQuestionIndex].option[3]

        }
    }

    override fun onClick(view: View?) {

        binding.apply {

            button0.setBackgroundColor(getColor(R.color.grey))
            button1.setBackgroundColor(getColor(R.color.grey))
            button2.setBackgroundColor(getColor(R.color.grey))
            button3.setBackgroundColor(getColor(R.color.grey))
        }

        val clickedButton = view as Button
        if (clickedButton.id == R.id.next_button) {

            if(selectedAnswer.isEmpty()){
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
                return
            }

            if(selectedAnswer == questionModelList[currentQuestionIndex].correct){
             score++
                Log.i("score",score.toString())
            }
            currentQuestionIndex++

                loadquestion()

        } else {

            selectedAnswer = clickedButton.text.toString()
            clickedButton.setBackgroundColor(getColor(R.color.orange))
        }



    }

    private fun finishedQuiz(){

        val totalQuestions = questionModelList.size
        val percentage = ((score.toFloat() / totalQuestions.toFloat() )* 100).toInt()

        val dialogBinding = ScoreDialogBinding.inflate(layoutInflater)
        dialogBinding.apply {
            scoreIndicator.progress = percentage
            scoreProgressText.text = "$percentage %"
            if(percentage>60) {
                scoreTitle.text = "Congrats! You have passed"
                scoreTitle.setTextColor(getColor(R.color.green))
            }else{
                scoreTitle.text = "OOPS! You have failed"
                scoreTitle.setTextColor(getColor(R.color.red))
            }
        scoreSubtitle.text = "$score out of $totalQuestions questions are correct"
            finishButton.setOnClickListener {
                finish()
            }

        }

        AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCancelable(false)
            .show()

        }



    }


