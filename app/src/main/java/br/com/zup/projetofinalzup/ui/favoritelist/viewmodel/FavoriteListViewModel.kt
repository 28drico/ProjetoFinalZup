package br.com.zup.projetofinalzup.ui.favoritelist.viewmodel

import android.app.Application
import br.com.zup.projetofinalzup.domain.usecase.DishesUseCase

class FavoriteListViewModel(application: Application){
    private val useCase = DishesUseCase(application)
}