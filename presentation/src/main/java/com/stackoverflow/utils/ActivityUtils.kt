package com.stackoverflow.utils

import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class ActivityUtils {

    companion object {
        fun addFragmentToActivity(
            @NonNull fragmentManager: FragmentManager,
            @NonNull fragment: Fragment, frameId: Int
        ) {
            checkNotNull(fragmentManager)
            checkNotNull(fragment)
            val transaction = fragmentManager.beginTransaction()
            transaction.add(frameId, fragment)
            transaction.commit()
        }
    }
}