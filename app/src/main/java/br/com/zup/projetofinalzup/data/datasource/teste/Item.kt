package br.com.zup.projetofinalzup.data.datasource.teste

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "menu")
data class Item (
    @PrimaryKey(autoGenerate = false)
    @SerializedName("name")
    val name:String = "Especiaria de Cacau",
    @SerializedName("description")
    val description:String = "A caixinha mais pedida, com 4 chocolates crocantes com direito a muito recheio de creme de avelã.",
    @SerializedName("value")
    val value:Double = 28.55,
    @SerializedName("url")
    val image:Int = 0,

    val isFavorite:Boolean
) : Parcelable