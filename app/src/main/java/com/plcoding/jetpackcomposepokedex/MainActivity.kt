package com.plcoding.jetpackcomposepokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.plcoding.jetpackcomposepokedex.pokemonDetail.PokemonDetailScreen
import com.plcoding.jetpackcomposepokedex.pokemonList.PokemonListScreen
import com.plcoding.jetpackcomposepokedex.pokemonList.PokemonListViewModel
import com.plcoding.jetpackcomposepokedex.ui.theme.JetpackComposePokedexTheme
import com.plcoding.jetpackcomposepokedex.utils.Constants.ARGS.DOMINANT_COLOR
import com.plcoding.jetpackcomposepokedex.utils.Constants.ARGS.POKEMON_NAME
import com.plcoding.jetpackcomposepokedex.utils.Constants.SCREENS.POKEMON_LIST_SCREEN
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //todo find a better way to pass viewmodel to composables
    private val viewModel: PokemonListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = POKEMON_LIST_SCREEN) {
                    composable(POKEMON_LIST_SCREEN) {
                        PokemonListScreen(navController = navController, viewModel = viewModel)
                    }
                    composable("pokemon_detail_screen/{dominantColor}/{pokemonName}",
                        arguments = listOf(
                            navArgument(DOMINANT_COLOR) {
                                type = NavType.IntType
                            },
                            navArgument(POKEMON_NAME) {
                                type = NavType.StringType
                            }
                        )) {
                        val dominantColor = remember {
                            val color = it.arguments?.getInt(DOMINANT_COLOR)
                            color?.let { Color(it) } ?: Color.White
                        }
                        val pokemonName = remember {
                            it.arguments?.getString(POKEMON_NAME)
                        }
                        PokemonDetailScreen(
                            dominantColor = dominantColor,
                            pokemonName = pokemonName ?: "",
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
