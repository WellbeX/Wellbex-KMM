package com.wellbex.shared.auth

import com.wellbex.shared.*
import com.wellbex.shared.Constants.COMPANIES
import com.wellbex.shared.Constants.DOMAIN
import com.wellbex.shared.Constants.USERS
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.firestore.where


class AuthRepository : BaseRepository() {

    suspend fun tryLogin(email: String, password: String): Result<User> =
        isSupportedDomain(User(email = email, password = password)) then ::loginUserResult


    suspend fun registerUser(user: User): Result<User> {
        return isSupportedDomain(user) then
                ::authNewUser then
                ::uploadProfileImage then
                ::saveUserDocument then
                ::addUserIdToCompany

    }

    suspend fun saveUserDocument(user: User): Result<User> {
        fireStore.collection(USERS).document(user.id).set(user)
        return success(user)
    }

    suspend fun addUserIdToCompany(user: User): Result<User> {
        fireStore.collection(COMPANIES)
            .document(user.companyId)
            .collection(USERS)
            .document(user.id)
            .set("id" to user.id)
        return success(user)
    }

    private suspend fun uploadProfileImage(user: User): Result<User> {
        val url = StorageUploadRepository().uploadProfile(user.imageUrl)
        user.imageUrl = url
        return success(user)
    }


    private suspend fun authNewUser(user: User): Result<User> {
        val currentUser =
            auth.createUserWithEmailAndPassword(user.email, user.password).user
        return if (currentUser != null)
            success(user)
        else
            fail("Cannot register this account")
    }


    private suspend fun loginUserResult(
        user: User
    ): Result<User> {
        val currentUser =
            auth.signInWithEmailAndPassword(user.email, user.password).user
        return if (currentUser != null)
            success(user)
        else
            fail("This Domain is not part of Wellbex family")
    }


    private suspend fun isSupportedDomain(
        user: User
    ): Result<User> {
        val foundCompanies = fireStore.collection(COMPANIES)
            .where(DOMAIN, getDomainFromEmail(user.email)).get().documents
        return if (foundCompanies.isNotEmpty())
            success(user.apply {
                companyId = foundCompanies.first().id
            })
        else
            fail("Company is not register")
    }


    private fun getDomainFromEmail(email: String) = email.split('@')[1]

}