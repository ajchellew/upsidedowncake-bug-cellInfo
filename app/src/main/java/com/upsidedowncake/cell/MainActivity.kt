package com.upsidedowncake.cell

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.CellIdentityCdma
import android.telephony.CellIdentityGsm
import android.telephony.CellIdentityLte
import android.telephony.CellIdentityNr
import android.telephony.CellIdentityWcdma
import android.telephony.CellInfo
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PERMISSIONS_REQUEST_LOCATION = 1
    }

    private val telephonyManager by lazy { getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager }

    private lateinit var textView: TextView
    private lateinit var permissionButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text_output)
        permissionButton = findViewById(R.id.button_permission)
        permissionButton.setOnClickListener {
            requestLocationPermission()
        }
    }

    override fun onResume() {
        super.onResume()
        updateServingCell()
    }

    private fun updateServingCell() {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {
            textView.text = "Location Permission Required"
            permissionButton.visibility = View.VISIBLE
            return
        }

        permissionButton.visibility = View.GONE

        val allCellInfo = telephonyManager.allCellInfo
        val count = allCellInfo.size
        var identity = "No Serving Cell"
        if (count > 0) {
            identity = allCellInfo[0].cellIdentityString()
        }

        textView.text = "All Cell Info Size: $count\n\nServing Cell\n$identity"
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSIONS_REQUEST_LOCATION
        )
    }
}

