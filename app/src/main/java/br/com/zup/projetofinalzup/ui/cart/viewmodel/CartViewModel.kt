package br.com.zup.projetofinalzup.ui.cart.viewmodel

import androidx.lifecycle.*
import br.com.zup.projetofinalzup.data.datasource.model.MenuItem
import br.com.zup.projetofinalzup.domain.repository.Repository
import br.com.zup.projetofinalzup.domain.repository.model.MenuRequest
import br.com.zup.projetofinalzup.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel(private val repository:Repository):ViewModel(){
    private val _menu = MutableLiveData<ViewState<List<MenuItem>>>()
    val menu: LiveData<ViewState<List<MenuItem>>> get() = _menu

    fun getMenu(menu: MenuRequest){
        viewModelScope.launch {
            _menu.value = ViewState.loading(null)
            try{
                val withContext = withContext(Dispatchers.Default){
                    repository.getAPI().getMenu(menu)
                }
                withContext?.let {
                    _menu.value = ViewState.success(it)
                }
            }catch(e:Exception){
                _menu.value = ViewState.error(null,e.message)
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