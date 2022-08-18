package br.com.zup.projetofinalzup.domain.usecase

import br.com.zup.projetofinalzup.data.datasource.local.database.AppApplication
import br.com.zup.projetofinalzup.data.datasource.remote.model.MenuRequest
import br.com.zup.projetofinalzup.data.model.MenuItem
import br.com.zup.projetofinalzup.domain.repository.Repository
import br.com.zup.projetofinalzup.ui.viewstate.ViewState

class DishesUseCase {
    private val dao = AppApplication.getdatabase().favoriteListDAO()
    private val repository = Repository(dao)

    suspend fun getMenu(): ViewState<List<MenuItem>> {
        return try {
            val response = repository.getMenu(MenuRequest("31037721000108"))
            ViewState.success(response)
        } catch (e: Exception) {
            ViewState.error(null, e.message)
        }
    }

    fun updateFavList(item: MenuItem): ViewState<MenuItem> {
        return try {
            if (item.isFavorite) {
                repository.insertIntoDatabase(item)
            } else {
                repository.deleteFromDatabase(item)
            }
            ViewState.success(item)
        } catch (e: Exception) {
            ViewState.error(null, e.message)
        }
    }

    fun getFavoritedList(): ViewState<List<MenuItem>> {
        return try {
            val list = repository.getFavoritedList()
            if (list.isEmpty()) {
                ViewState.empty(list)
            } else {
                ViewState.success(list)
            }
        } catch (e: Exception) {
            ViewState.error(null, e.message)
        }
    }

    fun sendToCart(item: MenuItem): ViewState<MenuItem> {
        return try {
            repository.insertToCart(item)
            repository.updateCartList(item)
            ViewState.success(item)
        } catch (e: Exception) {
            ViewState.error(null, e.message)
        }
    }

    fun getCartList(): ViewState<List<MenuItem>> {
        return try {
            val list = repository.getCartList()
            ViewState.success(list)
        } catch (e: Exception) {
            ViewState.error(null, e.message)
        }
    }
}