package br.com.zup.projetofinalzup.ui.menu.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.projetofinalzup.data.datasource.model.MenuItem
import br.com.zup.projetofinalzup.domain.singleliveevent.SingleLiveEvent
import br.com.zup.projetofinalzup.domain.usecase.DishesUseCase
import br.com.zup.projetofinalzup.ui.ERROR
import br.com.zup.projetofinalzup.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MenuViewModel(application: Application) : AndroidViewModel(application){
    private val useCase = DishesUseCase(application)
    val state = SingleLiveEvent<ViewState<List<MenuItem>>>()
    val favoritedState = SingleLiveEvent<ViewState<MenuItem>>()
    val loading = SingleLiveEvent<ViewState<Boolean>>()

    fun getMenu(){
        loading.value = ViewState.Loading(true)
        viewModelScope.launch {
            try{
                val response = withContext(Dispatchers.IO){
                    useCase.getMenuAPI()
                }
                state.value = response
            }catch(e:Exception){
                state.value = ViewState.Error(Throwable(ERROR))
            } finally {
                loading.value = ViewState.Loading(false)
            }
        }
    }
    fun updateFavoritedList(menu:MenuItem) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    useCase.updateFavoritedList(menu)
                }
                favoritedState.value = response
            } catch (ex: Exception) {
                favoritedState.value =
                    ViewState.Error(Throwable("Não foi possível atualizar o filme!"))
            }
        }
    }
}