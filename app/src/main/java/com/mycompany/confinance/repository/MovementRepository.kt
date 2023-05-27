package com.mycompany.confinance.repository

import com.google.gson.Gson
import com.mycompany.confinance.model.movement.CreateMovementModel
import com.mycompany.confinance.model.movement.GetMovementModel
import com.mycompany.confinance.model.movement.MovementResponse
import com.mycompany.confinance.model.user.UserTeste
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.repository.service.MovementService
import com.mycompany.confinance.util.Session
import com.mycompany.confinance.util.Session.userId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

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
        date: String,
        user: UserTeste,
        listener: ApiListener<CreateMovementModel>
    ) {
        val movement = CreateMovementModel(null, type_movement = type_movement, value = value,
            description = description, date = date,user = UserTeste(Session.userId) )
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
        newDate: String,
        listener: ApiListener<GetMovementModel>
    ) {
        val updatedMovement = userId?.let {
            GetMovementModel(
                id = id,
                type_movement = newTypeMovement,
                value = newValue,
                description = newDescription,
                date = newDate,
                userId = it
            )
        }

        val call = updatedMovement?.let { remote.updateMovementById(id, it) }
        call?.enqueue(object : Callback<GetMovementModel> {
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


