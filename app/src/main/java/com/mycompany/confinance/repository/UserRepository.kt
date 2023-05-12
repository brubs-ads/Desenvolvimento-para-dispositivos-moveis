package com.mycompany.confinance.repository
import com.mycompany.confinance.model.UserLoginModel
import com.mycompany.confinance.repository.service.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepository {

    private val remote = RetrofitClient.getService(UserService::class.java)

    fun login(email: String, password: String){
        var call = remote.login(email, password)
        call.enqueue(object :Callback<UserLoginModel>{
            override fun onResponse(
                call: Call<UserLoginModel>,response: Response<UserLoginModel>) {
                val s = ""
            }
            override fun onFailure(call: Call<UserLoginModel>, t: Throwable) {
                val s = ""
            }
        })
    }
}