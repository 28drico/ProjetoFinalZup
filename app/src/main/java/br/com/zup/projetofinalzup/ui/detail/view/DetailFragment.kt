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
import br.com.zup.projetofinalzup.data.datasource.model.MenuItem
import br.com.zup.projetofinalzup.data.datasource.teste.Item
import br.com.zup.projetofinalzup.databinding.FragmentDetailBinding
import br.com.zup.projetofinalzup.ui.DESFAVORITADO
import br.com.zup.projetofinalzup.ui.FAVORITADO_SUCESSO
import br.com.zup.projetofinalzup.ui.menu.viewmodel.MenuViewModel


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val viewModel: MenuViewModel by lazy {ViewModelProvider(this)[MenuViewModel::class.java]}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    private fun favoritedItem(item: Item){
        binding.ivFavorite.setOnClickListener {
            menu.isFavorite = !menu.isFavorite

            if (menu.isFavorite){
                Toast.makeText(
                    context,
                    FAVORITADO_SUCESSO,
                    Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(
                    context,
                    DESFAVORITADO,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


    private fun statusfavorito(menu: MenuItem){
        binding.ivFavorite.setImageDrawable(
            ContextCompat.getDrawable(
                binding.root.context,
                if(menu.isFavorite) {
                    R.drawable.fav_icon
                } else {
                    R.drawable.notfav_icon
                }
            )
        )
    }
}