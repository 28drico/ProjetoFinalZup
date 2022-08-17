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
import br.com.zup.projetofinalzup.data.model.MenuItem
import br.com.zup.projetofinalzup.databinding.FragmentMenuBinding
import br.com.zup.projetofinalzup.ui.menu.view.adapter.MenuAdapter
import br.com.zup.projetofinalzup.ui.menu.viewmodel.MenuViewModel
import br.com.zup.projetofinalzup.ui.viewstate.Status

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private lateinit var viewModel: MenuViewModel
    private lateinit var factory:MenuViewModel.MenuViewModelFactory
    private val adapter: MenuAdapter by lazy {MenuAdapter(arrayListOf(), this::goToDetail)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        factory = MenuViewModel.MenuViewModelFactory()
        viewModel = ViewModelProvider(this,factory).get(MenuViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMenu()

        viewModel.menu.observe(viewLifecycleOwner,Observer{
            when(it.status){
                Status.SUCCESS -> {
                    binding.rvMenu.adapter = adapter
                    binding.rvMenu.layoutManager = LinearLayoutManager(context)
                    adapter.updateList(it.data as ArrayList<MenuItem>)
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

    fun goToDetail(item: MenuItem){
        val bundle = bundleOf("ITEM_KEY" to item)
        NavHostFragment.findNavController(this).navigate(R.id.action_menuFragment_to_detailFragment,bundle)
    }
}