package com.wellbex.shared.auth


data class User(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",
    var dateOfBirth: String = "",
    var department: String = "",
    var jopTitle: String = "",
    var isValid: Boolean = false,
    var companyId: String = "",
    var id: String = "",
    var imageUrl: String = "",
    var isInvited: Boolean = false
) {
    fun getCompanyName() = email.split("@")[1].split(".")[0]
}