package com.example.week2task

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    lateinit var stateTextView: TextView
    lateinit var orientationTextView : TextView


    var portraitCount = 0
    var landscapeCount = 0
    var handler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stateTextView = findViewById(R.id.stateTextView)
        orientationTextView = findViewById(R.id.orientationTextView)

        if(savedInstanceState != null) {
            portraitCount = savedInstanceState.getInt("portraitCount")
            landscapeCount = savedInstanceState.getInt("landscapeCount")
        }
        handler.postDelayed({
            stateTextView.text = "Current State : OnCreate"
        }, 1000)


    }

    override fun onStart() {
        super.onStart()

        handler.postDelayed({
            stateTextView.text = "Current State : OnStart"
        }, 1500)    }

    override fun onResume() {
        super.onResume()
        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){

            portraitCount++
            orientationTextView.text = getString(R.string.portraitTextView, portraitCount)

        }else{

            landscapeCount++
            orientationTextView.text = getString(R.string.landscapeTextView, landscapeCount)
        }
        handler.postDelayed({
            stateTextView.text = "Current State : OnResume"
        }, 2000)    }

    override fun onPause() {
        super.onPause()
        handler.postDelayed({
            stateTextView.text = "Current State : OnPause"
        }, 2500)    }

    override fun onRestart() {
        super.onRestart()
        //to make the count not increase when minimized
        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            portraitCount--

        }else{
            landscapeCount--
        }
        handler.postDelayed({
            stateTextView.text = "Current State : OnRestart"
        }, 3000)    }

    override fun onStop() {
        super.onStop()
        handler.postDelayed({
            stateTextView.text = "Current State : OnStop"
        }, 3500)    }

    override fun onDestroy() {
        super.onDestroy()
        handler.postDelayed({
            stateTextView.text = "Current State : OnDestroy"
        }, 4000)    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)


        outState.putInt("portraitCount", portraitCount)
        outState.putInt("landscapeCount", landscapeCount)

    }


}