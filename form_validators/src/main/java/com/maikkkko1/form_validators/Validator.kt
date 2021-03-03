package com.maikkkko1.form_validators

import android.text.TextUtils
import android.util.Patterns
import com.maikkkko1.form_validators.entity.RuleValidationResult

class Validator constructor(private val value: String?) {
    fun isValidBasedOnRules(rulesToValidate: List<ValidatorRule>): RuleValidationResult {
        for (rule in rulesToValidate) {
            when (rule) {
                is ValidatorRule.MustHaveLetterAndNumbers -> {
                    val regex = value?.matches("\\S*(\\S*([a-zA-Z]\\S*[0-9])|([0-9]\\S*[a-zA-Z]))\\S*".toRegex()) ?: false

                    if (!regex) {
                        return RuleValidationResult(isValid = false, message = "Must contain letters and numbers!")
                    }
                }

                is ValidatorRule.MustHaveTheMinimumLength -> {
                    if (value?.length ?: 0 < rule.size) {
                        return RuleValidationResult(isValid = false, message = "Size must be at least ${rule.size}!")
                    }
                }

                is ValidatorRule.MustNotBeEmptyOrNull -> {
                    if (value.isNullOrEmpty()) {
                        return RuleValidationResult(isValid = false, message = "Must not be empty or null!")
                    }
                }

                is ValidatorRule.MustBeValidEmail -> {
                    if (!isValidEmail(value)) {
                        return RuleValidationResult(isValid = false, message = "Must be a valid email!")
                    }
                }
            }
        }

        return RuleValidationResult(isValid = true, message = null)
    }

    private fun isValidEmail(target: CharSequence?): Boolean =
            !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target)
                    .matches()
}