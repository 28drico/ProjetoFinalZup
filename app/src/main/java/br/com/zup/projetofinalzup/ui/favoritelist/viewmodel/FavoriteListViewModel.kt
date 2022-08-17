package br.com.zup.projetofinalzup.ui.favoritelist.viewmodel

import androidx.lifecycle.*
import br.com.zup.projetofinalzup.data.model.MenuItem
import br.com.zup.projetofinalzup.domain.singleliveevent.SingleLiveEvent
import br.com.zup.projetofinalzup.domain.usecase.DishesUseCase
import br.com.zup.projetofinalzup.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteListViewModel():ViewModel() {
    private val useCase = DishesUseCase()
    val favState = SingleLiveEvent<ViewState<List<MenuItem>>>()

    fun getFavoritedList() {
        viewModelScope.launch {
            favState.value = ViewState.loading(null)
            try {
                val response = withContext(Dispatchers.Default) {
                    useCase.getFavoritedList()
                }
                favState.value = response
            } catch (e: Exception) {
                favState.value = ViewState.error(null, e.message)
            }
        }
    }

    class FavoriteListViewModelFactory(): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(FavoriteListViewModel::class.java)){
                return FavoriteListViewModel() as T
            }
            throw IllegalArgumentException("unknown viewmodel class")
        }
    }
}