package pt.hventura.auth.domain

interface PatternValidator {
    fun matches(value: String): Boolean
}