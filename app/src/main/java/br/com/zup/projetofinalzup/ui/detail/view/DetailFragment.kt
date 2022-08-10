package br.com.zup.projetofinalzup.ui.detail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.zup.projetofinalzup.R
import br.com.zup.projetofinalzup.databinding.FragmentDetailBinding
import br.com.zup.projetofinalzup.ui.favoritelist.viewmodel.FavoriteListViewModel


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


    private fun clickfavorito(){
        //implementar o toast favorito e disfavorito
    }

    private fun statusfavorito(){
        //implementar codigo status favorito e disfavorito
    }



}