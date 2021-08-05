package com.example.githubpulls.extensions

import android.view.View

object Extensions {

    fun View.setVisibility(visible: Boolean) {
        this.visibility = if (visible) View.VISIBLE else View.GONE
    }
}