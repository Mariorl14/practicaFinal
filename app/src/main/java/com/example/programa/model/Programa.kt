package com.example.programa.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Programa(
    var id: String,
    val nombre: String,
    val audiencia: Double?,
    val canal: Number?,
    val presentadores: String?,
    val web: String?
) : Parcelable {
    constructor():
            this("","",0.0,0,"","")
}
