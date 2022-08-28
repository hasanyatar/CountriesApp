package com.example.countriesapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.countriesapp.model.Country
@Dao
interface CountryDao {
    // Data Access Object
    @Insert
    suspend fun insert(vararg countries:Country) : List<Long>
    // Insert -> Insert Into
    // suspend -> coroutine , pause & resume
    // vararg -> vararg is used to pass multiple arguments to a function
    // List<Long> -> return primary keys

    @Query("SELECT * FROM country")
    suspend fun getAllCountries(): List<Country>
    @Query("SELECT * FROM country WHERE uuid = :countryId")
    suspend fun getCountry(countryId:Int) :Country
    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
}