package com.example.runtimepermissionrequirement

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private fun needToAskPermission(): Boolean {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1)
    }

    fun hasLocationPermission(context: Context): Boolean {
        if (needToAskPermission())
            return ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

        return true
    }


}