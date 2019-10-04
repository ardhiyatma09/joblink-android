package com.example.joblink.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.joblink.R
import com.example.joblink.activity.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.android.synthetic.main.fr_profile.*

class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fr_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestProfile()
//            .requestEmail()
//            .build()
//        mGoogleApiClient = GoogleApiClient.Builder(activity!!)
//            .enableAutoManage(activity!! /* FragmentActivity */, this   /* OnConnectionFailedListener */)
//            .addApi(Auth.GOOGLE_SIGN_IN_API, mGoogleSignInOptions)
//            .build()


        logout.setOnClickListener {
            val logoutIntent = Intent(activity, LoginActivity::class.java)
            logoutIntent.putExtra("mode", "logout")
            startActivity(logoutIntent)
            activity!!.finish()

        }
        val acct = GoogleSignIn.getLastSignedInAccount(activity!!)
        if (acct != null) {

            Glide.with(view)
                .load(acct.photoUrl)
                .into(profile)
            displayName.text = acct.displayName
            email.text = acct.email

        }
    }
}