package com.mycompany.confinance.repository

import android.content.Context
import com.mycompany.confinance.model.MovementModel
import com.mycompany.confinance.model.User
import com.mycompany.confinance.request.ApiListener
import com.mycompany.confinance.request.Retrofit
import com.mycompany.confinance.service.MovementService
import com.mycompany.confinance.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RevenueRepository {

    private val remote = Retrofit.getService(MovementService::class.java)

    fun getUserIdFromSharedPreferences(context: Context): Long {
        val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        return sharedPreferences.getLong(Constants.KEY.KEY_USER_ID, -1)
    }



    fun createMovement(
        codeType: Int,
        value: Long,
        description: String,
        data: String,
        fixedIncome: Boolean?,
        repetitions: String?,
        category: Int?,
        listener: ApiListener<MovementModel>,
        context: Context
    ) {
        val part = repetitions?.split("x")
        val recurrenceIntervals = part?.get(0)?.toInt()
        val recurrenceFrequency = part?.get(1)

        val userId = getUserIdFromSharedPreferences(context = context)

        var call: Call<MovementModel>? = null


        if (codeType == 1) {
            call = remote.createMoviment(
                MovementModel(
                    id = null,
                    type_movement = "receita",
                    value = value,
                    description = description,
                    date = data,
                    Photo = category!!,
                    fixedIncome = fixedIncome,
                    recurrenceFrequency = recurrenceFrequency,
                    recurrenceIntervals = recurrenceIntervals,
                    user = User(userId)
                )
            )

            call.enqueue(object : Callback<MovementModel> {
                override fun onResponse(call: Call<MovementModel>, response: Response<MovementModel>) {

                }

                override fun onFailure(call: Call<MovementModel>, t: Throwable) {
                }

            })
        } else {
            call = remote.createMoviment(
                MovementModel(
                    id = null,
                    type_movement = "despesa",
                    value = value,
                    description = description,
                    date = data,
                    Photo = category!!,
                    fixedIncome = fixedIncome,
                    recurrenceFrequency = recurrenceFrequency,
                    recurrenceIntervals = recurrenceIntervals,
                    user = User(id = 7)
                )
            )

            call.enqueue(object : Callback<MovementModel> {
                override fun onResponse(call: Call<MovementModel>, response: Response<MovementModel>) {

                }

                override fun onFailure(call: Call<MovementModel>, t: Throwable) {
                }

            })
        }

    }


}