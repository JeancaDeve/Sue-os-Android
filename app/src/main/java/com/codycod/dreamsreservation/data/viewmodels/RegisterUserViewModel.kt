package com.codycod.dreamsreservation.data.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codycod.dreamsreservation.data.models.MdUser
import com.google.firebase.firestore.FirebaseFirestore

class RegisterUserViewModel : ViewModel() {

    //get instance of firestore
    private val firestoreInstance = FirebaseFirestore.getInstance()
    val userIsRegister = MutableLiveData<Boolean>()
    val userRegister = MutableLiveData<MdUser?>()
    val userNoExist = MutableLiveData(false)

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
                if (it.isSuccessful) {
                    userRegister.value = user
                }
            }
            .addOnFailureListener {
                userIsRegister.value = false
                userRegister.value = null
            }
    }

    //to add a user with dni and phone number uniques
    private fun userNotExist(dni: String, phone: String) {
        val collection = firestoreInstance.collection("usuarios")
        collection
            .whereEqualTo("dni", dni)
            .get()
            .addOnSuccessListener { documentsByDni ->
                if (documentsByDni.isEmpty) {
                    collection
                        .whereEqualTo("phone", phone)
                        .get()
                        .addOnSuccessListener { documentsByPhone ->
                            if (documentsByPhone.isEmpty) {
                                userNoExist.value = true
                            }
                        }
                }
            }
    }
}