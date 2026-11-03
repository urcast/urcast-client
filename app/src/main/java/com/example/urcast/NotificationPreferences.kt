package com.example.urcast

import android.content.Context

class NotificationPreferences(context: Context) {

    private val preferences = context.getSharedPreferences(
        "notification_preferences",
        Context.MODE_PRIVATE
    )

    fun isEnabled(key: String, defaultValue: Boolean = true): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }

    fun setEnabled(key: String, enabled: Boolean) {
        preferences.edit()
            .putBoolean(key, enabled)
            .apply()
    }
}
