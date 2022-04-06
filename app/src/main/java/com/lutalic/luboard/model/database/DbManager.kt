package com.lutalic.luboard.model.database

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Deprecated("Simple example how to use realtime firebase db")
object DbManager  { // FIXME just example
    private val db = Firebase.database.reference

    fun sendValueToDb(){
        db.setValue("TEST STRING")
    }
}