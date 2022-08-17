package br.com.zup.projetofinalzup.ui.menu.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.projetofinalzup.data.model.MenuItem
import br.com.zup.projetofinalzup.databinding.MenuItemBinding
import com.squareup.picasso.Picasso

class MenuAdapter (
    private var menu: List<MenuItem>,
    private val clickDetail: (item: MenuItem) -> Unit
) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>(){

    class ViewHolder(val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showInfo(item: MenuItem){
            binding.tvItemTitle.text = item.name
            binding.tvItemDescription.text = item.description
            val value = "R$ ${item.value}"
            binding.tvItemValue.text = value
            Picasso.get().load(item.urlImageProduct).into(binding.ivItemImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = menu[position]
        holder.showInfo(items)

        holder.binding.cvMenuItem.setOnClickListener{
            clickDetail(items)
        }
    }

    override fun getItemCount() = menu.size

    fun updateList(newList:MutableList<MenuItem>){
        menu = newList
        notifyDataSetChanged()
    }
}