package com.wellbex.shared

 class StorageUploadRepository() {
    suspend fun uploadProfile(uri: String): String {
        return uri
    }
    suspend fun uploadEventImages(list: List<String>): List<String>{
        return list
    }
}