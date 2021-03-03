package com.maikkkko1.form_validators

sealed class ValidatorRule {
    object MustHaveLetterAndNumbers: ValidatorRule()
    object MustNotBeEmptyOrNull: ValidatorRule()
    object MustBeValidEmail: ValidatorRule()

    data class MustHaveTheMinimumLength(val size: Int): ValidatorRule()
}