package com.example.joblink.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joblink.R
import com.example.joblink.activity.DetailJobActivity
import com.example.joblink.adapter.JobAdapter
import com.example.joblink.model.Job
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fr_simpanuser.*
import java.util.*

class SimpanUserFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fr_simpanuser, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    fun getData() {
        FirebaseDatabase.getInstance().getReference("favorit/${FirebaseAuth.getInstance().uid}")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    val list = ArrayList<Job>()
                    e("masuk", "iya")
                    p0.children.forEach {
                        e("value", it.value.toString())
                        if (it.exists()) {
                            FirebaseDatabase.getInstance().getReference("Users/${it.value.toString()}/")
                                .addListenerForSingleValueEvent(object : ValueEventListener {
                                    override fun onDataChange(p1: DataSnapshot) {
                                        val data = p1.child("job").getValue(Job::class.java)
                                        e("data", data!!.posisi)
                                        data.like = true
                                        data.likeKey = it.key
                                        data.email = p1.child("email").value.toString()
                                        list.add(data)
                                        listFav.adapter = JobAdapter(activity!!, R.layout.row_data, list)
                                        listFav.setOnItemClickListener { parent, view, position, id ->
                                            val mIntent = Intent(activity!!, DetailJobActivity::class.java)
                                            mIntent.putExtra("job", list.get(position))
                                            activity!!.startActivity(mIntent)
                                        }
                                    }

                                    override fun onCancelled(p1: DatabaseError) {

                                    }

                                })
                        }
                    }
                    listFav.adapter = JobAdapter(activity!!, R.layout.row_data, list)
                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
}