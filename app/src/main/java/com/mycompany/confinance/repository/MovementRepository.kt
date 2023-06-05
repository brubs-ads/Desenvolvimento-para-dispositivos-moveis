package com.mycompany.confinance.repository

import com.google.gson.Gson
import com.mycompany.confinance.model.movement.CreateMovementModel
import com.mycompany.confinance.model.movement.GetMovementModel
import com.mycompany.confinance.model.movement.MovementResponse
import com.mycompany.confinance.model.movement.MovementTotalsModel
import com.mycompany.confinance.model.user.UserResponse
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.repository.service.MovementService
import com.mycompany.confinance.util.Session
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class MovementRepository {

    private val remote = RetrofitClient.getService(MovementService::class.java)

    fun createMovement(
        type_movement: String,
        value: Double,
        description: String,
        date: String,
        user: UserResponse,
        listener: ApiListener<CreateMovementModel>
    ) {
        val movement = CreateMovementModel(
            null, type_movement = type_movement, value = value,
            description = description, date = date, user = UserResponse(Session.userId)
        )
        val call = remote.createMovement(movement)
        call.enqueue(object : Callback<CreateMovementModel> {
            override fun onResponse(
                call: Call<CreateMovementModel>, response: Response<CreateMovementModel>
            ) {
                if (response.code() == HttpURLConnection.HTTP_CREATED) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(), MovementResponse::class.java
                    )
                    listener.onFailure(error.message + " Code: ${error.status} ")
                }
            }

            override fun onFailure(call: Call<CreateMovementModel>, t: Throwable) {
                listener.onFailure("ERRO, ENTRA EM CONTATO COM O DESENVOLVEDOR")
            }
        })
    }



    fun deleteMovementById(id: Long, listener: ApiListener<MovementResponse>) {
        val call = remote.deleteMovementById(id)
        call.enqueue(object : Callback<MovementResponse> {
            override fun onResponse(
                call: Call<MovementResponse>, response: Response<MovementResponse>
            ) {

                if (response.code() == HttpURLConnection.HTTP_OK) {
                    response.body().let {
                        if (it != null) {
                            listener.onSuccess(it)
                        }
                    }
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(), MovementResponse::class.java
                    )
                    listener.onFailure(error.message)
                }
            }

            override fun onFailure(call: Call<MovementResponse>, t: Throwable) {
                listener.onFailure("ERRO, ENTRA EM CONTATO COM O DESENVOLVEDOR")
            }
        })
    }

    fun getMovementByUserId(
        id: Long,
        typeMovement: String,
        listener: ApiListener<List<GetMovementModel>>
    ) {
        val call = remote.getMovementByUserId(id)
        call.enqueue(object : Callback<List<GetMovementModel>> {
            override fun onResponse(
                call: Call<List<GetMovementModel>>, response: Response<List<GetMovementModel>>
            ) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    val movementList = response.body()
                    if (movementList != null) {
                        val filteredList = movementList.filter { it.type_movement == typeMovement }
                        listener.onSuccess(filteredList)
                    }

                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(),
                        MovementResponse::class.java
                    )
                    listener.onFailure(error.message + " Code: ${error.status} ")
                }
            }

            override fun onFailure(call: Call<List<GetMovementModel>>, t: Throwable) {
                listener.onFailure("ERRO, ENTRA EM CONTATO COM O DESENVOLVEDOR")
            }

        })
    }

    fun getTotalRevenueAndExpense(userId: Long, listener: ApiListener<MovementTotalsModel>) {
        val call = remote.getMovementTotals(userId)
        call.enqueue(object : Callback<MovementTotalsModel> {
            override fun onResponse(
                call: Call<MovementTotalsModel>,
                response: Response<MovementTotalsModel>
            ) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(), MovementResponse::class.java
                    )
                    listener.onFailure(error.message)
                }
            }

            override fun onFailure(call: Call<MovementTotalsModel>, t: Throwable) {
                listener.onFailure("ERRO, ENTRA EM CONTATO COM O DESENVOLVEDOR")
            }
        })
    }

    fun updateMovement(
        id: Long,
        type_movement: String,
        value: Double,
        description: String,
        date: String,
        listener: ApiListener<GetMovementModel>
    ) {
        val call = remote.updateMovementById(id,
            GetMovementModel(null,type_movement,value,description,date,UserResponse(Session.userId))
        )
        call.enqueue(object : Callback<GetMovementModel> {
            override fun onResponse(
                call: Call<GetMovementModel>, response: Response<GetMovementModel>
            ) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(), MovementResponse::class.java
                    )
                    listener.onFailure(error.message + " Code: ${error.status} ")
                }
            }

            override fun onFailure(call: Call<GetMovementModel>, t: Throwable) {
                listener.onFailure("ERRO, ENTRE EM CONTATO COM O DESENVOLVEDOR")
            }
        })
    }



}



