package com.maikkkko1.form_validators

import android.graphics.drawable.Drawable
import com.maikkkko1.form_validators.entity.FormValidatorDefaultInput
import com.maikkkko1.form_validators.entity.FormValidatorMaterialInput

object FormValidator {
    fun validate(inputs: List<FormValidatorMaterialInput>, showInputError: Boolean = false, errorIcon: Drawable? = null): Boolean =
            validateMaterialInput(inputs = inputs, showInputError = showInputError, errorIcon = errorIcon)

    fun validate(inputs: List<FormValidatorDefaultInput>, showInputError: Boolean = false): Boolean =
            validateDefaultInput(inputs = inputs, showInputError = showInputError)

    private fun validateMaterialInput(inputs: List<FormValidatorMaterialInput>, showInputError: Boolean, errorIcon: Drawable? = null): Boolean {
        var isAtLeastOneInputWithError = false

        for (item in inputs) {
            val value = item.input.text.toString()

            val validateForm = Validator(value).isValidBasedOnRules((item.rules))

            if (!validateForm.isValid) {
                if (!isAtLeastOneInputWithError) {
                    isAtLeastOneInputWithError = true
                }

                if (showInputError) {
                    item.container.apply {
                        if (errorIcon != null) errorIconDrawable = errorIcon

                        error = validateForm.message
                    }
                    item.container.error = validateForm.message
                } else item.container.error = null
            } else {
                item.container.error = null
            }
        }

        return !isAtLeastOneInputWithError
    }

    private fun validateDefaultInput(inputs: List<FormValidatorDefaultInput>, showInputError: Boolean): Boolean {
        var isAtLeastOneInputWithError = false

        for (item in inputs) {
            val value = item.input.text.toString()

            val validateForm = Validator(value).isValidBasedOnRules((item.rules))

            if (!validateForm.isValid) {
                if (!isAtLeastOneInputWithError) {
                    isAtLeastOneInputWithError = true
                }

                if (showInputError) item.input.error = validateForm.message else item.input.error = null
            } else {
                item.input.error = null
            }
        }

        return !isAtLeastOneInputWithError
    }
}