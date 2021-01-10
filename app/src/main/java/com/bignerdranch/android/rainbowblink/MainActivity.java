package com.bignerdranch.android.rainbowblink;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.things.contrib.driver.apa102.Apa102;
import com.google.android.things.contrib.driver.bmx280.Bmx280SensorDriver;
import com.google.android.things.contrib.driver.ht16k33.AlphanumericDisplay;
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;
import com.google.android.things.pio.Gpio;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Gpio redLed = null, greenLed = null, blueLed = null;
    private boolean toggle = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Opening the LEDs
        try {
            redLed = RainbowHat.openLedRed();
            greenLed = RainbowHat.openLedGreen();
            blueLed = RainbowHat.openLedBlue();
            blinkLed();
        } catch (Exception e){
            Log.d("Exception 1: ", e.getMessage());
        }
    }
    //This method is for setting the value of LED
    public void blinkLed(){
        //This anonymous class will run after every 500 mili seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toggle = !toggle;
                try {
                    redLed.setValue(toggle);
                    blueLed.setValue(toggle);
                    greenLed.setValue(toggle);
                    blinkToggle();
                }catch (Exception e){
                    Log.d("Exception 2: ", e.getMessage());
                }
            }
        },500);
    }

    public void blinkToggle() throws Exception{
        blinkLed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}