package com.example.pazig3

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.pazig3.MyDatabaseHelper.Companion.TABLE_NAME
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    fun selectAll(): ArrayList<String> {
        var results = ArrayList<String>()
        try {

            val dbHelper = MyDatabaseHelper(this)
            val db = dbHelper.writableDatabase
            val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast) {
                    results.add("${cursor.getString(1)} ${cursor.getString(2)}")
                    cursor.moveToNext()
                }
            }
            db.close()
        }
        catch(e:Exception){

        }
        return results
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var permission = android.Manifest.permission.ACCESS_FINE_LOCATION
        b1.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
                var locationmanager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
                var locationlistener = MyLocationListener()
                locationlistener.myOnLocationChangedListener={
                    it
                }
                locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,1f,locationlistener)
                try {
                    var Rel = selectAll()
                    Lista.adapter = ArrayAdapter<String>(this, R.layout.row, Rel)
                }catch(e: Exception){}


            } else {
                ActivityCompat.requestPermissions(this, arrayOf(permission), 0)

            }
        }
        b2.setOnClickListener {
            var locationmanager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            var locationlistener = MyLocationListener()
            locationmanager.removeUpdates(locationlistener)

        }


    }

}
