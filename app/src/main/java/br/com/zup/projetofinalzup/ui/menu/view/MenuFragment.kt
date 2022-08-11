package br.com.zup.projetofinalzup.ui.menu.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import br.com.zup.projetofinalzup.R
import br.com.zup.projetofinalzup.data.model.CardapioResult
import br.com.zup.projetofinalzup.databinding.FragmentMenu2Binding
import br.com.zup.projetofinalzup.ui.menu.viewmodel.MenuViewModel
import br.com.zup.projetofinalzup.ui.viewstate.ViewState

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenu2Binding

    private val viewModel: MenuViewModel by lazy {
        ViewModelProvider(this)[MenuViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu2, container, false)
    }

    private fun clickfavorito(){
        viewModel.cardapioFavoritedState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    Toast.makeText(
                        context,
                        "item cardapio foi favoritado com sucesso!",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }

    }

    private fun statusfavorito(cardapio : CardapioResult){

        binding.ivFavorite.setImageDrawable(
            ContextCompat.getDrawable(
                binding.root.context,
                if(cardapio.isFavorite) {
                    R.drawable.fav_icon
                } else {
                    R.drawable.notfav_icon
                }
            )
        )
    }

}