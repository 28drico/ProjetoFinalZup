package br.com.zup.projetofinalzup.domain.usecase

import android.app.Application
import androidx.constraintlayout.motion.utils.ViewState
import br.com.zup.projetofinalzup.data.datasource.local.FavoriteListDatabase
import br.com.zup.projetofinalzup.domain.repository.Repository

class DishesUseCase(application: Application) {
    private val dao = FavoriteListDatabase.getDatabase(application).favoriteListDAO()
    private val repository = Repository(dao)

}