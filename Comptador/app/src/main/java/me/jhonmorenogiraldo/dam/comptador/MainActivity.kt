package me.jhonmorenogiraldo.dam.comptador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private val INITIAL_TIME=3

    private val TAG =MainActivity::class.java.simpleName

    internal lateinit var tapMeButton : Button
    internal lateinit var timeTextView : TextView
    internal lateinit var counterTextView : TextView
    internal var counter = 0
    internal var time = INITIAL_TIME

    internal var appStarted =false
    internal lateinit var countdownTimer :CountDownTimer
//    internal val initalCountDownTimer: Long= 60000
    internal val initalCountDownTimer: Long = time.toLong() * 1000
    internal val intervalCountDownTimer: Long= 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"HOLA MON! onCreate")
        Log.d(TAG,counter.toString())
        Log.d(TAG,time.toString())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setContentView(R.layout.activity_main2)

        initCountdown()

        tapMeButton = findViewById(R.id.tapMeButton)
        timeTextView = findViewById(R.id.timeTextView)
        counterTextView = findViewById(R.id.counterTextView)

        //Actualitzar o definir valor inicial del countertextview

        //TODO en algun moment hauren d'executar incrementCounter

        tapMeButton.setOnClickListener {view ->

            val bounceAnimation =AnimationUtils.loadAnimation(this, R.anim.bounce)
            view.startAnimation(bounceAnimation)

            if(!appStarted){
                startGame()
            }
            incrementCounter()
            //TODO Iniciar el comptador
        }
//        timeTextView.text = time.toString()
       timeTextView.text = getString(R.string.timeText, time)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        if(item.itemId == R.id.actionAbout){
            showInfo()
        }
        return true
    }

    private fun showInfo() {
        val dialogTitle = getString(R.string.aboutTitle, BuildConfig.VERSION_NAME)
        val dialogMessage = getString(R.string.aboutMessage)

        val builder = AlertDialog.Builder(this)

        builder.setTitle(dialogTitle).setMessage(dialogMessage).create().show()
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
        Toast.makeText(this,getString(R.string.resultat,counter),Toast.LENGTH_LONG).show()
        resetGame()
    }

    private fun resetGame(){
        counter=0
        counterTextView.text =counter.toString()

        time = INITIAL_TIME

        timeTextView.text=time.toString()
        initCountdown()

        appStarted= false
    }
}