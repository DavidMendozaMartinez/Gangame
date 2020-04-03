package com.davidmendozamartinez.gangame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.davidmendozamartinez.gangame.deals.DealsFragment
import com.davidmendozamartinez.gangame.owned.TopOwnedFragment
import com.davidmendozamartinez.gangame.rated.TopRatedFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val DEFAULT_OPTION = R.id.action_deals
    }

    private val fragments: HashMap<Int, Fragment> = hashMapOf(
        Pair(R.id.action_deals, DealsFragment()),
        Pair(R.id.action_top_owned, TopOwnedFragment()),
        Pair(R.id.action_top_rated, TopRatedFragment())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        navigationView.selectedItemId = DEFAULT_OPTION
        navigationView.setOnNavigationItemSelectedListener { item ->
            val fragment: Fragment? = fragments[item.itemId]

            if (fragment != null)
                replaceFragment(fragment)

            true
        }
    }

    private fun initView() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)

        if (currentFragment == null)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, fragments[DEFAULT_OPTION]!!)
                .commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}