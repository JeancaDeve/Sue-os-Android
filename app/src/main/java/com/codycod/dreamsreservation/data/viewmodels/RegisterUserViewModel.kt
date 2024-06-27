package com.codycod.dreamsreservation.data.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codycod.dreamsreservation.data.models.MdUser
import com.google.firebase.firestore.FirebaseFirestore

class RegisterUserViewModel : ViewModel() {

    //get instance of firestore
    private val firestoreInstance = FirebaseFirestore.getInstance()
    val userIsRegister = MutableLiveData<Boolean>()



    fun registerUser(user: MdUser) {
        registerUserInFireStore(user)
    }

    private fun registerUserInFireStore(user: MdUser) {
        //body json with data of user
        val userJson = hashMapOf(
            "name" to user.name,
            "dni" to user.dni,
            "lastname" to user.lastname,
            "phone" to user.phone,
            "role" to user.role.name
        )

        firestoreInstance.collection("usuarios")
            .document()
            .set(userJson)
            .addOnCompleteListener {
                userIsRegister.value = it.isSuccessful
            }
            .addOnFailureListener {
                userIsRegister.value = false
            }
    }



}