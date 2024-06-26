package com.codycod.dreamsreservation.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codycod.dreamsreservation.data.models.MdUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RegisterUserViewModel : ViewModel() {

    //get instance of firestore
    private val firestoreInstance = FirebaseFirestore.getInstance()
    val userRegisterStatus = MutableLiveData<Boolean>()



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
                userRegisterStatus.value = it.isSuccessful
            }


    }


    suspend fun verifyExistUser(dni: String, phone: String) : Boolean {
        val usersCollection = FirebaseFirestore.getInstance().collection("usuarios")

        val querySnapshot = usersCollection.whereEqualTo("dni", dni)
            .whereEqualTo("phone", phone)
            .get()
            .await()

        return !querySnapshot.isEmpty

    }


}