package br.com.zup.projetofinalzup.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.zup.projetofinalzup.R
import br.com.zup.projetofinalzup.data.model.MenuItem
import br.com.zup.projetofinalzup.domain.singleliveevent.SingleLiveEvent
import br.com.zup.projetofinalzup.domain.usecase.DishesUseCase
import br.com.zup.projetofinalzup.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel: ViewModel(){
    private val useCase = DishesUseCase()
    val listState = SingleLiveEvent<ViewState<List<MenuItem>?>>()
    val favorite = SingleLiveEvent<ViewState<MenuItem>>()
    val disfavor = SingleLiveEvent<ViewState<MenuItem>>()

    fun updateFavoritedList(item: MenuItem){
        viewModelScope.launch {
            try{
                val withContext = withContext(Dispatchers.Default){
                    useCase.updateFavList(item)
                }
                favorite.value = withContext
            }catch(e:Exception){
                listState.value = ViewState.error(null, "${R.string.fav_error}")
            }
        }
    }

    fun disfavor(item: MenuItem){
        viewModelScope.launch {
            try{
                val response = withContext(Dispatchers.Default){
                    useCase.updateFavList(item)
                }
                disfavor.value = response
            }catch(e:Exception){
                disfavor.value = ViewState.error(null, "${R.string.disfav_error}")
            }
        }
    }

    fun sendItemToCart(item:MenuItem){
        viewModelScope.launch {
            try{
                useCase.cart(item)
            }catch(e:Exception){
                listState.value = ViewState.error(null, "${R.string.cart_error}")
            }
        }
    }

    class DetailModelFactory(): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
                return DetailViewModel() as T
            }
            throw IllegalArgumentException("unknown viewmodel class")
        }
    }
}