package com.example.countriesapp.util.brodcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast

class HasConnectionNetwork : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        isOnline(context)
    }

    fun isOnline(context: Context): Boolean {
      try {
       val connectivityManager :ConnectivityManager =  context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
              val nw = connectivityManager.activeNetwork
              if (nw == null) {
                  Toast.makeText(context, "Network Disconnected", Toast.LENGTH_SHORT).show()
                  return false
              }
              val actNw = connectivityManager.getNetworkCapabilities(nw)
              if (actNw == null) {
                  Toast.makeText(context, "Network Disconnected", Toast.LENGTH_SHORT).show()
                  return false
              }
              return when {
                  actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                      Toast.makeText(context, "Network Connected", Toast.LENGTH_SHORT).show()
                      true
                  }
                  actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                      Toast.makeText(context, "Network Connected", Toast.LENGTH_SHORT).show()
                      true
                  }
                  //for other device how are able to connect with Ethernet
                  actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                      Toast.makeText(context, "Network Connected", Toast.LENGTH_SHORT).show()
                      true
                  }
                  //for check internet over Bluetooth
                  actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> {
                      Toast.makeText(context, "Network Connected", Toast.LENGTH_SHORT).show()
                      true
                  }
                  else -> {
                      Toast.makeText(context, "Network Disconnected", Toast.LENGTH_SHORT).show()
                      false
                  }
              }
          }
          else {
              Toast.makeText(context, "Network Disconnected", Toast.LENGTH_SHORT).show()
              return connectivityManager.activeNetworkInfo?.isConnected ?: false
          }


      }catch (e: Exception){
          Toast.makeText(context, "Network Disconnected", Toast.LENGTH_SHORT).show()
        return  false
      }
    }
}