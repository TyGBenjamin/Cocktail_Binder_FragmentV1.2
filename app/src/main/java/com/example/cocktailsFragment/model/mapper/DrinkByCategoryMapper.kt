package com.example.cocktailsFragment.model.mapper

import com.example.cocktailsFragment.model.entity.DrinkByCategory
import com.example.cocktailsFragment.model.remote.dto.DrinkByCategoryDTO

/**
 * Drink Category Mapper mapper for mapping DTO to data class objects.
 *
 * @constructor Create empty Origin mapper
 */
class DrinkByCategoryMapper : Mapper<DrinkByCategoryDTO, DrinkByCategory> {
    override fun invoke(dto: DrinkByCategoryDTO): DrinkByCategory {
        return with(dto) {
            DrinkByCategory(
                idDrink = idDrink ?: "",
                strDrink = strDrink,
                strDrinkThumb = strDrinkThumb
            )
        }
    }
}
