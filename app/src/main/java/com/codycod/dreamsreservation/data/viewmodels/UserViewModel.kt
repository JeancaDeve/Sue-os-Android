package com.codycod.dreamsreservation.data.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codycod.dreamsreservation.data.models.MdUser
import com.codycod.dreamsreservation.utils.Functions
import com.google.firebase.firestore.FirebaseFirestore

class UserViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    private val collection = firestore.collection("usuarios")
    val userDni = MutableLiveData<MdUser?>()

    //this is user get in the query
    val user = MutableLiveData<MdUser?>()

    //to get user by dni and phone number
    fun getUserByDniAndPhone(dni: String, phone: String) {
        collection
            .whereEqualTo("dni", dni)
            .whereEqualTo("phone", phone)
            .get()
            .addOnSuccessListener { users ->
                if (users != null && !users.isEmpty) {
                    val userDocument = users.documents[0].data
                    user.value = userDocument?.let { Functions.parseUserJson(it) }
                } else {
                    Log.e("Not Found", "Not exist users with dni $dni and phone $phone")
                    user.value = null
                }
            }
            .addOnFailureListener {
                Log.d("error", "The error is $it")
                user.value = null
            }
    }

    //to get user by number of dni

    fun getUserByDni(dni: String) {
        collection.whereEqualTo("dni", dni)
            .get()
            .addOnSuccessListener {
                if (!it.isEmpty || it != null) {
                    val userData = it.documents[0].data

                    val user = userData?.let { Functions.parseUserJson(userData) }

                    userDni.value = user
                }
            }
    }


}