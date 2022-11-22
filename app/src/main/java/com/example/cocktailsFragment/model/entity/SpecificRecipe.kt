package com.example.cocktailsFragment.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Specific recipe entity to hold data converted from DTO for UI purposes.
 *
 * @property dateModified
 * @property idDrink
 * @property strAlcoholic
 * @property strCategory
 * @property strCreativeCommonsConfirmed
 * @property strDrink
 * @property strDrinkAlternate
 * @property strDrinkThumb
 * @property strGlass
 * @property strIBA
 * @property strImageAttribution
 * @property strImageSource
 * @property strIngredient1
 * @property strIngredient10
 * @property strIngredient11
 * @property strIngredient12
 * @property strIngredient13
 * @property strIngredient14
 * @property strIngredient15
 * @property strIngredient2
 * @property strIngredient3
 * @property strIngredient4
 * @property strIngredient5
 * @property strIngredient6
 * @property strIngredient7
 * @property strIngredient8
 * @property strIngredient9
 * @property strInstructions
 * @property strInstructionsDE
 * @property strInstructionsES
 * @property strInstructionsFR
 * @property strInstructionsIT
 * @property strInstructionszhHANS
 * @property strInstructionszhHANT
 * @property strMeasure1
 * @property strMeasure10
 * @property strMeasure11
 * @property strMeasure12
 * @property strMeasure13
 * @property strMeasure14
 * @property strMeasure15
 * @property strMeasure2
 * @property strMeasure3
 * @property strMeasure4
 * @property strMeasure5
 * @property strMeasure6
 * @property strMeasure7
 * @property strMeasure8
 * @property strMeasure9
 * @property strTags
 * @property strVideo
 * @constructor Create empty Specific recipe
 */
@kotlinx.serialization.Serializable
data class SpecificRecipe(
    val dateModified: String? = "",
    val idDrink: String,
    val strAlcoholic: String,
    val strCategory: String,
    val strCreativeCommonsConfirmed: String,
    val strDrink: String,
    val strDrinkAlternate: String?,
    val strDrinkThumb: String,
    val strGlass: String,
    val strIBA: String?,
    val strImageAttribution: String?,
    val strImageSource: String? = "",
    val strIngredient1: String,
    val strIngredient10: String? = "",
    val strIngredient11: String? = "",
    val strIngredient12: String? = "",
    val strIngredient13: String? = "",
    val strIngredient14: String? = "",
    val strIngredient15: String? = "",
    val strIngredient2: String,
    val strIngredient3: String,
    val strIngredient4: String,
    val strIngredient5: String,
    val strIngredient6: String,
    val strIngredient7: String,
    val strIngredient8: String? = "",
    val strIngredient9: String? = "",
    val strInstructions: String,
    val strInstructionsDE: String? = "",
    val strInstructionsES: String? = "",
    val strInstructionsFR: String? = "",
    val strInstructionsIT: String,
    @SerializedName("strInstructionsZH-HANS")
    val strInstructionszhHANS: String? = "",
    @SerializedName("strInstructionsZH-HANT")
    val strInstructionszhHANT: String? = "",
    val strMeasure1: String,
    val strMeasure10: String? = "",
    val strMeasure11: String? = "",
    val strMeasure12: String? = "",
    val strMeasure13: String? = "",
    val strMeasure14: String? = "",
    val strMeasure15: String? = "",
    val strMeasure2: String,
    val strMeasure3: String,
    val strMeasure4: String,
    val strMeasure5: String,
    val strMeasure6: String,
    val strMeasure7: String,
    val strMeasure8: String,
    val strMeasure9: String,
    val strTags: String,
    val strVideo: String
)
