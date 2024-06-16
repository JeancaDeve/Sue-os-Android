package com.codycod.dreamsreservation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.activities.MyReservationsActivity


class ProfileUserFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get btn to see reservations
        val btnReservations = view.findViewById<LinearLayout>(R.id.btn_reservas_profile)

        btnReservations.setOnClickListener {
            startActivity(Intent(view.context, MyReservationsActivity::class.java))
        }

        //get btn to open google maps

        val btnOpenMaps = view.findViewById<LinearLayout>(R.id.btn_open_maps)
        btnOpenMaps.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:-8.101283,-79.022558?z=16&q=-8.101283,-79.022558(Hotel Sueños XD)")
            )
            intent.setClassName(
                "com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"
            )
            startActivity(intent)
        }


    }


    private fun openGoogleMaps(latitude: Double, longitude: Double, label: String) {
        val gmmIntentUri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude($label)")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.gms.maps") // Paquete de la aplicación de Google Maps

        val context = context
        if (context != null && mapIntent.resolveActivity(context.packageManager) != null) {
            startActivity(mapIntent)
        } else {
            // Google Maps app is not installed
            Toast.makeText(context, "Google Maps is not installed.", Toast.LENGTH_LONG).show()
        }
    }


    companion object {
        fun newInstance(): ProfileUserFragment = ProfileUserFragment()
    }


}