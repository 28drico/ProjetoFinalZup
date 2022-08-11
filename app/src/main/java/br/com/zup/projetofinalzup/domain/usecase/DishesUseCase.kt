package br.com.zup.projetofinalzup.domain.usecase

import android.app.Application
import br.com.zup.projetofinalzup.data.datasource.local.FavoriteListDatabase
import br.com.zup.projetofinalzup.data.model.CardapioResult
import br.com.zup.projetofinalzup.domain.repository.Repository
import br.com.zup.projetofinalzup.ui.viewstate.ViewState

class DishesUseCase(application: Application) {
    private val dao = FavoriteListDatabase.getDatabase(application).favoriteListDAO()
    private val repository = Repository(dao)



    suspend fun updateCardapioFavorite(cardapio: CardapioResult): ViewState<CardapioResult>{
        return try {
            repository.updateCardapioFavorited(cardapio)
            ViewState.Success(cardapio)
        }catch (ex: Exception){
            ViewState.Error(Exception("Não foi possível atualizar o filme!"))
        }
    }

}