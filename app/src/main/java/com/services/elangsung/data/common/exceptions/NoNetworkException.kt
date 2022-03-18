package com.services.elangsung.data.common.exceptions

import android.content.Context
import com.services.elangsung.R
import java.io.IOException

class NoNetworkException(val context: Context) : IOException() {

    override fun getLocalizedMessage(): String {
        return context.getString(R.string.general_no_internet_error_message)
    }
}
