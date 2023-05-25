package com.mycompany.confinance.repository

import com.google.gson.Gson
import com.mycompany.confinance.model.movement.CreateMovementModel
import com.mycompany.confinance.model.movement.GetMovementModel
import com.mycompany.confinance.model.movement.MovementResponse
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.repository.service.MovementService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import java.time.LocalDate

class MovementRepository {

    private val remote = RetrofitClient.getService(MovementService::class.java)

    fun getAllMovements(listener: ApiListener<List<GetMovementModel>>) {
        val call = remote.getAllMovements()
        call.enqueue(object : Callback<List<GetMovementModel>> {
            override fun onResponse(
                call: Call<List<GetMovementModel>>,
                response: Response<List<GetMovementModel>>
            ) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(),
                        MovementResponse::class.java
                    )
                    listener.onFailure(error.message)
                }
            }

            override fun onFailure(call: Call<List<GetMovementModel>>, t: Throwable) {
                listener.onFailure("ERRO, ENTRA EM CONTATO COM O DESENVOLVEDOR")
            }
        })
    }

    fun getMovementById(id: Long, listener: ApiListener<GetMovementModel>) {
        val call = remote.getMovementById(id)
        call.enqueue(object : Callback<GetMovementModel> {
            override fun onResponse(
                call: Call<GetMovementModel>,
                response: Response<GetMovementModel>
            ) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(),
                        MovementResponse::class.java
                    )
                    listener.onFailure(error.message)
                }
            }

            override fun onFailure(call: Call<GetMovementModel>, t: Throwable) {
                listener.onFailure("ERRO, ENTRA EM CONTATO COM O DESENVOLVEDOR")
            }
        })
    }

    fun createMovement(
        type_movement: String,
        value: Double,
        description: String,
        date: LocalDate,
        listener: ApiListener<CreateMovementModel>
    ) {
        val movement = CreateMovementModel(0, type_movement, value, description, date, 0)
        val call = remote.createMovement(movement)
        call.enqueue(object : Callback<CreateMovementModel> {
            override fun onResponse(
                call: Call<CreateMovementModel>,
                response: Response<CreateMovementModel>
            ) {
                if (response.code() == HttpURLConnection.HTTP_CREATED) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(),
                        MovementResponse::class.java
                    )
                    listener.onFailure(error.message + " Code: ${error.status} ")
                }
            }

            override fun onFailure(call: Call<CreateMovementModel>, t: Throwable) {
                listener.onFailure("ERRO, ENTRA EM CONTATO COM O DESENVOLVEDOR")
            }
        })
    }

    fun updateMovementById(
        id: Long,
        newTypeMovement: String,
        newValue: Double,
        newDescription: String,
        newDate: LocalDate,
        listener: ApiListener<GetMovementModel>
    ) {
        val updatedMovement = GetMovementModel(
            id = id,
            type_movement = newTypeMovement,
            value = newValue,
            description = newDescription,
            date = newDate
        )

        val call = remote.updateMovementById(id, updatedMovement)
        call.enqueue(object : Callback<GetMovementModel> {
            override fun onResponse(
                call: Call<GetMovementModel>,
                response: Response<GetMovementModel>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(),
                        MovementResponse::class.java
                    )
                    listener.onFailure(error.message)
                }
            }

            override fun onFailure(call: Call<GetMovementModel>, t: Throwable) {
                listener.onFailure("ERRO, ENTRA EM CONTATO COM O DESENVOLVEDOR")

            }
        })
    }

    fun deleteMovementById(id: Long, listener: ApiListener<MovementResponse>) {
        val call = remote.deleteMovementById(id)
        call.enqueue(object : Callback<MovementResponse> {
            override fun onResponse(
                call: Call<MovementResponse>,
                response: Response<MovementResponse>
            ) {

                if (response.code() == HttpURLConnection.HTTP_OK) {
                    response.body().let {
                        if (it != null) {
                            listener.onSuccess(it)
                        }
                    }
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(),
                        MovementResponse::class.java
                    )
                    listener.onFailure(error.message)
                }
            }

            override fun onFailure(call: Call<MovementResponse>, t: Throwable) {
                listener.onFailure("ERRO, ENTRA EM CONTATO COM O DESENVOLVEDOR")
            }
        })
    }
}


