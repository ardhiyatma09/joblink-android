package com.example.joblink.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.joblink.R
import com.example.joblink.model.Job


class JobAdapter(val ctx: Context, val layoutResId: Int, val jobList: List<Job>) :
    ArrayAdapter<Job>(ctx, layoutResId, jobList), Filterable {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val tvNamaPerusahaan = view.findViewById<TextView>(R.id.namaPerusahaan)
        val tvLokasi = view.findViewById<TextView>(R.id.lokasi)
        val tvPosisi = view.findViewById<TextView>(R.id.posisi)
        val tvWaktuKerja = view.findViewById<TextView>(R.id.waktuBekerja)
        val tvGaji = view.findViewById<TextView>(R.id.gaji)
        val foto = view.findViewById<ImageView>(R.id.logoPerusahaan)


        val job = jobList[position]
        Glide.with(ctx)
            .load(job.foto)
            .into(foto)

        tvNamaPerusahaan.text = job.namaPerusahaan
        tvLokasi.text = job.lokasi
        tvPosisi.text = job.posisi
        tvWaktuKerja.text = job.waktuBekerja
        tvGaji.text = job.gaji

        return view

    }

    override fun getCount(): Int {
        return jobList.size
    }

}