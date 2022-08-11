package br.com.zup.projetofinalzup.data.datasource.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "menu")
data class MenuItem (
    @PrimaryKey(autoGenerate = false)
    @SerializedName("name")
    val name:String,
    @SerializedName("description")
    val description:String,
    @SerializedName("value")
    val value:Double,
    @SerializedName("url")
    val image:String,
    var isFavorite:Boolean
) : Parcelable