package com.example.cocktails_fragment.model.remote.dto

/**
 * Drink by category DTO for holding objects retrieved from API request.
 *
 * @property idDrink
 * @property strDrink
 * @property strDrinkThumb
 * @constructor Create empty Drink by category d t o
 */
@kotlinx.serialization.Serializable
data class DrinkByCategoryDTO(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
)
