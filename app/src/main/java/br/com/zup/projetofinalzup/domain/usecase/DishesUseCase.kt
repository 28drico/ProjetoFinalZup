package br.com.zup.projetofinalzup.domain.usecase
/**
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
            repository.insertDatabaseList(response.menu)
            ViewState.Success(response.menu)
        }catch(e:Exception){
            getLocalList()
        }
    }

    suspend fun getFavoritedList():ViewState<List<MenuItem>>{
        return try{
            val favoritedItems = repository.getFavoritedList()
            ViewState.Success(favoritedItems)
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
}**/