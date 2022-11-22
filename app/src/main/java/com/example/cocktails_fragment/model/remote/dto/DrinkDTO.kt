package com.example.cocktails_fragment.model.remote.dto

/**
 * Drink DTO is structure for holding data rom this API request endpoint.
 *
 * @property strCategory
 * @constructor Create empty Drink d t o
 */
@kotlinx.serialization.Serializable
data class DrinkDTO(
    val strCategory: String
)
