@file:Suppress("DEPRECATION")

package com.mycompany.confinance

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mycompany.confinance.model.QueryResponse
import com.mycompany.confinance.model.ResponseModel
import com.mycompany.confinance.model.UserModel
import com.mycompany.confinance.repository.UserRepository
import com.mycompany.confinance.request.ApiListener
import com.mycompany.confinance.service.UserService
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class YourClass {

}


@RunWith(AndroidJUnit4::class)
class UserRepositoryTest {
    annotation class AndroidJUnit4


    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var apiListener: ApiListener<ResponseModel>

    @Mock
    private lateinit var call: Call<ResponseModel>

    @Mock
    private lateinit var userService: UserService

    private lateinit var userRepository: UserRepository

    @Captor
    lateinit var callbackCaptor: ArgumentCaptor<Callback<ResponseModel>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        userRepository = UserRepository(ApplicationProvider.getApplicationContext())
    }

    @Test
    private fun testLoginSuccess() {

        val responseModel = ResponseModel("Login Feito com Sucesso!", 200, 1)
        val response = Response.success(responseModel)

        userRepository.login("brunacamilly.ads@gmail.com", "12345678", apiListener)
        Mockito.verify(call).enqueue(callbackCaptor.capture())

        callbackCaptor.value.onResponse(call, response)
        Mockito.verify(apiListener).onSuccess(responseModel)
    }


    @Test
    fun testLoginFailure() {

        val response = Response.error<ResponseModel>(400, Mockito.mock(ResponseBody::class.java))

        Mockito.`when`(call.enqueue(callbackCaptor.capture())).thenAnswer {
            val callback = callbackCaptor.value
            callback.onResponse(call, response)
            null
        }

        Mockito.`when`(context.getString(R.string.error_failure_login)).thenReturn("Erro de login")
        userRepository.login("email", "senha", apiListener)
        Mockito.verify(apiListener).onFailure("Erro de login", 400)
    }

    fun acount() {
        MockitoAnnotations.initMocks(this)
        userRepository = UserRepository(context)
    }

    @Test
    fun testCreateAccountSuccess() {

        Mockito.`when`(getService()).thenReturn(userService)

        val call: Call<*> = Mockito.mock(Call::class.java) as Call<*>
        Mockito.`when`(userService.createAccount(Mockito.any(UserModel::class.java))).thenReturn(Call)
        val responseModel = ResponseModel("Usuário Criado com Sucesso!", 201, 123)
        val response = Response.success(responseModel)

        Mockito.`when`(call.enqueue(Mockito.any())).thenAnswer {
            val callback = it.arguments[0] as Callback<*>
            callback.onResponse(call as Call<ResponseModel>, response)
        }

        Mockito.`when`(context.getString(Mockito.anyInt())).thenReturn("Email já vinculado")

        userRepository.createAccount("nome", "email", "senha", apiListener)

        Mockito.verify(apiListener).onSuccess(responseModel)
    }

    @Test
    fun testCreateAccountForbidden() {

        Mockito.`when`(getService()).thenReturn(userService)

        val call = Mockito.mock(Call::class.java) as Call<ResponseModel>
        Mockito.`when`(userService.createAccount(Mockito.any(UserModel::class.java))).thenReturn(call)

        val response = Response.error<ResponseModel>(403, Mockito.mock(okhttp3.ResponseBody::class.java))

        Mockito.`when`(call.enqueue(Mockito.any())).thenAnswer {
            val callback = it.arguments[0] as Callback<ResponseModel>
            callback.onResponse(call, response)
        }

        Mockito.`when`(context.getString(Mockito.anyInt())).thenReturn("Email já vinculado")

        userRepository.createAccount("nome", "email", "senha", apiListener)
        Mockito.verify(apiListener).onFailure("Email já vinculado", 403)
    }

    private fun getService(): Any {
        TODO("Not yet implemented")
    }

    //class YourClassTest {

        // Mocks necessários
        //private val remote = mock<YourClassTest>()//
       // private val listener = mock<ApiListener<ResponseModel>>()//
       // private val call = mock<Call<ResponseModel>>()//
       // private val yourClass = YourClass(remote) //

        @Before
        fun setUp() {
            // Configurações iniciais
            val remote = null
            whenever(remote.emailSending(any())).thenReturn(call)
        }

        @Test
        fun testForgotPasswordSuccess(it: Any) {

            Response.success((ResponseModel("Código de recuperação de senha enviado com sucesso!", 201, 123)
                    whenever(call.enqueue(any())).thenAnswer {
                val callback = it.arguments[0] as Callback<ResponseModel>
                callback.onResponse(call, successResponse)
            }).yourClassforgotPassword("brunacamilly.ads@gmail.com"), listener)

            verify(listener).onSuccess(any())
        }

        @Test
        fun testForgotPasswordFailure(): Unit {

            val errorResponse = Response.error<ResponseModel>(400, mock())
            whenever(call.enqueue(any())).thenAnswer {
                val callback = (it.arguments[0] as Callback<ResponseModel>).also {
                    it.onResponse(call, errorResponse)
                }
            }

            yourClass.forgotPassword("example@email.com", listener)
            verify(listener).onFailure(eq("E-mail não encontrado no sistema."), eq(400))
        }

        @Test
        fun testForgotPasswordNetworkFailure() {

            whenever(call.run { enqueue(any()) }).thenAnswer {
                val callback = it.arguments[0] as Callback<ResponseModel>
                callback.onFailure(call, IOException("Erro de rede simulado"))
            }

            var yourClass = null
            yourClass.forgotPassword("example@email.com", listener)
            verify(listener).onFailure(eq("Validação de código não concluída. Por favor, valide o código primeiro."), eq(500))
        }
    class YourClassTest {

        @Mock
        private lateinit var context: Context

        @Mock
        private lateinit var remote: Remote

        @Captor
        lateinit var callbackCaptor: ArgumentCaptor<Callback<QueryResponse>>

        private lateinit var yourClass: YourClass

        @Before
        fun setup() {
            MockitoAnnotations.initMocks(this)
            YourClass().also { yourClass = it }
        }

        @Test
        fun testQueryMonthAndYearSuccess() {
            val response = QueryResponse(2.0, 1.5, 1.5, 123)
            val call = Mockito.mock(Call::class.java) as Call<QueryResponse>

            Mockito.`when`(remote.queryMonthAndYear(Mockito.anyLong(), Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(call)

            yourClass.queryMonthAndYear(10, 2023, object : ApiListener<QueryResponse> {
                override fun onSuccess(response: QueryResponse) {
                    // Verifique se o response é o que você espera
                    // Use o Mockito.verify para verificar o que você precisa
                }

                override fun onFailure(message: String, code: Int) {
                    // Lida com falhas, se necessário
                }
            })

            // Simula uma resposta de sucesso
            Mockito.`when`(call.enqueue(callbackCaptor.capture())).thenAnswer {
                val callback = callbackCaptor.value
                callback.onResponse(call, Response.success(response))
            }

            // Chame o método onResponse para simular a resposta
            callbackCaptor.value.onResponse(call, Response.success(response))
        }

        @Test
        fun testQueryMonthAndYearFailure() {
            val response = Response.error<QueryResponse>(400, Mockito.mock(ResponseBody::class.java))

            Mockito.`when`(remote.queryMonthAndYear(Mockito.anyLong(), Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(call)

            yourClass.queryMonthAndYear(10, 2023, object : ApiListener<QueryResponse> {
                override fun onSuccess(response: QueryResponse) {
                    // Lida com sucesso, se necessário
                }

                override fun onFailure(message: String, code: Int) {
                    // Verifique se a mensagem e o código de erro correspondem ao que você espera
                    // Use o Mockito.verify para verificar o que você precisa
                }
            })

            // Simula uma resposta de erro
            Mockito.`when`(call.enqueue(callbackCaptor.capture())).thenAnswer {
                val callback = callbackCaptor.value
                callback.onResponse(call, response)
            }

            // Chame o método onResponse para simular a resposta de erro
            callbackCaptor.value.onResponse(call, response)
        }
    }

}


