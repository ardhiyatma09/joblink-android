package com.example.joblink.data

import android.content.Context
import android.content.SharedPreferences
import com.example.joblink.R


class SettingApi {
    internal var mContext: Context
    private var sharedSettings: SharedPreferences

    constructor(context: Context) {
        mContext = context
        sharedSettings =
            mContext.getSharedPreferences(mContext.getString(R.string.settings_file_name), Context.MODE_PRIVATE)
    }


    fun deleteAllSettings() {
        sharedSettings.edit().clear().apply()
    }

}