package com.example.countriesapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countriesapp.model.Country
import com.example.countriesapp.service.CountryDataBase
import kotlinx.coroutines.launch

class CountryViewModel(application: Application) : BaseViewModel(application) {
    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(uuid: Int) {
       launch {
           val dao = CountryDataBase(getApplication()).countryDao()
           val country = dao.getCountry(uuid)
           countryLiveData.value = country
       }
    }

}