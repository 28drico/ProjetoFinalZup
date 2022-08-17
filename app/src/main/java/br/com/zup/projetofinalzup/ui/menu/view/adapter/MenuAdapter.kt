package br.com.zup.projetofinalzup.ui.menu.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.projetofinalzup.R
import br.com.zup.projetofinalzup.data.model.MenuItem
import br.com.zup.projetofinalzup.databinding.DishItemBinding
import com.squareup.picasso.Picasso

class MenuAdapter (
    private var menu: List<MenuItem>,
    private val clickDetail: (item: MenuItem) -> Unit,
    private val clickFav: (item: MenuItem) -> Unit
) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>(){

    class ViewHolder(val binding: DishItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showInfo(item: MenuItem){
            binding.tvItemTitle.text = item.name
            binding.tvItemDescription.text = item.description
            val value = "R$ ${item.value}"
            binding.tvItemValue.text = value
            Picasso.get().load(item.urlImageProduct).into(binding.ivItemImage)

            binding.ivItemFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    binding.root.context,
                    if (item.isFavorite == true)
                        R.drawable.fav_icon
                    else
                        R.drawable.icon_heart
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DishItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = menu[position]
        holder.showInfo(items)

        holder.binding.ivItemFavorite.setOnClickListener{
            items.isFavorite = !(items.isFavorite ?: false)
            notifyItemChanged(position)
            clickFav(items)
        }
        holder.binding.cvItem.setOnClickListener{
            clickDetail(items)
        }
    }

    override fun getItemCount() = menu.size

    fun updateList(newList:MutableList<MenuItem>){
        menu = newList
        notifyDataSetChanged()
    }
}