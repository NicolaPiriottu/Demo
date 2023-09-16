package it.nicolapiriottu.demo.ui.login

import android.util.Patterns
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import it.nicolapiriottu.demo.util.LiveDataEvent
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    //UseCase
    sealed class StartUseCase {

        data class ShowError(
            val message: String,
        ) :
            StartUseCase()

        data object GoToHome : StartUseCase()
    }

    //LiveData
    val useCaseLiveData = MutableLiveData<LiveDataEvent<StartUseCase>>()

    private var email: String = ""
    private var password: String = ""

    fun setEmail(email: String) {
        this.email = email
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun login(context: FragmentActivity) {
        if (checkValues()) {
            useCaseLiveData.value= LiveDataEvent(StartUseCase.GoToHome)
        }
    }

    fun signup() {
    }

    private fun checkValues(): Boolean {
        return (email.isNotEmpty() && password.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email)
            .matches())
    }
}