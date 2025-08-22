package com.example.socialapp.data.session

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "session")

class SessionManager(private val context: Context) {
    private val KEY_USER_ID = longPreferencesKey("user_id")

    val userIdFlow: Flow<Long?> = context.dataStore.data.map { prefs: Preferences ->
        prefs[KEY_USER_ID]
    }

    suspend fun setUserId(id: Long) {
        context.dataStore.edit { it[KEY_USER_ID] = id }
    }

    suspend fun clear() {
        context.dataStore.edit { it.remove(KEY_USER_ID) }
    }
}