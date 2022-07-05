package com.example.mykotlinapp.features.map

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mykotlinapp.R
import com.example.mykotlinapp.common.ShowDialog
import com.example.mykotlinapp.data.model.LichGomRac
import com.example.mykotlinapp.databinding.ActivityMapsBinding
import com.example.mykotlinapp.util.customView.ProgressDialog
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, MapOnClickListenner {

    val viewModel: MapViewModel by viewModel()
    private lateinit var mMap: GoogleMap
    lateinit var progressDialog: ProgressDialog
    private lateinit var binding: ActivityMapsBinding
    private var lichGom = mutableListOf<LichGomRac>()
    var lat = 10.844018
    var long = 106.636052


    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.grac_green))
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.map = this

//        progressDialog.show()
        registerLiveData()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    private fun registerLiveData() {
        progressDialog.show()
        viewModel.lichgonracDESC.observe(this) {
            it?.let {
                this.lichGom = it
                Log.d("dataMapit", it.toString())
            }
            progressDialog.dismiss()
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(10.844254, 106.635652)
        mMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
        )
//      mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 34f))

        // show pockemons

        Handler().postDelayed(Runnable {
            for (i in 0..lichGom.size- 1) {

                var user = lichGom[i]

                val pointLoc = LatLng(lat, long)
                mMap!!.addMarker(
                    MarkerOptions()
                        .position(pointLoc)
                        .title("tên: " + user.tenKhachHang)
                        .snippet(user.ngayDang!! + " newPoint!!.diaChi")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_green_png))
                )
                lat += 0.001
                long += 0.001
            }
        }, 2000)


    }

    var location: Location? = null

    //Get user location

    inner class MylocationListener : LocationListener {


        constructor() {
            location = Location("Start")
            location!!.longitude = 0.0
            location!!.longitude = 0.0
        }

        override fun onLocationChanged(p0: Location) {
            location = p0
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(p0: String) {
            // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(p0: String) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    override fun back() {
        onBackPressed()
    }

    override fun question() {
        ShowDialog().showDialog(this)
    }


}
