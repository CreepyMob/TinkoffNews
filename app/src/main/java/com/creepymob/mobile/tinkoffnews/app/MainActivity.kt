package com.creepymob.mobile.tinkoffnews.app

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.creepymob.mobile.tinkoffnews.R

class MainActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
