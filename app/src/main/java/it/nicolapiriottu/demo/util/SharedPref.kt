package it.nicolapiriottu.demo.util

import android.content.Context
import android.content.SharedPreferences
import it.nicolapiriottu.demo.App

object SharedPref {

    private lateinit var mSharedPref: SharedPreferences
    const val PREFS_NAME = "demo_prefs"

    fun initSharedPrefs() {
        mSharedPref =
            App.instance.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun clear() {
        mSharedPref.edit().clear().apply()
    }

    fun read(key: String, value: String): String? {
        return mSharedPref.getString(key, value)
    }

    fun read(key: String, value: Int): Int {
        return mSharedPref.getInt(key, value)
    }

    fun read(key: String, value: Boolean): Boolean {
        return mSharedPref.getBoolean(key, value)
    }

    fun read(key: String, value: Long): Long {
        return mSharedPref.getLong(key, value)
    }

    fun read(key: String, value: Float): Float {
        return mSharedPref.getFloat(key, value)
    }

    fun write(key: String, value: String) {
        val prefsEditor: SharedPreferences.Editor = mSharedPref.edit()
        with(prefsEditor) {
            putString(key, value)
            commit()
        }
    }

    fun write(key: String, value: Long) {
        val prefsEditor: SharedPreferences.Editor = mSharedPref.edit()
        with(prefsEditor) {
            putLong(key, value)
            commit()
        }
    }

    fun write(key: String, value: Int) {
        val prefsEditor: SharedPreferences.Editor = mSharedPref.edit()
        with(prefsEditor) {
            putInt(key, value)
            commit()
        }
    }

    fun write(key: String, value: Boolean) {
        val prefsEditor: SharedPreferences.Editor = mSharedPref.edit()
        with(prefsEditor) {
            putBoolean(key, value)
            commit()
        }
    }

    fun write(key: String, value: Float) {
        val prefsEditor: SharedPreferences.Editor = mSharedPref.edit()
        with(prefsEditor) {
            putFloat(key, value)
            commit()
        }
    }
}