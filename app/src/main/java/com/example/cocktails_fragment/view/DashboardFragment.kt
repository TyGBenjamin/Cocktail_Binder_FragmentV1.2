package com.example.cocktails_fragment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.compose.rememberAsyncImagePainter
import com.example.cocktails_fragment.R
import com.example.cocktails_fragment.model.entity.Drink
import com.example.cocktails_fragment.model.entity.DrinkByCategory
import com.example.cocktails_fragment.ui.theme.Cocktails_FragmentTheme
import com.example.cocktails_fragment.util.Constants
import com.example.cocktails_fragment.util.Resource
import com.example.cocktails_fragment.viewmodel.CategoryListViewModel
import com.example.cocktails_fragment.viewmodel.DrinkByCategoryViewModel
import com.example.cocktails_fragment.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Dashboard fragment displays initial list of drinks retrieved from API.
 *
 * @constructor Create instance of [Dashboardfragment]
 */
@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private val dashViewModel by viewModels<CategoryListViewModel>()
    private val categoryViewModel by activityViewModels<DrinkByCategoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashViewModel.getCategoryList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // requireActivity()
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Cocktails_FragmentTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        val drinks = dashViewModel.categoryList.collectAsState().value
                        println("DRINKS ARE SHOWN AS $drinks")

                        when (drinks) {
                            is Resource.Error -> ErrorIndicator()
                            Resource.Loading -> ProgressIndicator()
                            is Resource.Success -> {
                                val drinkList = drinks.data
                                HomeScreen(
                                    navigate = { findNavController().navigate(R.id.categoryFragment) },
                                    drinks = drinkList,
                                    viewModel = categoryViewModel
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

@Composable
fun CharCard(
    drink: Drink,
    navigate: () -> Unit,
    viewModel: DrinkByCategoryViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Black)
            .clickable {
                viewModel.getDrinkByCategory(drink.strCategory)
                navigate()
        }
            .padding(
                5.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 20.dp,
                    start = 10.dp
                )
        ) {
            Column(modifier = Modifier.padding(start = 15.dp, end = 15.dp)) {
                Text(text = drink.strCategory)
                Image(
                    painter = rememberAsyncImagePainter(Constants.mainImg + Constants.mainImg2),
                    contentDescription = null,
                    modifier = Modifier
                        .size(145.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun CharCard2(
    drink: DrinkByCategory,
    navigate: () -> Unit,
    viewModel: RecipeViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Black)
            .clickable {
                viewModel.getDetailRecipe(drink.strDrink)
                navigate()
            }
            .padding(
            5.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 20.dp,
                    start = 10.dp
                )
        ) {
            Column(modifier = Modifier.padding(start = 15.dp, end = 15.dp)) {
                Text(text = drink.strDrink)
                Image(
                    painter = rememberAsyncImagePainter(drink.strDrinkThumb),
                    contentDescription = null,
                    modifier = Modifier
                        .size(145.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun ProgressIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "ERROR IS RETRIEVING DATA", fontSize = 40.sp)
    }
}

@Composable
fun HomeScreen(
    viewModel: DrinkByCategoryViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navigate: () -> Unit,
    drinks: List<Drink>
) {
    LazyColumn(state = rememberLazyListState(), modifier = Modifier.padding(5.dp)) {
        items(items = drinks) { drink ->
            CharCard(
                drink = drink,
                navigate = { navigate() },
                viewModel
            )
        }
    }
}

@Composable
fun HomeScreen2(
    viewModel: RecipeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navigate: () -> Unit,
    drinks: List<DrinkByCategory>
) {
    LazyColumn(state = rememberLazyListState(), modifier = Modifier.padding(5.dp)) {
        items(items = drinks) { drink ->
            CharCard2(
                drink = drink,
                navigate = { navigate() },
                viewModel
            )
        }
    }
}
