package com.example.cocktails_fragment.model.repository

import com.example.cocktails_fragment.model.entity.Drink
import com.example.cocktails_fragment.model.entity.DrinkByCategory
import com.example.cocktails_fragment.model.entity.SpecificRecipe
import com.example.cocktails_fragment.model.mapper.DrinkByCategoryMapper
import com.example.cocktails_fragment.model.mapper.DrinkCategoryListMapper
import com.example.cocktails_fragment.model.mapper.SpecificRecipeMapper
import com.example.cocktails_fragment.model.remote.ApiService
import com.example.cocktails_fragment.repository.Repository
import com.example.cocktails_fragment.util.Resource
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


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

