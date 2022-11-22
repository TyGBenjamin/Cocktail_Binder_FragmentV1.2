package com.example.cocktailsFragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailsFragment.model.entity.Drink
import com.example.cocktailsFragment.model.repository.RepositoryImpl
import com.example.cocktailsFragment.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Category list view model.
 *
 * @constructor Create empty Category list view model
 */
@HiltViewModel
class CategoryListViewModel @Inject constructor(private val repo: RepositoryImpl) : ViewModel() {
    private val _categoryList: MutableStateFlow<Resource<List<Drink>>> = MutableStateFlow(Resource.Loading)
    val categoryList = _categoryList.asStateFlow()

    /**
     * Get category list function.
     *
     * @param list
     */
    fun getCategoryList(list: String = "list") {
        viewModelScope.launch {
            _categoryList.value = repo.getCategoryList(list)
        }
    }
}
