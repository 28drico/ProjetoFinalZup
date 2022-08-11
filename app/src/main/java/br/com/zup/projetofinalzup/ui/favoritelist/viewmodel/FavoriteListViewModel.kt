package br.com.zup.projetofinalzup.ui.favoritelist.viewmodel

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

class FavoriteListViewModel(application: Application): AndroidViewModel(application) {
    private val useCase = DishesUseCase(application)
    val favoriteState = SingleLiveEvent<ViewState<List<MenuItem>>>()
    val disfavorState = SingleLiveEvent<ViewState<MenuItem>>()

    fun getFavoritedList(){
        viewModelScope.launch {
            try{
                val response = withContext(Dispatchers.IO){
                    useCase.getFavoritedList()
                }
                favoriteState.value = response
            }catch(e:Exception){
                favoriteState.value = ViewState.Error(Throwable(ERROR))
            }
        }
    }
    fun disfavorItem(item: MenuItem) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    useCase.updateFavoritedList(item)
                }
                disfavorState.value = response
            } catch (ex: Exception) {
                disfavorState.value =
                    ViewState.Error(Throwable("Não foi desfavoritar o filme!"))
            }
        }
    }
}