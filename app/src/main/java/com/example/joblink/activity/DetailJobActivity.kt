package com.example.joblink.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.joblink.R
import com.example.joblink.model.Job
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_detailjob.*
import java.util.*

class DetailJobActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailjob)
        val job = intent.getSerializableExtra("job") as Job

        posisi.text = job.posisi
        gaji.text = job.gaji
        lokasi.text = job.lokasi
        nama_perusahaan.text = job.namaPerusahaan
        waktu_bekerja.text = job.waktuBekerja
        deskripsi.text = job.deskripsi
        Glide.with(this)
            .load(job.foto)
            .into(foto_perusahaan)

        sendEmailBtn.setOnClickListener {
            //            Toast.makeText(this, job.email!!, Toast.LENGTH_SHORT).show()
            sendEmail(job.email!!)
        }


        if (job.like!!) {
            fav.setImageResource(R.mipmap.ic_favorite2)
        }
        fav.setOnClickListener {
            if (job.like!!) {
                fav.setImageResource(R.mipmap.ic_like)
                val db = FirebaseDatabase.getInstance()
                    .getReference("favorit/${FirebaseAuth.getInstance().uid}/${job.likeKey}")
                db.removeValue()
                job.like = false
            } else {
                val uuid = UUID.randomUUID().toString()
                val db = FirebaseDatabase.getInstance()
                    .getReference("favorit/${FirebaseAuth.getInstance().uid}/$uuid")
                db.setValue(job.key)
                db.push()
                fav.setImageResource(R.mipmap.ic_favorite2)
                job.like = true
                job.likeKey = uuid
            }

        }


    }

    private fun sendEmail(email: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
        }
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Lamaran Pekerjaan dari JobLink")
        intent.putExtra(Intent.EXTRA_TEXT, "")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, "Send Email"))
        }


    }
}