package com.example.cocktailsFragment.model.repository

import com.example.cocktailsFragment.model.entity.Drink
import com.example.cocktailsFragment.model.entity.DrinkByCategory
import com.example.cocktailsFragment.model.entity.SpecificRecipe
import com.example.cocktailsFragment.model.mapper.DrinkByCategoryMapper
import com.example.cocktailsFragment.model.mapper.DrinkCategoryListMapper
import com.example.cocktailsFragment.model.mapper.SpecificRecipeMapper
import com.example.cocktailsFragment.model.remote.ApiService
import com.example.cocktailsFragment.repository.Repository
import com.example.cocktailsFragment.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Repository impl for handling actions and retrieveing data for viewmodel and view.
 *
 * @property api
 * @constructor Create empty Repository impl
 */
class RepositoryImpl @Inject constructor(private val api: ApiService) : Repository {
    //    private val apiInstance by lazy { RetrofitClass.getApiService() }
    private val mapper = DrinkCategoryListMapper()
    private val mapperByCategory = DrinkByCategoryMapper()
    private val recipeMapper = SpecificRecipeMapper()

    override suspend fun getCategoryList(categoryList: String): Resource<List<Drink>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val res = api.getCategoryList("list")
                if (res.isSuccessful && res.body() != null) {
                    println(res.body())
                    Resource.Success(res.body()!!.drinks.map { mapper(it) })
                } else {
                    Resource.Error(res.message())
                }
            } catch (e: IllegalAccessError) {
                Resource.Error(e.message.toString())
            }
        }

    override suspend fun getSelectedCategoryDrinks(categoryName: String): Resource<List<DrinkByCategory>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val res = api.getSelectedCategoryDrinks(categoryName)
                if (res.isSuccessful && res.body() != null) {
                    println(res.body())
                    Resource.Success(res.body()!!.drinks.map { mapperByCategory(it) })
                } else {
                    Resource.Error(res.message())
                }
            } catch (e: IllegalAccessError) {
                Resource.Error(e.message.toString())
            }
        }

    override suspend fun getDrinkDetailRecipe(drinkName: String): Resource<List<SpecificRecipe>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val res = api.getRecipe(drinkName)
                if (res.isSuccessful && res.body() != null) {
                    println(res.body())
                    Resource.Success(res.body()!!.drinks.map { recipeMapper(it) })
                } else {
                    Resource.Error(res.message())
                }
            } catch (e: IllegalAccessError) {
                Resource.Error(e.message.toString())
            }
        }
}
