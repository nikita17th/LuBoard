package com.lutalic.luboard

import android.app.Application
import com.lutalic.luboard.core.BaseApplication
import com.lutalic.luboard.core.model.Repository
import com.lutalic.luboard.model.accounthelper.FirebaseAuthRepository

/**
 * Here we store instances of model layer classes.
 */
class App : Application(), BaseApplication {

    /**
     * Place your repositories here, now we have only 1 repository
     */
    override val repositories: List<Repository> = listOf<Repository>(
        //FirebaseAuthRepository()
    )

}

