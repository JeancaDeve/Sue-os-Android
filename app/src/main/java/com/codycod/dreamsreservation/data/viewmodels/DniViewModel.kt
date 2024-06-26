package com.codycod.dreamsreservation.data.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codycod.dreamsreservation.data.models.MdResponseApiReniec
import com.codycod.dreamsreservation.utils.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DniViewModel : ViewModel() {

    val dniData = MutableLiveData<MdResponseApiReniec>()
    val dniError = MutableLiveData<String>()

    fun fetchDniData(
        token: String,
        dniNumber: String
    ) {
        val apiService = RetrofitInstance.create(token)

        viewModelScope.launch {
            apiService.getPeopleByDni(dniNumber).enqueue(object : Callback<MdResponseApiReniec> {
                override fun onResponse(
                    call: Call<MdResponseApiReniec>,
                    response: Response<MdResponseApiReniec>
                ) {
                    if (response.isSuccessful) {
                        dniData.postValue(response.body())
                    } else {
                        dniError.postValue(response.message())
                    }
                }

                override fun onFailure(call: Call<MdResponseApiReniec>, error: Throwable) {
                    dniError.postValue(error.message)
                }


            })
        }
    }

}