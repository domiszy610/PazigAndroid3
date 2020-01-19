package com.example.pazig3

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MyLocationListener() : LocationListener{
    // var distance: Double = 0.0
    //var lastLocation: Location? = null

    lateinit var myOnLocationChangedListener : (Float?) -> Unit
    var lastlocation: Location?= null
    val dbHelper = MyDatabaseHelper(context = MainActivity())
    val db = dbHelper.writableDatabase




    override fun onLocationChanged(p0: Location?) {
        // if (lastLocation == null) {
        //  lastLocation = p0
        //}
        //distance += p0!!.distanceTo(lastLocation)
        //lastLocation = p0
        if(lastlocation== null)
            lastlocation=p0
        var distance = p0?.distanceTo(lastlocation)
        myOnLocationChangedListener(distance)
        lastlocation=p0

        dbHelper.apply {
            var dystans = distance.toString()
            var data = ""

            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            val formatted = current.format(formatter)

            data = formatted
            insert(dystans, data)
        }


        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}