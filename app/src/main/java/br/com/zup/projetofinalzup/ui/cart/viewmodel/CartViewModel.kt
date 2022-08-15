package br.com.zup.projetofinalzup.ui.cart.viewmodel

import androidx.lifecycle.*
import br.com.zup.projetofinalzup.data.datasource.model.MenuItem
import br.com.zup.projetofinalzup.domain.repository.Repository
import br.com.zup.projetofinalzup.domain.repository.model.MenuRequest
import br.com.zup.projetofinalzup.domain.singleliveevent.SingleLiveEvent
import br.com.zup.projetofinalzup.domain.usecase.DishesUseCase
import br.com.zup.projetofinalzup.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel(private val repository:Repository):ViewModel(){
    private val useCase = DishesUseCase()
    val cartState = SingleLiveEvent<ViewState<List<MenuItem>>>()
    private val _order = MutableLiveData<ViewState<List<MenuItem>>>()
    val order: LiveData<ViewState<List<MenuItem>>> get() = _order

    fun getCartList(){
        viewModelScope.launch {
            _order.value = ViewState.loading(null)
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


    class CartModelFactory(val repository: Repository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(CartViewModel::class.java)){
                return CartViewModel(repository) as T
            }
            throw IllegalArgumentException("unknown viewmodel class")
        }
    }
}