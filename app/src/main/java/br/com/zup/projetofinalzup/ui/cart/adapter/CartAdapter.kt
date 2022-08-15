package br.com.zup.projetofinalzup.ui.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.projetofinalzup.R
import br.com.zup.projetofinalzup.data.datasource.local.AppApplication
import br.com.zup.projetofinalzup.data.datasource.model.MenuItem
import br.com.zup.projetofinalzup.databinding.CartItemBinding
import com.squareup.picasso.Picasso

class CartAdapter (private var cartList: List<MenuItem>,
                   private val clickDetail: (item: MenuItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.ViewHolder>(){

    class ViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showInfo(item: MenuItem){
            binding.tvItemTitle.text = item.name
            val value = item.value * 4
            binding.tvItemValue.text = value.toString()
            binding.tvItemQtd.text = "4"
            Picasso.get().load(item.urlImageProduct).into(binding.ivItemImage)
            TODO()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val menu = cartList[position]
        if(cartList.size == 0){
            Toast.makeText(AppApplication(), R.string.empty_cart,Toast.LENGTH_LONG).show()
        }else {
            holder.showInfo(menu)
            holder.binding.ivItemImage.setOnClickListener{
                clickDetail(menu)
            }
        }
    }

    override fun getItemCount() = cartList.size

    fun updateList(newList:MutableList<MenuItem>){
        cartList = newList
        notifyDataSetChanged()
    }
}