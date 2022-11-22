package com.example.cocktails_fragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktails_fragment.model.entity.DrinkByCategory
import com.example.cocktails_fragment.model.repository.RepositoryImpl
import com.example.cocktails_fragment.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Drink by category view model.
 *
 * @constructor Create empty Drink by category view model
 */
@HiltViewModel
class DrinkByCategoryViewModel@Inject constructor(private val repo: RepositoryImpl): ViewModel() {
//    private val repo = RepositoryImpl
    private val _drinkList: MutableStateFlow<Resource<List<DrinkByCategory>>> = MutableStateFlow(Resource.Loading)
    val drinkList = _drinkList.asStateFlow()

    /**
     * Get drink by category.
     *
     * @param list
     */
    fun getDrinkByCategory(list:String ="cocktail"){
        viewModelScope.launch {
            _drinkList.value = repo.getSelectedCategoryDrinks(list)
            println("THIS IS VALUE OF FLOW ${repo.getCategoryList(list)}")
        }
    }
}