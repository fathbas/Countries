package com.fatihb.countries.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.fatihb.countries.model.Country
import com.fatihb.countries.services.CountryDatabase
import kotlinx.coroutines.launch

class InfoViewModel(application: Application) : BaseViewModel(application) {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(uuid: Int){

        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            val country = dao.getCountry(uuid)

            countryLiveData.value = country
        }


    }
}