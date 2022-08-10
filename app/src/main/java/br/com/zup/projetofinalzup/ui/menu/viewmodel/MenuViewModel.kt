package br.com.zup.projetofinalzup.ui.menu.viewmodel

import android.app.Application
import br.com.zup.projetofinalzup.domain.usecase.DishesUseCase

class MenuViewModel(application: Application){
    private val useCase = DishesUseCase(application)
}