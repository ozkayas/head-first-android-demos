package com.hfad.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    lateinit var stopwatch: Chronometer
    var running = false
    var offset: Long = 0

    // Add key Strings for use with the bundle
    val OFFSET_KEY = "offset"
    val RUNNING_KEY = "running"
    val BASE_KEY = "base"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stopwatch = findViewById(R.id.stopwatch)

        val startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener() {
            if(!running){
                setBaseTime()
                stopwatch.start()
                running = true
            }
        }


        val pauseButton = findViewById<Button>(R.id.pause_button)
        pauseButton.setOnClickListener() {
            if(running){
                saveOffsetTime()
                stopwatch.stop()
                running = false
            }
        }

        val resetButton = findViewById<Button>(R.id.reset_button)
        resetButton.setOnClickListener() {
            if(!running){
                offset = 0
                setBaseTime()
            }
        }

    }

    override fun onStop() {
        super.onStop()
        println("ON STOP")
        if (running) {
            saveOffsetTime()
            stopwatch.stop()
        }
    }

    override fun onRestart() {
        super.onRestart()
        println("ON RESTART")
        if (running) {
            setBaseTime()
            stopwatch.start()
            offset = 0
        }
    }


    fun setBaseTime(){
        stopwatch.base = SystemClock.elapsedRealtime() - offset
    }

    fun saveOffsetTime(){
        offset = SystemClock.elapsedRealtime() - stopwatch.base
    }




}