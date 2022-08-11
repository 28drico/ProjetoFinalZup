package br.com.zup.projetofinalzup.data.datasource.remote

import br.com.zup.projetofinalzup.data.datasource.model.MenuItem
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("response")
    val menu:List<MenuItem>
)