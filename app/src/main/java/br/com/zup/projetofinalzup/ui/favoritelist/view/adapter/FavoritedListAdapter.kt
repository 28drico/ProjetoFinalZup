package br.com.zup.projetofinalzup.ui.favoritelist.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.projetofinalzup.data.model.MenuItem
import br.com.zup.projetofinalzup.databinding.DishItemBinding
import com.squareup.picasso.Picasso

class FavoritedListAdapter (private var favoritedList: List<MenuItem>
) : RecyclerView.Adapter<FavoritedListAdapter.ViewHolder>(){

    class ViewHolder(val binding: DishItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showInfo(item: MenuItem){
            binding.tvItemTitle.text = item.name
            binding.tvItemDescription.text = item.description
            val value = "R$ ${item.value}"
            binding.tvItemValue.text = value
            Picasso.get().load(item.urlImageProduct).into(binding.ivItemImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DishItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = favoritedList[position]
        holder.showInfo(item)
    }

    override fun getItemCount() = favoritedList.size

    fun updateList(newList:MutableList<MenuItem>){
        favoritedList = newList
        notifyDataSetChanged()
    }
}