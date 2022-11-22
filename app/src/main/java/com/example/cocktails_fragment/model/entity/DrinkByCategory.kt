package com.example.cocktails_fragment.model.entity

/**
 * Drink by category.
 *
 * @property idDrink
 * @property strDrink
 * @property strDrinkThumb
 * @constructor Create empty Drink by category
 */
data class DrinkByCategory(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
)
