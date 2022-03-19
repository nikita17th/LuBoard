package com.lutalic.luboard.core.navigator

import com.lutalic.luboard.core.utils.ResourceActions
import com.lutalic.luboard.core.views.BaseScreen

/**
 * Mediator that holds nav actions in the queue if real navigator is not active.
 */
class IntermediateNavigator : Navigator {

    private val targetNavigator = ResourceActions<Navigator>()

    override fun launch(screen: BaseScreen) = targetNavigator.tryInvoke {
        it.launch(screen)
    }

    override fun goBack(result: Any?) = targetNavigator.tryInvoke {
        it.goBack(result)
    }

    fun setTargetNavigator(navigator: Navigator?) {
        targetNavigator.navigatorResource = navigator
    }

    fun clearNavigatorActions() {
        targetNavigator.clearActions()
    }

}