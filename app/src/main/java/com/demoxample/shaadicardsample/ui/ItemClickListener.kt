package com.demoxample.shaadicardsample.ui

import android.view.View

/**
 * Created by Gaurav on 13-03-2019.
 */
interface ItemClickListener {
    fun accept(view: View, position: Int)
    fun decline(view: View, position: Int)
}