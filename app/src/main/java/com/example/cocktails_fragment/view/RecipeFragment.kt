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
 * Recipe fragment displays sleetced drinks and associated recipes.
 *
 * @constructor Create insatnce of  [Recipefragment]
 */
@AndroidEntryPoint
class RecipeFragment : Fragment() {
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
                val recipe = recipeViewModel.recipe.collectAsState().value
                println("IS $recipe KEEPING ITS VALUE?")
                Cocktails_FragmentTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        println("DRINKS ARE SHOWN AS $recipe")

                        when (recipe) {
                            is Resource.Error -> ErrorIndicator()
                            Resource.Loading -> ProgressIndicator()
                            is Resource.Success ->
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
    recipe: SpecificRecipe
) {
    val (
        snackbarVisibleState,
        setSnackBarState
    ) = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Black)
            .clickable {
                setSnackBarState(!snackbarVisibleState)
                println("Card has been   HERE ON NEW PAGE CLICKED")
            }
            .padding(
                5.dp
            )
    ) {
        RecipeRowBuild(text = "CLICK ON THE CARD TO SEE RECIPE")
        if (snackbarVisibleState) {
            Snackbar(
                modifier = Modifier.padding(8.dp)
            ) {
                Column() {
                    RecipeText(s1 = recipe.strIngredient1, s2 = recipe.strIngredient2)
                    RecipeText(s1 = recipe.strIngredient3, s2 = recipe.strIngredient4)
                    RecipeText(s1 = recipe.strIngredient5, s2 = recipe.strIngredient6)
                    recipe.strIngredient8?.let {
                        RecipeText(
                            s1 = recipe.strIngredient7,
                            s2 = it
                        )
                    }
                }
            }
        }
        RecipeBottom(
            s1 = recipe.strAlcoholic,
            s2 = recipe.strCategory,
            drinkName = recipe.strDrink,
            imageLink = recipe.strDrinkThumb
        )
    }
}

@Composable
fun RecipeRowBuild(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 20.dp,
                start = 10.dp
            )
    ) {
        Column(
            modifier = Modifier.padding(
                start = 15.dp,
                end = 15.dp
            )
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(vertical = 15.dp)
            )
        }
    }
}

@Composable
fun RecipeBottom(s1: String, s2: String, drinkName: String, imageLink: String) {
    Row {
        Text(text = s1)
        Text(text = s2)
    }
    Text(text = drinkName)
    Image(
        painter = rememberAsyncImagePainter(imageLink),
        contentDescription = "",
        modifier = Modifier.size(60.dp)
    )
}

@Composable
fun RecipeText(s1: String, s2: String) {
    Text(text = "you need" + " " + s1 + " and  " + s2)
}
