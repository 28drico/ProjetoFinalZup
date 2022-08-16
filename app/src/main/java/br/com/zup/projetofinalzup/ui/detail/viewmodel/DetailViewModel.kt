package br.com.zup.projetofinalzup.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.projetofinalzup.data.model.MenuItem
import br.com.zup.projetofinalzup.domain.singleliveevent.SingleLiveEvent
import br.com.zup.projetofinalzup.domain.usecase.DishesUseCase
import br.com.zup.projetofinalzup.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel: ViewModel(){
    private val useCase = DishesUseCase()
    val state = SingleLiveEvent<ViewState<MenuItem>>()

    fun sendItemToCart(item:MenuItem){
        viewModelScope.launch {
            try{
                val withContext = withContext(Dispatchers.Default){
                    useCase.cart(item)
                }
                state.value = withContext
            }catch(e:Exception){
                state.value = ViewState.error(null,e.message)
            }
        }
    }
}