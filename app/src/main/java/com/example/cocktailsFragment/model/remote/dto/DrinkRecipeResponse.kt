package com.example.cocktailsFragment.model.remote.dto

/**
 * Drink recipe response.
 *
 * @property drinks
 * @constructor Create empty Drink recipe response
 */
data class DrinkRecipeResponse(
    val drinks: List<SpecificRecipeDTO>
)
