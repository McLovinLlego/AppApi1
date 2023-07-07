package com.example.appapi1

import retrofit2.Call
import retrofit2.http.GET
//se de fine una interfaz
interface ApiInterface {
    //Se especifica la solicitud GET HTTP
    @GET("/users")
    //Se utiliza el metodo get data para realizar la llamada a la api
    //y obtener una lista de objetos
    fun getData() : Call<List<UsersItem>>

}