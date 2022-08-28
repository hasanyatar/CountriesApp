package com.example.countriesapp.view

import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.countriesapp.R
import com.example.countriesapp.util.brodcast_receiver.HasConnectionNetwork

class MainActivity : AppCompatActivity() {
    lateinit var hasConnectionNetwork: HasConnectionNetwork
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hasConnectionNetwork = HasConnectionNetwork()
        setContentView(R.layout.activity_main)
        IntentFilter("android.net.conn.CONNECTIVITY_CHANGE").also {
            registerReceiver(hasConnectionNetwork, it)
        }
    }

}