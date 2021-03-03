package com.maikkkko1.form_validator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.maikkkko1.form_validators.FormValidator
import com.maikkkko1.form_validators.ValidatorRule
import com.maikkkko1.form_validators.entity.FormValidatorDefaultInput
import com.maikkkko1.form_validators.entity.FormValidatorMaterialInput

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonMaterial = findViewById<Button>(R.id.buttonValidateMaterial)
        val buttonDefault = findViewById<Button>(R.id.buttonValidateDefault)

        val materialInputContainer = findViewById<TextInputLayout>(R.id.materialInputContainer)
        val materialInput = findViewById<TextInputEditText>(R.id.materialInput)

        val defaultInput = findViewById<EditText>(R.id.defaultInput)

        val validationRules: List<ValidatorRule> = listOf(ValidatorRule.MustNotBeEmptyOrNull)

        val materialInputsToValidate = mutableListOf(
                FormValidatorMaterialInput(
                        input = materialInput,
                        container = materialInputContainer,
                        rules = validationRules
                )
        )

        buttonMaterial.setOnClickListener {
            val isValidForm = FormValidator.validate(materialInputsToValidate, true)

            Toast.makeText(this, "Is Material Valid: ${isValidForm}", Toast.LENGTH_LONG).show()
        }

        val defaultInputsToValidate = mutableListOf(
                FormValidatorDefaultInput(
                        input = defaultInput,
                        rules = validationRules
                )
        )


        buttonDefault.setOnClickListener {
            val isValidForm = FormValidator.validate(defaultInputsToValidate, true)

            Toast.makeText(this, "Is Default Valid: ${isValidForm}", Toast.LENGTH_LONG).show()
        }
    }
}