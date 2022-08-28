package com.example.countriesapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.countriesapp.model.Country

@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDataBase : RoomDatabase() {
    abstract fun countryDao(): CountryDao

    // Singleton
    companion object
    {
        @Volatile private var instance : CountryDataBase? = null
        private val LOCK = Any()
        operator fun invoke (context:Context)= instance ?: synchronized(LOCK){
            instance ?: makeDatabase(context).also { instance = it }
        }
        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CountryDataBase::class.java,
            "countrydatabase"
        ).build()

    }
}








 // Lazy Singleton
//public class Singleton private constructor() {
//    init { println("This ($this) is a singleton") }
//
//    private object Holder { val INSTANCE = Singleton() }
//
//    companion object {
//        val instance: Singleton by lazy { Holder.INSTANCE }
//    }
//    var b:String? = null
//}