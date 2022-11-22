package com.example.cocktailsFragment.view

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.cocktails_fragment.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity for hosting Fragments.
 *
 * @constructor Create empty Main activity
 */
@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
