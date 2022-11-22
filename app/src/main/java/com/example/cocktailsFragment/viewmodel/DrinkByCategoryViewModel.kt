package com.example.cocktailsFragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailsFragment.model.entity.DrinkByCategory
import com.example.cocktailsFragment.model.repository.RepositoryImpl
import com.example.cocktailsFragment.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Drink by category view model.
 *
 * @constructor Create empty Drink by category view model
 */
@HiltViewModel
class DrinkByCategoryViewModel@Inject constructor(private val repo: RepositoryImpl) : ViewModel() {
    private val _drinkList: MutableStateFlow<Resource<List<DrinkByCategory>>> = MutableStateFlow(Resource.Loading)
    val drinkList = _drinkList.asStateFlow()

    /**
     * Get drink by category.
     *
     * @param list
     */
    fun getDrinkByCategory(list: String = "cocktail") {
        viewModelScope.launch {
            _drinkList.value = repo.getSelectedCategoryDrinks(list)
        }
    }
}
