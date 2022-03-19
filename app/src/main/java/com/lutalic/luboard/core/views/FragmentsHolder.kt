package com.lutalic.luboard.core.views

import com.lutalic.luboard.core.ActivityScopeViewModel

/**
 * Implement this interface in the activity.
 * Needed to implement the BaseFragment & ViewModelFactory classes.
 */
interface FragmentsHolder {

    /**
     * Called when activity views should be re-drawn.
     */
    fun notifyScreenUpdates()


    fun getActivityScopeViewModel(): ActivityScopeViewModel

}