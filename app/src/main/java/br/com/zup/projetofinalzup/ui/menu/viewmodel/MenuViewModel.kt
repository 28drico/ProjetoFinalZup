package br.com.zup.projetofinalzup.ui.menu.viewmodel

import androidx.lifecycle.*
import br.com.zup.projetofinalzup.data.model.MenuItem
import br.com.zup.projetofinalzup.domain.singleliveevent.SingleLiveEvent
import br.com.zup.projetofinalzup.domain.usecase.DishesUseCase
import br.com.zup.projetofinalzup.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuViewModel():ViewModel(){
    private val useCase = DishesUseCase()
    val menu = SingleLiveEvent<ViewState<List<MenuItem>>>()

    fun getMenu(){
        viewModelScope.launch {
            menu.value = ViewState.loading(null)
            try{
                val withContext = withContext(Dispatchers.Default){
                    useCase.getMenu()
                }
                withContext?.let {
                    menu.value = ViewState.success(it.data)
                }
            }catch(e:Exception){
                menu.value = ViewState.error(null,e.message)
            }
        }
    }

    class MenuViewModelFactory():ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MenuViewModel::class.java)){
                return MenuViewModel() as T
            }
            throw IllegalArgumentException("unknown viewmodel class")
        }
    }
}