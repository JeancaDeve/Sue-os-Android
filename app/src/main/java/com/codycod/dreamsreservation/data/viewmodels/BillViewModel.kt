package com.codycod.dreamsreservation.data.viewmodels

import androidx.lifecycle.ViewModel
import com.codycod.dreamsreservation.data.models.MdBill
import com.google.firebase.firestore.FirebaseFirestore

class BillViewModel : ViewModel() {

    private val firebase = FirebaseFirestore.getInstance()
    private val collection = firebase.collection("bills")

    fun createBill(bill: MdBill) {
        //body json to create a bill
        val billJson = hashMapOf(
            "amount" to bill.amount,
            "date" to bill.date,
            "ruc" to bill.ruc
        )

        collection
            .document()
            .set(billJson)
    }

}