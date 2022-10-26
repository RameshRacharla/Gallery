package com.application.data.localprefs

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(private val prefs: SharedPreferences) {

    companion object {
        const val KEY_USER_NAME = "PREF_KEY_USER_NAME"
    }

    fun getUserName(): String? =
        prefs.getString(KEY_USER_NAME, null)

    fun setUserName(userName: String) =
        prefs.edit().putString(KEY_USER_NAME, userName).apply()


    fun clearData() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
}