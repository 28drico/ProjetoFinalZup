package br.com.zup.projetofinalzup.ui.menu.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.projetofinalzup.R
import br.com.zup.projetofinalzup.data.datasource.model.MenuItem
import br.com.zup.projetofinalzup.domain.repository.Repository
import br.com.zup.projetofinalzup.domain.repository.model.MenuRequest
import br.com.zup.projetofinalzup.databinding.FragmentMenuBinding
import br.com.zup.projetofinalzup.ui.menu.view.adapter.MenuAdapter
import br.com.zup.projetofinalzup.ui.menu.viewmodel.MenuViewModel
import br.com.zup.projetofinalzup.ui.viewstate.Status

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private lateinit var viewModel: MenuViewModel
    private lateinit var factory:MenuViewModel.MenuViewModelFactory
    private val adapter: MenuAdapter by lazy {MenuAdapter(arrayListOf(), this::goToDetail, this::favItem)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        factory = MenuViewModel.MenuViewModelFactory(Repository)
        viewModel = ViewModelProvider(this,factory).get(MenuViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMenu(MenuRequest("31037721000108"))

        viewModel.menu.observe(viewLifecycleOwner,Observer{
            when(it.status){
                Status.SUCCESS -> {
                    binding.rvMenu.adapter = adapter
                    binding.rvMenu.layoutManager = LinearLayoutManager(context)
                    binding.rvMenu.isVisible = true
                    binding.pbLoading.isVisible = false
                }
                Status.LOADING -> {
                    binding.pbLoading.isVisible = true
                    binding.rvMenu.isVisible = false
                }
                Status.ERROR -> {
                    Toast.makeText( context,"${it.message}",Toast.LENGTH_LONG).show()
                    binding.pbLoading.isVisible = false
                }
            }
        })
    }

    private fun clickfavorito(menu: MenuItem){
        binding.rvMenu.setOnClickListener {
            menu.isFavorite = menu.isFavorite
            favoriteItem(menu)
            if (menu.isFavorite == true){
                Toast.makeText(
                    context,
                    "FAVORITADO_SUCESSO",
                    Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(
                    context,
                    "DESFAVORITADO",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun goToDetail(item:MenuItem){
        val bundle = bundleOf("ITEM_KEY" to item)
        NavHostFragment.findNavController(this).navigate(R.id.action_menuFragment_to_detailFragment,bundle)
    }
    fun favoriteItem(item:MenuItem){
        viewModel.insertFavoriteItem(item)
    }
}