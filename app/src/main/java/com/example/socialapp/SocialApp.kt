package com.example.socialapp

import AuthRepositoryImpl
import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.socialapp.data.local.AppDatabase
import com.example.socialapp.data.repo.AuthRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SocialApp : Application() {

}