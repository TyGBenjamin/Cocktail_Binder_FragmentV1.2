package com.example.cocktails_fragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktails_fragment.model.entity.Drink
import com.example.cocktails_fragment.model.repository.RepositoryImpl
import com.example.cocktails_fragment.util.Resource
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
//    private val repo = RepositoryImpl
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
            println("THIS IS VALUE OF FLOW ${repo.getCategoryList(list)}")
        }
    }
}
