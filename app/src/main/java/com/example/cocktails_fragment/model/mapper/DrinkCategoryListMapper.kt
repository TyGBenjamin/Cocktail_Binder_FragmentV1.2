package com.example.cocktails_fragment.model.mapper

import com.example.cocktails_fragment.model.entity.Drink
import com.example.cocktails_fragment.model.remote.dto.DrinkDTO

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
