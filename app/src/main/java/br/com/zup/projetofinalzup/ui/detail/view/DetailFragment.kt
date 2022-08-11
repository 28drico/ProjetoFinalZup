package br.com.zup.projetofinalzup.ui.detail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
<<<<<<< HEAD
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import br.com.zup.projetofinalzup.R
import br.com.zup.projetofinalzup.data.model.CardapioResult
import br.com.zup.projetofinalzup.databinding.FragmentDetailBinding
import br.com.zup.projetofinalzup.ui.DESFAVORITADO
import br.com.zup.projetofinalzup.ui.FAVORITADO_SUCESSO
import br.com.zup.projetofinalzup.ui.menu.viewmodel.MenuViewModel
=======
import androidx.lifecycle.ViewModelProvider
import br.com.zup.projetofinalzup.R
import br.com.zup.projetofinalzup.databinding.FragmentDetailBinding
import br.com.zup.projetofinalzup.ui.favoritelist.viewmodel.FavoriteListViewModel
>>>>>>> a474a72f805579931f424702967608ef61833242


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

<<<<<<< HEAD
    private val viewModel: MenuViewModel by lazy {
        ViewModelProvider(this)[MenuViewModel::class.java]
    }



=======
>>>>>>> a474a72f805579931f424702967608ef61833242

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


<<<<<<< HEAD
    private fun clickfavorito(cardapio: CardapioResult){
        binding.ivFavorite.setOnClickListener {
            cardapio.isFavorite = !cardapio.isFavorite
            updateFavorito(cardapio)


            if (cardapio.isFavorite){
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

    private fun updateFavorito(cardapio: CardapioResult){
        viewModel.disfavorCardapio(cardapio)
    }

    private fun statusfavorito(cardapio: CardapioResult){
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
=======
    private fun clickfavorito(){
        //implementar o toast favorito e disfavorito
    }

    private fun statusfavorito(){
        //implementar codigo status favorito e disfavorito
    }



>>>>>>> a474a72f805579931f424702967608ef61833242

}