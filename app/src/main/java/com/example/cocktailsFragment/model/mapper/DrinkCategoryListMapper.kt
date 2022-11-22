package com.example.cocktailsFragment.model.mapper

import com.example.cocktailsFragment.model.entity.Drink
import com.example.cocktailsFragment.model.remote.dto.DrinkDTO

/**
 * drink Category mapper for mapping DTO to data class objects.
 *
 * @constructor Create empty Origin mapper
 */
class DrinkCategoryListMapper : Mapper<DrinkDTO, Drink> {
    override fun invoke(dto: DrinkDTO): Drink {
        return with(dto) {
            Drink(
                strCategory = strCategory ?: ""
            )
        }
    }
}
