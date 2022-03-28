package com.lutalic.luboard.presentation.main.auth

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.lutalic.luboard.Repositories
import com.lutalic.luboard.presentation.uiactions.AndroidUiActions
import com.lutalic.luboard.utils.viewModelCreator

class ValidateErrorDialogFragment : DialogFragment() {

    private val viewModel by viewModelCreator {
        ValidateErrorDialogViewModel(
            Repositories.accountsRepository,
            AndroidUiActions(requireContext())
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setMessage("Email invalidate")
            .setPositiveButton("Ok") { _, _ -> }
            .setNegativeButton("Send email again") { _, _ ->
                viewModel.sendValidateEmail()
            }
            .create()
    }

    companion object {
        const val TAG = "ValidateEmailErrorDialog"
    }
}