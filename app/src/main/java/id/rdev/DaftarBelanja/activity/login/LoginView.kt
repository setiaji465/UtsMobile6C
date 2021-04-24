package id.rdev.DaftarBelanja.activity.login

import id.rdev.DaftarBelanja.model.User

interface LoginView {
    fun onSuccessLogin(user: User?)
    fun onErrorLogin(msg: String?)
}