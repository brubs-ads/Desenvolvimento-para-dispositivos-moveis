package com.mycompany.confinance.repository

import com.mycompany.confinance.model.MovementModel
import com.mycompany.confinance.repository.MovementRepository.Companion.valueTotal
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.repository.service.MovementService
import com.mycompany.confinance.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class MovementRepository {

    private val remote = RetrofitClient.getService(MovementService::class.java)

    companion object {
        var valueTotal: Double = 0.0
    }


    fun createmovement(
        type_movement: String,
        value: Double,
        description: String,
        date: LocalDate,
        userId: Long?,
        listener: ApiListener<MovementModel>
    ) {
        val movement = MovementModel(
            type_movement = type_movement,
            value = value,
            description = description,
            date = date,
            userId = userId
        )
        val call = remote.createMovement(movement)
        call.enqueue(object : Callback<MovementModel> {
            override fun onResponse(
                call: Call<MovementModel>,
                response: Response<MovementModel>
            ) {
                if (response.code() == Constants.HTTP.CODE.SUCCESS) {
                    response.body()?.let {
                        listener.onSuccess(it)
                        // Atualize o valor total
                        valueTotal += it.value
                    }
                } else {
                    listener.onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<MovementModel>, t: Throwable) {
                listener.onFailure("ERRO, ENTRA EM CONTATO COM O DESENVOLVEDOR")
            }
        })
    }
}

