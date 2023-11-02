package com.mycompany.confinance

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.mycompany.confinance.model.ResponseModel
import com.mycompany.confinance.repository.UserRepository
import com.mycompany.confinance.request.ApiListener
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("DEPRECATION")
class UserRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var apiListener: ApiListener<ResponseModel>

    @Mock
    lateinit var call: Call<ResponseModel>

    private lateinit var userRepository: UserRepository


    @Captor
    lateinit var callbackCaptor: ArgumentCaptor<Callback<ResponseModel>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        userRepository = UserRepository(ApplicationProvider.getApplicationContext())
        userRepository = UserRepository(context, remote)
    }

    @Test
    fun testLoginSuccess() {
        // esse trecho simula uma resposta bem-sucedida com código HTTP 200
        val responseModel = ResponseModel("Login Feito com Sucesso!", 200, 1)
        val response = Response.success(responseModel)

        // Chame a função de ‘login’ e capture o Callback passado para call.enqueue
        userRepository.login("brunacamilly.ads@gmail.com", "12345678", apiListener)
        Mockito.verify(call).enqueue(callbackCaptor.capture())

        // Chame manualmente o método onResponse no Callback capturado
        callbackCaptor.value.onResponse(call, response)

        // Verifique se o método onSuccess do ApiListener foi chamado com a resposta esperada
        Mockito.verify(apiListener).onSuccess(responseModel)
    }


    @Test
    fun testLoginFailure() {
        // Simule uma resposta de erro com código HTTP diferente de 200
        val response = Response.error<ResponseModel>(400, Mockito.mock(ResponseBody::class.java))

        // Simule o comportamento esperado ao chamar a função ‘login’
        Mockito.`when`(call.enqueue(callbackCaptor.capture())).thenAnswer {
            val callback = callbackCaptor.value
            callback.onResponse(call, response)
            null
        }

        // Simule a string de recurso do contexto
        Mockito.`when`(context.getString(R.string.error_failure_login)).thenReturn("Erro de login")

        // Chame a função de ‘login’ e verifique se ela chama onFailure do ApiListener com a mensagem de erro esperada
        userRepository.login("brunacamily.ads@gmail.com", "123456789", apiListener)

        Mockito.verify(apiListener).onFailure("Erro de login", 400)
    }
    fun testCreateAccountSuccess() {
        val name = "John"
        val email = "john@example.com"
        val password = "password"

        val responseModel = ResponseModel("Conta criada com sucesso!",200, 123)
        val response = Response.success(responseModel)

        Mockito.`when`(remote.createAccount(Mockito.any())).thenReturn(response)
        Mockito.`when`(context.getString(R.string.error_failure_login)).thenReturn("Erro de login")

        userRepository.createAccount(name, email, password, apiListener)

        Mockito.verify(apiListener).onSuccess(responseModel)
    }

    @Test
    fun testCreateAccountFailure() {
        val name = "John"
        val email = "john@example.com"
        val password = "password"

        val response = Response.error<ResponseModel>(400, Mockito.mock(okhttp3.ResponseBody::class.java))

        Mockito.`when`(remote.createAccount(Mockito.any())).thenReturn(response)
        Mockito.`when`(context.getString(R.string.email_already_linked)).thenReturn("Email já vinculado")

        userRepository.createAccount(name, email, password, apiListener)

        Mockito.verify(apiListener).onFailure("Email já vinculado", 400)
    }
}

