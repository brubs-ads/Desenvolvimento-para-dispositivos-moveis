package com.mycompany.confinance.repository.service

import com.mycompany.confinance.model.MovementModel
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MovementService {

   /* @GET("movement")
   fun List():Call<List<>>

    @GET("movement/{id}")
    fun load(@Path(value = "id" , encoded = true) id:Long):Call<>

    @GET("movement/user/{id}")

    @POST("movement")
    @FormUrlEncoded
    fun create(
        @Field ("type_movement") type_movement : String,
        @Field ("value") value: Double,
        @Field ("description") description : String,
        @Field ("date") date :String
    )

    @PUT("movement")
    @FormUrlEncoded
    fun update(
        @Field ("id") id : Long,
        @Field ("type_movement") type_movement : String,
        @Field ("value") value: Double,
        @Field ("description") description : String,
        @Field ("date") date :String
    )

    @DELETE
    @FormUrlEncoded
    fun delete(@Field ("id") id: Long): Call<> */
}