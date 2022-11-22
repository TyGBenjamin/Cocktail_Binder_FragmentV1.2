package com.example.cocktails_fragment.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktails_fragment.model.entity.SpecificRecipe
import com.example.cocktails_fragment.model.repository.RepositoryImpl
import com.example.cocktails_fragment.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Recipe view model.
 *
 * @constructor Create empty Recipe view model
 */
@HiltViewModel
class RecipeViewModel@Inject constructor(private val repo: RepositoryImpl) : ViewModel() {
//    private val repo = RepositoryImpl
    private val _recipe: MutableStateFlow<Resource<List<SpecificRecipe>>> = MutableStateFlow(
        Resource.Loading
    )
    val recipe = _recipe.asStateFlow()

    /**
     * Get detail recipes.
     *
     * @param list
     */
    fun getDetailRecipe(list: String) {
        Log.d(TAG, "getDetailRecipe called")
        viewModelScope.launch {
            _recipe.value = repo.getDrinkDetailRecipe(list)
            Log.d(TAG, "Recipe Flow State: ${recipe.value}")
        }
    }

    companion object {
        const val TAG = "RecipeViewModelLgr"
    }
}
