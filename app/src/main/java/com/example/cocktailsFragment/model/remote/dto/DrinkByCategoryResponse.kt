package com.example.cocktailsFragment.model.remote.dto

/**
 * Drink by category response.
 *
 * @property drinks
 * @constructor Create empty Drink by category response
 */
@kotlinx.serialization.Serializable
data class DrinkByCategoryResponse(
    val drinks: List<DrinkByCategoryDTO>
)
