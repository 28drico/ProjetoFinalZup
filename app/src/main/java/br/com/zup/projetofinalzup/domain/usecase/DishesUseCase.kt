package br.com.zup.projetofinalzup.domain.usecase

import br.com.zup.projetofinalzup.data.datasource.local.AppApplication
import br.com.zup.projetofinalzup.data.datasource.model.MenuItem
import br.com.zup.projetofinalzup.domain.repository.FavoritedListRepository
import br.com.zup.projetofinalzup.ui.viewstate.ViewState

class DishesUseCase {
    private val dao = AppApplication.getdatabase().favoriteListDAO()
    private val repository = FavoritedListRepository(dao)

    suspend fun favoriteItem(item: MenuItem): ViewState<MenuItem> {
        return try {
            repository.favoriteItem(item)
            ViewState.success(item)
        } catch (e: Exception) {
            ViewState.error(null, e.message)
        }
    }

    suspend fun getFavoritedList(): ViewState<List<MenuItem>> {
        return try {
            val menu = repository.getFavoritedList()
            ViewState.success(menu)
        } catch (e: Exception) {
            ViewState.error(null, e.message)
        }
    }

    suspend fun cart(item: MenuItem): ViewState<MenuItem> {
        return try {
            TODO()
            ViewState.success(item)
        } catch (e: Exception) {
            ViewState.error(null, e.message)
        }
    }
    suspend fun getCartList():ViewState<List<MenuItem>> {
        return try {
            TODO()

        } catch (e: Exception) {
            ViewState.error(null, e.message)
        }
    }
}