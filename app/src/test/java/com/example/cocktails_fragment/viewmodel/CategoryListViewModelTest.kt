package com.example.cocktails_fragment.viewmodel

import com.example.cocktails_fragment.model.entity.Drink
import com.example.cocktails_fragment.model.repository.RepositoryImpl
import com.example.cocktails_fragment.util.Resource
import com.example.cocktails_fragment.utilTest.CoroutinesTestExtension
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

        coEvery { repo.getCategoryList() } coAnswers { fakeResponse}

        val vm = CategoryListViewModel(repo)

        // Then
        assertTrue(vm.categoryList.value is Resource.Loading)
        vm.getCategoryList()

        val result = vm.categoryList.value
        assertEquals(result, fakeResponse)
    }
}
