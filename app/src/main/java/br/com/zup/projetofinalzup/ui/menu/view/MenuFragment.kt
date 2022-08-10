package br.com.zup.projetofinalzup.ui.menu.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.zup.projetofinalzup.R

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu2, container, false)
    }

    private fun clickfavorito(){
        //implementar o toast favorito e disfavorito
    }

    private fun statusfavorito(){
        //implementar codigo status favorito e disfavorito
    }


}