package com.example.fitness_first.util

import android.content.Context
import android.content.SharedPreferences
import com.example.fitness_first.R

class SettingsManager(context: Context) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val BOTTOM_BAR_SETTINGS = "bottom-bar-settings"
    }

    fun bottomBarSetting(): Int {
        return preferences.getInt(BOTTOM_BAR_SETTINGS, 1)
    }

    fun changeBottomBarSetting(order: Int) {
        val editor = preferences.edit()
        editor.putInt(BOTTOM_BAR_SETTINGS, order)
        editor.apply()
    }
}