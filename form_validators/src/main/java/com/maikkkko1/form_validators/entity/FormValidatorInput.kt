package com.maikkkko1.form_validators.entity

import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.maikkkko1.form_validators.ValidatorRule

data class FormValidatorMaterialInput(
        val input: TextInputEditText,
        val container: TextInputLayout,
        val rules: List<ValidatorRule>
)

data class FormValidatorDefaultInput(
        val input: EditText,
        val rules: List<ValidatorRule>
)
