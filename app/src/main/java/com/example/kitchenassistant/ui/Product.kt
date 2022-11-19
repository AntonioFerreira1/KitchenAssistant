package com.example.kitchenassistant.ui

data class Product(
    val title:String,
    val quantity: Int,
    val unit: Unit
)

enum class Unit {
    GRAMS, KILOGRAM, UNIT, PACKAGE, LITERS, MILLILITERS
}
