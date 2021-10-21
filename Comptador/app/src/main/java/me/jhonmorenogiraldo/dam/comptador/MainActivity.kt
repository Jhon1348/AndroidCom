package me.jhonmorenogiraldo.dam.comptador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    internal lateinit var tapMeButton : Button
    internal lateinit var timeTextView : TextView
    internal lateinit var counterTextView : TextView
    internal var counter = 0
    internal var time = 10

    internal var appStarted =false
    internal lateinit var countdownTimer :CountDownTimer
    internal val initalCountDownTimer: Long= 60000
    internal val intervalCountDownTimer: Long= 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setContentView(R.layout.activity_main2)
        initCountdown()

        tapMeButton = findViewById(R.id.tapMeButton)
        timeTextView = findViewById(R.id.timeTextView)
        counterTextView = findViewById(R.id.counterTextView)

        //Actualitzar o definir valor inicial del countertextview

        //TODO en algun moment hauren d'executar incrementCounter

        tapMeButton.setOnClickListener {
            if(!appStarted){
                startGame()
            }
            incrementCounter()
            //TODO Iniciar el comptador
        }
//        timeTextView.text = time.toString()
       timeTextView.text = getString(R.string.timeText, time)

    }

    private fun startGame() {
        countdownTimer.start()
        appStarted=true
    }

    private fun initCountdown(){
        countdownTimer = object : CountDownTimer(initalCountDownTimer,intervalCountDownTimer){
            override fun onTick(p0: Long) {
                val timeLeft = p0 /1000
                timeTextView.text = timeLeft.toString()
            }

            override fun onFinish() {
            endGame()
            }
        }
    }

   private fun incrementCounter(){
       //       counter = counter +1
       counter += 1
       counterTextView.text =counter.toString()

   }
    private  fun endGame(){
        Toast.makeText(this, getString(R.string.endGame),Toast.LENGTH_LONG).show()
    }

    private fun resetGame(){

    }
}