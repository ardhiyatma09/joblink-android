package com.example.joblink.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.joblink.R
import com.example.joblink.activity.DetailJobActivity
import com.example.joblink.adapter.JobAdapter
import com.example.joblink.model.Job
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fr_homeuser.*

class HomeUserFragment : Fragment() {

    lateinit var jobList: MutableList<Job>
    lateinit var listView: ListView
    lateinit var dbRef: DatabaseReference
    lateinit var adapter: JobAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fr_homeuser, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        jobList = mutableListOf()
        listView = view.findViewById(R.id.rvData)


        getData()

        tvSearch2.setOnClickListener {
            val query = tvSearch.text.toString()
            if (query.isEmpty()) {
                getData()
            } else {
                getData(query)
            }
        }

    }

    fun getData() {
        dbRef = FirebaseDatabase.getInstance().getReference("Users")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    jobList = ArrayList()
                    for (h in p0.children) {
                        if (h.child("job").exists()) {
                            val job = h.child("job").getValue(Job::class.java)
                            job!!.key = h.key
                            job.email = h.child("email").value.toString()
                            FirebaseDatabase.getInstance().getReference("favorit/${FirebaseAuth.getInstance().uid}")
                                .orderByValue().equalTo(job.key)
                                .addListenerForSingleValueEvent(object : ValueEventListener {
                                    override fun onDataChange(p0: DataSnapshot) {
                                        e("liked?", p0.value.toString().split("=")[0].drop(1))
                                        if (p0.exists()) {
                                            job.likeKey = p0.value.toString().split("=")[0].drop(1)
                                            e("likeKey", job.likeKey)
                                            job.like = true
                                        }
                                    }

                                    override fun onCancelled(p0: DatabaseError) {
                                    }

                                })
                            jobList.add(job)
                        }
                    }

                    adapter = JobAdapter(activity!!.applicationContext, R.layout.row_data, jobList)
                    listView.adapter = adapter
                    listView.setOnItemClickListener { parent, view, position, id ->
                        val mIntent = Intent(activity!!, DetailJobActivity::class.java)
                        mIntent.putExtra("job", jobList.get(position))
                        activity!!.startActivity(mIntent)
                    }
                }
            }

        })
    }

    fun getData(query: String) {
        dbRef = FirebaseDatabase.getInstance().getReference("Users")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    jobList = ArrayList()
                    for (h in p0.children) {
                        if (h.child("job").exists()) {
                            val job = h.child("job").getValue(Job::class.java)
                            if (job!!.lokasi!!.toLowerCase().contains(query.toLowerCase()) ||
                                job.posisi!!.toLowerCase().contains(query.toLowerCase())
                            ) {
                                job.key = h.key
                                job.email = h.child("email").value.toString()
                                FirebaseDatabase.getInstance().getReference("favorit/${FirebaseAuth.getInstance().uid}")
                                    .orderByValue().equalTo(job.key)
                                    .addListenerForSingleValueEvent(object : ValueEventListener {
                                        override fun onDataChange(p0: DataSnapshot) {
                                            e("liked?", p0.value.toString().split("=")[0].drop(1))
                                            if (p0.exists()) {
                                                job.likeKey = p0.value.toString().split("=")[0].drop(1)
                                                e("likeKey", job.likeKey)
                                                job.like = true
                                            }
                                        }

                                        override fun onCancelled(p0: DatabaseError) {
                                        }

                                    })
                                jobList.add(job)
                            }
                        }
                    }

                    adapter = JobAdapter(activity!!.applicationContext, R.layout.row_data, jobList)
                    listView.adapter = adapter
                    listView.setOnItemClickListener { parent, view, position, id ->
                        val mIntent = Intent(activity!!, DetailJobActivity::class.java)
                        mIntent.putExtra("job", jobList.get(position))
                        activity!!.startActivity(mIntent)
                    }
                }
            }

        })
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
}