package com.example.joblink.model

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Job(
    var namaPerusahaan: String? = null,
    var lokasi: String? = null,
    var posisi: String? = null,
    var waktuBekerja: String? = null,
    var deskripsi: String? = null,
    var gaji: String? = null,
    var foto: String? = null,
    var email: String? = "",
    var key: String? = "",
    var like: Boolean? = false,
    var likeKey: String? = ""
) : Serializable