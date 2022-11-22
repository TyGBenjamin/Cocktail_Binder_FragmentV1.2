package com.example.cocktails_fragment.repository

import com.example.cocktails_fragment.model.entity.Drink
import com.example.cocktails_fragment.model.entity.DrinkByCategory
import com.example.cocktails_fragment.model.entity.SpecificRecipe
import com.example.cocktails_fragment.util.Resource

/**
 * Repository Abstract.
 *
 * @constructor Create empty Repository
 */
interface Repository {
    /**
     * Get category list from API Request.
     *
     * @param categoryList
     * @return
     */
    suspend fun getCategoryList(categoryList: String = "list"): Resource<List<Drink>>

    /**
     * Shows list of drinks under category chosen by user.
     *
     * @param categoryName
     * @return
     */
    suspend fun getSelectedCategoryDrinks(categoryName: String): Resource<List<DrinkByCategory>>

    /**
     * Get drink detail recipes for selected drink.
     *
     * @param drinkName
     * @return
     */
    suspend fun getDrinkDetailRecipe(drinkName: String): Resource<List<SpecificRecipe>>
}
