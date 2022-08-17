package br.com.zup.projetofinalzup.ui.cart.viewmodel

import androidx.lifecycle.*
import br.com.zup.projetofinalzup.data.model.MenuItem
import br.com.zup.projetofinalzup.domain.singleliveevent.SingleLiveEvent
import br.com.zup.projetofinalzup.domain.usecase.DishesUseCase
import br.com.zup.projetofinalzup.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel():ViewModel(){
    private val useCase = DishesUseCase()
    val cartState = SingleLiveEvent<ViewState<List<MenuItem>>>()

    fun getCartList(){
        viewModelScope.launch {
            cartState.value = ViewState.loading(null)
            try{
                val withContext = withContext(Dispatchers.Default){
                    useCase.getCartList()
                }
                cartState.value = withContext
            }catch(e:Exception){
                cartState.value = ViewState.error(null,e.message)
            }
        }
    }

    class CartModelFactory(): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(CartViewModel::class.java)){
                return CartViewModel() as T
            }
            throw IllegalArgumentException("unknown viewmodel class")
        }
    }
}