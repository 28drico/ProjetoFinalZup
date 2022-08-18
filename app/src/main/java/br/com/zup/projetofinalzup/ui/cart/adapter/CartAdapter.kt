package br.com.zup.projetofinalzup.ui.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.projetofinalzup.data.model.MenuItem
import br.com.zup.projetofinalzup.databinding.CartItemBinding
import com.squareup.picasso.Picasso

class CartAdapter (
    private var cartList: List<MenuItem>,
    private val clickDetail: (item: MenuItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.ViewHolder>(){

    class ViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showInfo(item: MenuItem){
            binding.tvItemTitle.text = item.name
            val value = "R$ ${item.value}"
            binding.tvItemValue.text = value
            binding.tvItemQtd.text = "1x"
            Picasso.get().load(item.urlImageProduct).into(binding.ivItemImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val menu = cartList[position]
        holder.showInfo(menu)
        holder.binding.cvCart.setOnClickListener{
            clickDetail(menu)
        }
        listSize(menu)
    }

    override fun getItemCount() = cartList.size

    fun updateList(newList:MutableList<MenuItem>){
        cartList = newList
        notifyDataSetChanged()
    }
    fun listSize(item:MenuItem){
        for(item in cartList){

        }
    }
}