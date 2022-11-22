package com.example.cocktailsFragment.viewmodel

import com.example.cocktailsFragment.model.entity.Drink
import com.example.cocktailsFragment.model.repository.RepositoryImpl
import com.example.cocktailsFragment.util.Resource
import com.example.cocktailsFragment.utilTest.CoroutinesTestExtension
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

/**
 * Bob view model test.
 * @constructor Create empty Bob view model test
 */
internal class CategoryListViewModelTest {
    @RegisterExtension
    private val coroutinesTestExtension = CoroutinesTestExtension()
    private val repo = mockk<RepositoryImpl>()

    @Test
    @DisplayName("Testing viewmodel for initial lists to load")
    fun testViewModel() = runTest(coroutinesTestExtension.dispatcher) {
        // Given
        val list = listOf(Drink(strCategory = ""))
        val fakeResponse = Resource.Success(list)

        coEvery { repo.getCategoryList() } coAnswers { fakeResponse }

        val vm = CategoryListViewModel(repo)

        // Then
        assertTrue(vm.categoryList.value is Resource.Loading)
        vm.getCategoryList()

        val result = vm.categoryList.value
        assertEquals(result, fakeResponse)
    }
}
