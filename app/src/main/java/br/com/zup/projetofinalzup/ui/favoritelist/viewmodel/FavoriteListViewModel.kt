package br.com.zup.projetofinalzup.ui.favoritelist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.projetofinalzup.ERROR
import br.com.zup.projetofinalzup.data.datasource.model.MenuItem
import br.com.zup.projetofinalzup.domain.singleliveevent.SingleLiveEvent
import br.com.zup.projetofinalzup.domain.usecase.DishesUseCase
import br.com.zup.projetofinalzup.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteListViewModel(application: Application): AndroidViewModel(application) {
    private val useCase = DishesUseCase(application)
    val state = SingleLiveEvent<ViewState<List<MenuItem>>>()

    fun getCharacterList(){
        viewModelScope.launch {
            try{
                val response = withContext(Dispatchers.IO){
                    useCase.getMenuAPI()
                }
                state.value = response
            }catch(e:Exception){
                state.value = ViewState.Error(Throwable(ERROR))
            }
        }
    }
}