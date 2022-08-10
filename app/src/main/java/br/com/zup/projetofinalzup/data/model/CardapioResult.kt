package br.com.zup.projetofinalzup.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardapioResult (
    var isFavorite : Boolean
): Parcelable