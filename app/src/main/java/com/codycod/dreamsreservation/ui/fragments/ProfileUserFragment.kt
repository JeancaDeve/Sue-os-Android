package com.codycod.dreamsreservation.ui.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.data.enums.EnUserRoles
import com.codycod.dreamsreservation.data.models.MdUser
import com.codycod.dreamsreservation.ui.activities.LoginActivity
import com.codycod.dreamsreservation.ui.activities.MyReservationsActivity
import com.codycod.dreamsreservation.utils.Functions


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

        //get textview

        val nameText = view.findViewById<TextView>(R.id.info_user_name)
        val phoneText = view.findViewById<TextView>(R.id.info_user_phone)
        val dniText = view.findViewById<TextView>(R.id.info_user_dni)

        //this is a user get information in login

        val userinfo = Functions.getUserInfo(view.context)

        //to load info user in items
        nameText.text = "${userinfo.name} ${userinfo.lastname}"
        phoneText.text = userinfo.phone
        dniText.text = userinfo.dni


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

    companion object {
        fun newInstance(): ProfileUserFragment = ProfileUserFragment()
    }

}