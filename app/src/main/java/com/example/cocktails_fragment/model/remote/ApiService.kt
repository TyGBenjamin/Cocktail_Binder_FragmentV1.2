package com.example.cocktails_fragment.model.remote

import com.example.cocktails_fragment.model.remote.dto.DataResponse
import com.example.cocktails_fragment.model.remote.dto.DrinkByCategoryResponse
import com.example.cocktails_fragment.model.remote.dto.DrinkRecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Api service for retrieving request from API.
 *
 * @constructor Create empty Api service
 */
interface ApiService {

    @GET(LIST_ENDPOINT)
    suspend fun getCategoryList(@Query("c") list: String="list"): Response<DataResponse>

    @GET(DRINK_IN_CATEGORY)
    suspend fun getSelectedCategoryDrinks(@Query("c") filter:String): Response<DrinkByCategoryResponse>

    @GET(RECIPE_ENDPOINT)
    suspend fun getRecipe(@Query("s") search:String): Response<DrinkRecipeResponse>



    companion object {
        private const val LIST_ENDPOINT = "list.php"
        private const val DRINK_IN_CATEGORY = "filter.php"
        private const val RECIPE_ENDPOINT = "search.php"
    }

}
