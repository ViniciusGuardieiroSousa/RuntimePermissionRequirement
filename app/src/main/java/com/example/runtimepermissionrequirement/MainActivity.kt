package com.example.runtimepermissionrequirement

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        const val REQUEST_LOCATION_CODE = 111
        const val PREF_NAME = "dont_ask_again_location"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        checkPermission()
    }

    private fun checkPermission() {
        if (viewModel.hasLocationPermission(this)) {
            permissionPermitted()
        } else {
            askPermission()
        }
    }

    private fun askPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        ) {
            //show a explanation why this  permission is necessary, because the user already deny,
            requestPermission()

        } else {
            if (sharedPreferences.contains(PREF_NAME)) {
                if (sharedPreferences.getBoolean(PREF_NAME, true)) {
                    //user mark the box with don't ask again, to use the permission, the user need
                    // to change on app's configuration
                }
            }

            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            REQUEST_LOCATION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_LOCATION_CODE ->{
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    permissionPermitted()
                }
                else{
                    with(sharedPreferences.edit()) {
                        putBoolean(PREF_NAME, true)
                        commit()
                    }
                    permissionDenied()
                }
            }
        }
    }

    private fun permissionPermitted() {
        Toast.makeText(applicationContext, "Permitted", Toast.LENGTH_SHORT).show()
    }

    private fun permissionDenied(){
        Toast.makeText(applicationContext, "Denied", Toast.LENGTH_SHORT).show()
    }


}
