package br.com.zup.projetofinalzup.ui.favoritelist.viewmodel

import androidx.lifecycle.*
import br.com.zup.projetofinalzup.data.datasource.model.MenuItem
import br.com.zup.projetofinalzup.domain.repository.Repository
import br.com.zup.projetofinalzup.domain.repository.model.MenuRequest
import br.com.zup.projetofinalzup.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteListViewModel(private val repository:Repository):ViewModel() {
    private val _response = MutableLiveData<ViewState<List<MenuItem>>>()
    val response: LiveData<ViewState<List<MenuItem>>> get() = _response

    fun getFavoritedList(menu: MenuRequest) {
        viewModelScope.launch {
            _response.value = ViewState.loading(null)
            try {
                val withContext = withContext(Dispatchers.Default) {
                    repository.getAPI().getMenu(menu)
                }
                withContext?.let {
                    _response.value = ViewState.success(it)
                }
            } catch (e: Exception) {
                _response.value = ViewState.error(null,e.message)
            }
        }

    }

    class FavoriteListViewModelFactory(val repository: Repository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(FavoriteListViewModel::class.java)){
                return FavoriteListViewModel(repository) as T
            }
            throw IllegalArgumentException("unknown viewmodel class")
        }
    }
}