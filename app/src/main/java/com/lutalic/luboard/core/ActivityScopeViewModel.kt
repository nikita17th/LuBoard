package com.lutalic.luboard.core

import androidx.lifecycle.ViewModel
import com.lutalic.luboard.core.navigator.IntermediateNavigator
import com.lutalic.luboard.core.navigator.Navigator
import com.lutalic.luboard.core.uiactions.UiActions

/**
 * Implementation of [Navigator] and [UiActions].
 * It is based on activity view-model because instances of [Navigator] and [UiActions]
 * should be available from fragments' view-models (usually they are passed to the view-model constructor).
 */
class ActivityScopeViewModel(
    val uiActions: UiActions,
    val navigator: IntermediateNavigator
) : ViewModel(),
    Navigator by navigator,
    UiActions by uiActions {

    override fun onCleared() {
        super.onCleared()
        navigator.clearNavigatorActions()
    }

}