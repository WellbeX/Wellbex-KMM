package com.wellbex.shared

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.database.database
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.initialize

open class BaseRepository {
    val app = Firebase.initialize(
        FirebaseOptions(
            apiKey = "AIzaSyDKKuvbTo8Sw6GL9n0FsxLZZmOn2tH1UWo",
            applicationId = "1:780180199962:android:1df1e4090ece10ced40fa3",
            storageBucket = "wellbex-d750f",
            projectId = "wellbex-d750f",
            databaseUrl = "https://wellbex-d750f.firebaseio.com"
        )
    )!!
    val fireStore = Firebase.firestore(app)
    val database = Firebase.database(app)
    val auth = Firebase.auth(app)
}