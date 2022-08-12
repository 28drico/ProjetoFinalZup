package br.com.zup.projetofinalzup.domain.usecase

import br.com.zup.projetofinalzup.data.datasource.local.AppApplication
import br.com.zup.projetofinalzup.data.datasource.model.MenuItem
import br.com.zup.projetofinalzup.domain.repository.Repository
import br.com.zup.projetofinalzup.ui.viewstate.ViewState

class DishUseCase{
    private val repository = Repository
    private val dao = AppApplication.getdatabase().favoriteListDAO()

    suspend fun getFavoritedList(): ViewState<List<MenuItem>> {
        return try{
            val menu = repository.getFavoritedList()
            ViewState.success(menu)
        }catch(e:Exception){
            ViewState.error(null, e.message)
        }
    }

    suspend fun favoriteItem(item:MenuItem):ViewState<MenuItem>{
        return try{
            repository.insertFavoriteItem(item)
            ViewState.success(item)
        }catch(e:Exception){
            ViewState.error(null,e.message)
        }
    }
}