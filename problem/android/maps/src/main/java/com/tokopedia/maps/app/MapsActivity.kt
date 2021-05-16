package com.tokopedia.maps.app

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.tokopedia.maps.R
import com.tokopedia.maps.data.UtilHelper.formatNumber
import com.tokopedia.maps.domain.Country
import kotlinx.android.synthetic.main.activity_maps.*

open class MapsActivity : AppCompatActivity(), MapsContract.View {

    private lateinit var presenter: MapsPresenter

    private var mapFragment: SupportMapFragment? = null
    private var googleMap: GoogleMap? = null

    private lateinit var textCountryName: TextView
    private lateinit var textCountryCapital: TextView
    private lateinit var textCountryPopulation: TextView
    private lateinit var textCountryCallCode: TextView

    private var editText: EditText? = null
    private var buttonSubmit: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        initPresenter()
        bindViews()
        initListeners()
        loadMap()
    }

    private fun initPresenter() {
        presenter = MapsPresenter(this)
    }

    private fun bindViews() {
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        editText = findViewById(R.id.editText)
        buttonSubmit = findViewById(R.id.buttonSubmit)
        textCountryName = findViewById(R.id.txtCountryName)
        textCountryCapital = findViewById(R.id.txtCountryCapital)
        textCountryPopulation = findViewById(R.id.txtCountryPopulation)
        textCountryCallCode = findViewById(R.id.txtCountryCallCode)
    }

    private fun initListeners() {
        buttonSubmit!!.setOnClickListener {
            presenter.getCountryData(editText?.text.toString())
        }
    }

    private fun loadMap() {
        mapFragment!!.getMapAsync { googleMap ->
            this@MapsActivity.googleMap = googleMap
            this@MapsActivity.googleMap?.apply {
                setMaxZoomPreference(4.0f)
                setMinZoomPreference(4.0f)
            }
        }
    }

    override fun setData(country: Country) {
        tv_country_name?.text = country.name
        tv_country_capital?.text = country.capital
        tv_country_pop?.text = country.population.formatNumber()
        tv_country_call_code?.text = country.callingCodes
        googleMap?.apply {
            clear()
            val countryLoc = country.latlng
            addMarker(MarkerOptions().position(countryLoc).title("$country.name} Marker"))
            moveCamera(CameraUpdateFactory.newLatLng(countryLoc))
        }
    }

    override fun onError() {
        Toast.makeText(this.baseContext, "No country found with that name in the database!", Toast.LENGTH_SHORT).show()
    }
}
