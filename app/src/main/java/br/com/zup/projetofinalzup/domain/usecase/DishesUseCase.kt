package br.com.zup.projetofinalzup.domain.usecase

import android.app.Application
import br.com.zup.projetofinalzup.data.datasource.local.FavoriteListDatabase
import br.com.zup.projetofinalzup.data.datasource.model.MenuItem
import br.com.zup.projetofinalzup.domain.repository.Repository
import br.com.zup.projetofinalzup.ui.ERROR
import br.com.zup.projetofinalzup.ui.viewstate.ViewState

class DishesUseCase(application: Application) {
    private val dao = FavoriteListDatabase.getDatabase(application).favoriteListDAO()
    private val repository = Repository(dao)

    suspend fun getMenuAPI(): ViewState<List<MenuItem>> {
        return try{
            val response = repository.getMenuAPI()
            //pegar lista do database (salvando tudo no database, não só favoritos)
            repository.insertDatabaseList(response.menu)
            ViewState.Success(response.menu)
        }catch(e:Exception){
            //pegar lista do database (salvando tudo no database, não só favoritos)
            getLocalList()
        }
    }

    suspend fun getFavoritedList():ViewState<List<MenuItem>>{
        return try{
            val charactersFavorited = repository.getFavoritedList()
            ViewState.Success(charactersFavorited)
        }catch(e:Exception){
            ViewState.Error(Exception(ERROR))
        }
    }

    suspend fun updateFavoritedList(menu:MenuItem):ViewState<MenuItem>{
        return try{
            repository.updateFavoritedList(menu)
            ViewState.Success(menu)
        }catch(e:Exception){
            ViewState.Error(Exception(ERROR))
        }
    }

    suspend fun getLocalList():ViewState<List<MenuItem>>{
        return try{
            val menu = repository.getLocalList()
            ViewState.Success(menu)
        }catch(e:Exception){
            ViewState.Error(Exception(ERROR))
        }
    }
}