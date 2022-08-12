package br.com.zup.projetofinalzup.ui.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.projetofinalzup.data.datasource.model.MenuItem
import br.com.zup.projetofinalzup.databinding.DishItemBinding
import com.squareup.picasso.Picasso

class CartAdapter (private var charactersList: List<MenuItem>
) : RecyclerView.Adapter<CartAdapter.ViewHolder>(){

    class ViewHolder(val binding: DishItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showInfo(item: MenuItem){
            binding.tvItemTitle.text = item.name
            binding.tvItemDescription.text = item.description
            binding.tvItemValue.text = item.value.toString()
            Picasso.get().load(item.urlImageProduct).into(binding.ivItemImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DishItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = charactersList[position]
        holder.showInfo(character)
    }

    override fun getItemCount() = charactersList.size

    fun updateList(newList:MutableList<MenuItem>){
        charactersList = newList
        notifyDataSetChanged()
    }
}