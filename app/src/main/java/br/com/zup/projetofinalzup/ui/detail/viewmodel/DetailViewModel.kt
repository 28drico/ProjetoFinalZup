package br.com.zup.projetofinalzup.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.zup.projetofinalzup.R
import br.com.zup.projetofinalzup.data.datasource.local.database.AppApplication
import br.com.zup.projetofinalzup.data.model.MenuItem
import br.com.zup.projetofinalzup.domain.repository.Repository
import br.com.zup.projetofinalzup.domain.singleliveevent.SingleLiveEvent
import br.com.zup.projetofinalzup.domain.usecase.DishesUseCase
import br.com.zup.projetofinalzup.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel : ViewModel() {
    private val useCase = DishesUseCase()
    val listState = SingleLiveEvent<ViewState<List<MenuItem>?>>()
    val favorite = SingleLiveEvent<ViewState<MenuItem>>()
    val cart = SingleLiveEvent<ViewState<MenuItem>>()
    val favoriteList = getFavoritedList()


    fun getFavoritedList(): List<MenuItem>? {
        val dao = AppApplication.getdatabase().favoriteListDAO()
         val repository = Repository(dao)
        try {

            return repository.getFavoritedList()

        } catch (e: Exception) {
            return null
        }

    }

    fun updateFavoritedList(item: MenuItem) {
        viewModelScope.launch {
            try {
                val withContext = withContext(Dispatchers.Default) {
                    useCase.updateFavList(item)
                }
                favorite.value = withContext
            } catch (e: Exception) {
                listState.value = ViewState.error(null, "${R.string.fav_error}")
            }
        }
    }

    fun sendItemToCart(item: MenuItem) {
        viewModelScope.launch {
            try {
                val withContext = withContext(Dispatchers.Default) {
                    useCase.sendToCart(item)
                }
                cart.value = withContext
            } catch (e: Exception) {
                listState.value = ViewState.error(null, "${R.string.cart_error}")
            }
        }
    }

    class DetailModelFactory() : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                return DetailViewModel() as T
            }
            throw IllegalArgumentException("unknown viewmodel class")
        }
    }
}