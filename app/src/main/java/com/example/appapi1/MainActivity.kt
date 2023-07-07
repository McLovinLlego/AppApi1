package com.example.appapi1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var rvMain :RecyclerView
    lateinit var myAdapter : MyAdapter
    //Url base de la api
    var BASE_URL = "https://api.github.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Asignacion de la vista actual a la variable rvMain
        rvMain = findViewById(R.id.recycler_view)
        //Se crea una instancia de linearlayoutmanager y la asigna como administrador de
        //diseño layoutManager del RecyclerView rvMain
        rvMain.layoutManager = LinearLayoutManager(this)
        //invoca el metodo getAllData para obtener los datos que se mostraran
        //en el RecyclerView
        getAllData()

    }
    //se define la funcion getAllData
    private fun getAllData() {
        //Se crea una instancia de retrofit
        var retrofit = Retrofit.Builder()
            //se establece la url base
            .baseUrl(BASE_URL)
            //se agrega el convertidor Gson para convertir la respuesta Json
                //en objetos de Kotlin
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            //se llama al metodo create() en la instancia de retrofit
                //para crear una instancia de la interfaz ApiInterface
            .create(ApiInterface::class.java)
        //Se llama al metodo get data para realizar la solicitud Http
        var retroData = retrofit.getData()

        //se define un objeto anonimo que implementa la interfaz
        retroData.enqueue(object : Callback<List<UsersItem>>{
            //Dentro de este objeto se implementan los metodos onResponse
            //y onFailure para menejar la respuesta exitosa o el fallo
            //de la solicitud
            override fun onResponse(
                call: Call<List<UsersItem>>,
                response: Response<List<UsersItem>>
            ) {
                var data = response.body()!!
                myAdapter = MyAdapter(baseContext,data)
                rvMain.adapter = myAdapter
                Log.d("data", data.toString())
            }

            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {

            }
        })
    }

}