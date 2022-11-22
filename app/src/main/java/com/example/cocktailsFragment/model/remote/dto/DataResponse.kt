package com.example.cocktailsFragment.model.remote.dto

/**
 * Data response for holding data objects from initial request from API.
 *
 * @property drinks
 * @constructor Create empty Data response
 */
@kotlinx.serialization.Serializable
data class DataResponse(
    val drinks: List<DrinkDTO>
)
