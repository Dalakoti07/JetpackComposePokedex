package com.plcoding.jetpackcomposepokedex

import com.plcoding.jetpackcomposepokedex.data.remote.PokeApi
import com.plcoding.jetpackcomposepokedex.data.remote.responses.Pokemon
import com.plcoding.jetpackcomposepokedex.data.remote.responses.PokemonList
import com.plcoding.jetpackcomposepokedex.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
) {

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>{
        val response = try{
            api.getPokemonList(limit = limit, offset = offset)
        } catch (e: Exception){
            return Resource.Error("An error occured")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon>{
        val response = try{
            api.getPokemonInfo(pokemonName)
        } catch (e: Exception){
            return Resource.Error(e.message ?: "Unknown error occured ")
        }
        return Resource.Success(response)
    }

}