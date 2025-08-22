package com.example.socialapp

import AuthRepositoryImpl
import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.socialapp.data.local.AppDatabase
import com.example.socialapp.data.repo.AuthRepository


class SocialApp : Application() {
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}

class AppContainer(context: Context) {
    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "social_app.db"
    ).fallbackToDestructiveMigration().build()


 //hn3od al7gat aly btkon reusable zy aldatabase w hkza
    // fa hn3rf hna al auth
    val authRepository: AuthRepository = AuthRepositoryImpl(
        userDao = db.userDao(),

    )
}