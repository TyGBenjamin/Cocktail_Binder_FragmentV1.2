package com.example.cocktailsFragment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cocktailsFragment.ui.theme.Cocktails_FragmentTheme
import com.example.cocktailsFragment.util.Resource
import com.example.cocktailsFragment.viewmodel.DrinkByCategoryViewModel
import com.example.cocktailsFragment.viewmodel.RecipeViewModel
import com.example.cocktails_fragment.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Category fragment for displaying list of selected drinks from.
 *
 * @constructor Create instance of [Categoryfragment]
 */
@AndroidEntryPoint
class CategoryFragment : Fragment() {
    private val viewModel by activityViewModels<DrinkByCategoryViewModel>()
    private val recipeViewModel by activityViewModels<RecipeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val drinkList2 = viewModel.drinkList.collectAsState().value

                Cocktails_FragmentTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        println("DRINKS ARE SHOWN AS $drinkList2")

                        when (drinkList2) {
                            is Resource.Error -> ErrorIndicator()
                            Resource.Loading -> ProgressIndicator()
                            is Resource.Success -> {
                                val drinkList = drinkList2.data
                                HomeScreen2(
                                    navigate = { findNavController().navigate(R.id.recipeFragment) },
                                    drinks = drinkList,
                                    viewModel = recipeViewModel
                                )
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }
}
