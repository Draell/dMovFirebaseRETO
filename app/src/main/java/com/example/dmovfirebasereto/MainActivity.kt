package com.example.dmovfirebasereto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dmovfirebasereto.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONObject
import java.time.Year

class MainActivity : AppCompatActivity() {
private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = Firebase.database
        val myRef = database.reference

        binding.btnGuardar.setOnClickListener{
            val Id = binding.etId.text.toString()
            val Title = binding.etTitle.text.toString()
            val Year = binding.etYear.text.toString()
            val imdbID = binding.etImdbID.text.toString()
            val Type = binding.etType.text.toString()
            val Poster = binding.etPoster.text.toString()
            val Country = binding.etCountry.text.toString()
            val Genre = binding.etGenre.text.toString()

            myRef.child( "Peliculas").child(Id).setValue(movies(Id, Title, Year, imdbID, Type, Poster, Country, Genre))
        }

       /* binding.btnBuscar.setOnClickListener{
            val vId = binding.etBuscar.text.toString()
            myRef.child("Peliculas").child("1").get().addOnSuccessListener { leer ->
                val Base = JSONObject(leer.value.toString())
                binding.tvTitle.setText(Base.getString("title").toString())
            }
        } */

        //val valor : Int = 0

        binding.btnBuscar.setOnClickListener{
            val myDB = FirebaseDatabase.getInstance()
            val valor = binding.etBuscar.text.toString()
            myRef.child("Peliculas").child(valor).get().addOnSuccessListener { record ->
                val json = JSONObject(record.value.toString())
                Log.d("ValoresFirebase", "got value ${record.value}")
                binding.tvTitle.setText(json.getString("title").toString())
                binding.tvYear.setText(json.getString("year").toString())
                binding.tvimdbID.setText(json.getString("imdbID").toString())
                binding.tvType.setText(json.getString("type").toString())
                binding.tvPoster.setText(json.getString("poster").toString())
                binding.tvCountry.setText(json.getString("country").toString())
                binding.tvGenre.setText(json.getString("genre").toString())

            }


        }

    }
}