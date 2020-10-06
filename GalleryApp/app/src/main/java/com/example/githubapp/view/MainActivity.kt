package com.example.githubapp


import android.os.Bundle

import com.example.githubapp.common.Constants

import com.example.githubapp.view.UsersListFragment
import com.example.githubapp.view.ui.BaseActivity


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        createFragments(savedInstanceState)
    }

    private fun createFragments(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            // set list fragment
            val listFragment = UsersListFragment()
            supportFragmentManager.beginTransaction().add(R.id.flUsersList,listFragment, Constants.LIST_FRAGMENT_TAG).commit()
        }

    }

    override fun onPause() {

        super.onPause()
    }





}

