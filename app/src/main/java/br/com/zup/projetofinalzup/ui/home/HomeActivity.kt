package br.com.zup.projetofinalzup.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import br.com.zup.projetofinalzup.R
import br.com.zup.projetofinalzup.databinding.ActivityHomeBinding
import br.com.zup.projetofinalzup.ui.cart.view.CarttFragment
import br.com.zup.projetofinalzup.ui.favoritelist.view.FavoriteFragment
import br.com.zup.projetofinalzup.ui.menu.view.MenuFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var toolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(MenuFragment())

        toolbar = binding.toolbar
        toolbar.title = ""
        setSupportActionBar(toolbar)


        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){
                R.id.Menu -> replaceFragment(MenuFragment())
                R.id.Favoritos -> replaceFragment(FavoriteFragment())
                R.id.Carrinho -> replaceFragment(CarttFragment())

                else -> {

                }
            }
            true
        }
    }


    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}