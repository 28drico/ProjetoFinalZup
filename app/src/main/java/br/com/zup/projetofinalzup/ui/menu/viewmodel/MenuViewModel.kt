package br.com.zup.projetofinalzup.ui.menu.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.projetofinalzup.data.model.CardapioResult
import br.com.zup.projetofinalzup.domain.singleliveevent.SingleLiveEvent
import br.com.zup.projetofinalzup.domain.usecase.DishesUseCase
import br.com.zup.projetofinalzup.ui.FALHA_DESFAVORITA
import br.com.zup.projetofinalzup.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MenuViewModel(application: Application) : AndroidViewModel(application){

    private val dishesUseCase = DishesUseCase(application)
    val cardapioFavoritedState = SingleLiveEvent<ViewState<CardapioResult>>()
    val cardapioDisfavorState = SingleLiveEvent<ViewState<CardapioResult>>()


    fun disfavorCardapio(cardapio: CardapioResult) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    dishesUseCase.updateCardapioFavorite(cardapio)
                }
                cardapioDisfavorState.value = response
            } catch (ex: Exception) {
                cardapioDisfavorState.value =
                    ViewState.Error(Throwable(FALHA_DESFAVORITA))
            }
        }
    }


    fun updateCardapioFavorited(cardapioResult: CardapioResult) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    dishesUseCase.updateCardapioFavorite(cardapioResult)
                }
                cardapioFavoritedState.value = response
            } catch (ex: Exception) {
                cardapioFavoritedState.value =
                    ViewState.Error(Throwable("Não foi possível atualizar o cardapio!"))
            }
        }
    }

}