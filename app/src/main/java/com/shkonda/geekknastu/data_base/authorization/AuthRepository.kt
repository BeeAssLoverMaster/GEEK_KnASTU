package com.shkonda.geekknastu.data_base.authorization

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.shkonda.geekknastu.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>>
}