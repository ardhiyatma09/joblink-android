package com.example.joblink.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.joblink.R
import com.example.joblink.adapter.PagerAdapter
import com.example.joblink.fragment.HomeUserFragment
import com.example.joblink.fragment.ProfileFragment
import com.example.joblink.fragment.SimpanUserFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var pagerAdapter: PagerAdapter? = null
    private val tabIcons = intArrayOf(R.drawable.home, R.drawable.like2, R.drawable.user)

//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.navigation_home -> {
//                val fragment = HomeUserFragment()
//                addFragment(fragment)
//                return@OnNavigationItemSelectedListener true
//                finish()
//            }
//            R.id.navigation_simpan -> {
//                val fragment = SimpanUserFragment()
//                addFragment(fragment)
//                return@OnNavigationItemSelectedListener true
//                finish()
//            }
//            R.id.navigation_profile -> {
//                val fragment = ProfileFragment()
//                addFragment(fragment)
//                return@OnNavigationItemSelectedListener true
//                finish()
//            }
//        }
//        false
//    }

//    private fun addFragment(fragment: Fragment) {
//        supportFragmentManager
//            .beginTransaction()
//            .setCustomAnimations(
//                R.anim.design_bottom_sheet_slide_in,
//                R.anim.design_bottom_sheet_slide_out
//            )
//            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
//            .commit()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//        val fragment = HomeUserFragment()
//        addFragment(fragment)

        pagerAdapter = PagerAdapter(supportFragmentManager)
        pagerAdapter!!.addFragment(HomeUserFragment(), "Home")
        pagerAdapter!!.addFragment(SimpanUserFragment(), "Favorit")
        pagerAdapter!!.addFragment(ProfileFragment(), "Profile")

        customViewPager.adapter = pagerAdapter
        customTabLayout.setupWithViewPager(customViewPager)
        setupTabIcons()

    }

    private fun setupTabIcons() {
        customTabLayout.getTabAt(0)!!.setIcon(tabIcons[0])
        customTabLayout.getTabAt(1)!!.setIcon(tabIcons[1])
        customTabLayout.getTabAt(2)!!.setIcon(tabIcons[2])
    }
}
