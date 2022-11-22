package com.example.cocktails_fragment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import coil.compose.rememberAsyncImagePainter
import com.example.cocktails_fragment.R
import com.example.cocktails_fragment.model.entity.SpecificRecipe
import com.example.cocktails_fragment.ui.theme.Cocktails_FragmentTheme
import com.example.cocktails_fragment.util.Resource
import com.example.cocktails_fragment.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Recipe fragment
 *
 * @constructor Create empty Recipe fragment
 */
@AndroidEntryPoint
class RecipeFragment : Fragment() {
    private val recipeViewModel by activityViewModels<RecipeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val recipe = recipeViewModel.recipe.collectAsState().value
                println("IS $recipe KEEPING ITS VALUE?")
                Cocktails_FragmentTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        println("DRINKS ARE SHOWN AS $recipe")

                        when (recipe) {
                            is Resource.Error -> ErrorIndicator()
                            Resource.Loading -> ProgressIndicator()
                            is Resource.Success -> {
                                Column() {
                                    LazyColumn(modifier = Modifier.padding(5.dp)) {
                                        items(items = recipe.data) { recipe ->
                                            RecipeCard(recipe = recipe)
                                            Button(
                                                onClick = { findNavController().navigate(R.id.dashboardFragment) },
                                                modifier = Modifier
                                                    .align(alignment = Alignment.CenterHorizontally)
                                                    .padding(horizontal = 140.dp)
                                            ) {
                                                Text(text = "Home")
                                            }

                                        }
                                    }
                                }

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
fun RecipeCard(
    recipe: SpecificRecipe,
) {
    val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }
    Card(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.Black)
        .clickable {
//            navigate()
            setSnackBarState(!snackbarVisibleState)
            println("Card has been   HERE ON NEW PAGE CLICKED")
        }
        .padding(5.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 20.dp, start = 10.dp
                )
        ) {
            Column(modifier = Modifier.padding(start = 15.dp, end = 15.dp)) {
                Text(
                    text = "CLICK ON THE CARD TO SEE RECIPE",
                    modifier = Modifier.padding(vertical = 15.dp)
                )
                if (snackbarVisibleState) {
                    Snackbar(

                        action = {
                            Button(onClick = {}) {
                                Text("MyAction")
                            }
                        },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Column() {

                            Text(text = "you need" + " " + recipe.strIngredient1 + " and  " + recipe.strIngredient2)
                            Text(text = "you need" + " " + recipe.strIngredient3 + " and " + recipe.strIngredient4)
                            Text(text = "you need" + " " + recipe.strIngredient5 + " and " + recipe.strIngredient6)
                            Text(text = "you need" + " " + recipe.strIngredient7 + " and " + recipe.strIngredient8)
                        }
                    }
                }
                Row {
                    Text(text = recipe.strAlcoholic)
                    Text(text = recipe.strCategory)
                }
                Text(text = recipe.strDrink)
                Image(
                    painter = rememberAsyncImagePainter(recipe.strDrinkThumb),
                    contentDescription = "",
                    modifier = Modifier.size(60.dp)
                )
            }
        }
    }
}

