package com.codycod.dreamsreservation.data.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codycod.dreamsreservation.data.enums.EnRoomStatus
import com.codycod.dreamsreservation.data.enums.EnTypeRoom
import com.codycod.dreamsreservation.data.models.MdRoom
import com.codycod.dreamsreservation.utils.Functions
import com.google.firebase.firestore.FirebaseFirestore

class RoomsViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    val listRooms = MutableLiveData<List<MdRoom>>()
    private val listDocuments = ArrayList<MdRoom>()

    // To get all available rooms by type
    fun getAvailableRoomsByType(typeRoom: EnTypeRoom? = null) {

        val collection = firestore.collection("rooms")

        val query = if (typeRoom != null) {
            collection.whereEqualTo("typeRoom", typeRoom)
        } else {
            collection
        }

        query
            .whereEqualTo("status", EnRoomStatus.AVAILABLE)
            .get()
            .addOnSuccessListener { documentsRoom ->
                listDocuments.clear()
                for (room in documentsRoom) {
                    if (room.exists()) {
                        val roomExist = room.data
                        try {

                            val roomModel = Functions.parseRoomJson(roomExist)

                            listDocuments.add(roomModel)
                        } catch (e: Exception) {
                            Log.e("RoomsViewModel", "Error parsing room data", e)
                        }
                    }
                }
                listRooms.value = listDocuments
            }
            .addOnFailureListener { exception ->
                Log.e("RoomsViewModel", "Error getting documents: ", exception)
            }
    }

    //correction to list by content not explicit

    fun getRoomByContent(content: String? = null) {
        val collection = firestore.collection("rooms")

        val query = if (content != null) collection.whereArrayContains(
            "content",
            content
        ) else collection

        query
            .whereEqualTo("status", EnRoomStatus.AVAILABLE)
            .get()
            .addOnSuccessListener { receivedDocuments ->

                listDocuments.clear()

                for (document in receivedDocuments) {
                    if (document.exists()) {
                        val roomReceived = document.data

                        val documentRoom = Functions.parseRoomJson(roomReceived)

                        listDocuments.add(documentRoom)
                    }
                }

                listRooms.value = listDocuments


            }

    }


}
