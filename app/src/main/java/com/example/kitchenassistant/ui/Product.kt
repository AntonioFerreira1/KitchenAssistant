package com.example.kitchenassistant.ui

data class Product(
    val title:String,
    val quantity: Int,
    val unit: Unit
)

enum class Unit {
    KILOGRAM, UNIT
}
