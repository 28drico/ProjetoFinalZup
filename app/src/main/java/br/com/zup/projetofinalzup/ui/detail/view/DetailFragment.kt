package br.com.zup.projetofinalzup.ui.detail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import br.com.zup.projetofinalzup.R
import br.com.zup.projetofinalzup.data.model.MenuItem
import br.com.zup.projetofinalzup.databinding.FragmentDetailBinding
import br.com.zup.projetofinalzup.ui.detail.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var factory: DetailViewModel.DetailModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        factory = DetailViewModel.DetailModelFactory()
        viewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataRecovery()
    }

    private fun dataRecovery() {
        val item = arguments?.getParcelable<MenuItem>("ITEM_KEY")
        item?.let {
            Picasso.get().load(it.urlImageProduct).into(binding.ivItemDetail)
            binding.tvItemTitle.text = it.name
            binding.tvItemDescription.text = it.description
            val value = "${getString(R.string.item_price)} ${it.value}"
            binding.tvItemPrice.text = value
            binding.tvQuantity.text = it.qtd.toString()
            updateColor(item)
        }
        var num = 1
        binding.tvLess.setOnClickListener{
            num = num - 1
            if(num > 0 ){
                binding.tvQuantity.text = num.toString()
                if (item != null) {
                    item.qtd = num
                }
            }
        }

        binding.tvMore.setOnClickListener{
            num = num + 1
            if(num > 0 ){
                binding.tvQuantity.text = num.toString()
                if (item != null) {
                    item.qtd = num
                }
            }
        }
        binding.tvCartAdd.setOnClickListener {
            if (item != null) {
                addItemCart(item)
            }
        }
        binding.ivFavorite.setOnClickListener {
            if (item != null) {
                item.isFavorite = !item.isFavorite
                viewModel.updateFavoritedList(item)
                favoriteItem(item)
                verifyIconFavorite(item.isFavorite)

            }
        }
    }

    private fun addItemCart(item: MenuItem) {
        viewModel.sendItemToCart(item)
        Toast.makeText(context, R.string.item_add, Toast.LENGTH_SHORT).show()
    }

    private fun updateColor(item: MenuItem) {
        var isFavorite = false
        var listFavorite = viewModel.favoriteList
        listFavorite?.forEach {
            if (it.name == item.name) {
                isFavorite = true
            }
        }
        verifyIconFavorite(isFavorite)
    }

    private fun verifyIconFavorite(isFavorite: Boolean) {
        binding.ivFavorite.setImageDrawable(
            ContextCompat.getDrawable
                (
                binding.root.context,
                if (isFavorite) {
                    R.drawable.fav_icon
                } else {
                    R.drawable.icon_heart
                }
            )
        )
    }

    fun favoriteItem(item: MenuItem) {
        if (item.isFavorite) {
            Toast.makeText(
                context,
                "${item.name} ${getString(R.string.item_fav)}",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                context,
                "${item.name} ${getString(R.string.item_disfav)}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}