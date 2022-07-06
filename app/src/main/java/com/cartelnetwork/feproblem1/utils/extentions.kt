package com.cartelnetwork.feproblem1.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun AppCompatActivity.replaceFragment(
    layoutId: Int,
    fragment: Fragment,
    isAddToBackStack: Boolean = true,
    isReplace: Boolean = true
) {
    val transaction = supportFragmentManager.beginTransaction()

    transaction.setCustomAnimations(
        android.R.anim.fade_in,
        android.R.anim.fade_out,
        android.R.anim.fade_in,
        android.R.anim.fade_out
    )

    if (isReplace)
        transaction.replace(layoutId, fragment, fragment.javaClass.name)
    else
        transaction.add(layoutId, fragment, fragment.javaClass.name)

    if (isAddToBackStack)
        transaction.addToBackStack(fragment.javaClass.name)

    if (!isFinishing)
        transaction.commitAllowingStateLoss()
}