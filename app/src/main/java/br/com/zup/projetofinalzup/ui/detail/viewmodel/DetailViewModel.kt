package br.com.zup.projetofinalzup.ui.detail.viewmodel

import android.app.Application
import br.com.zup.projetofinalzup.domain.usecase.DishesUseCase

class DetailViewModel(application: Application){
    private val useCase = DishesUseCase(application)
}