package com.lutalic.luboard.core.views

import android.os.Parcelable
import java.io.Serializable

/**
 * Defines arguments for the screen
 */
interface BaseScreen : Serializable {

    companion object {
        const val ARG_SCREEN = "ARG_SCREEN"
    }

}
