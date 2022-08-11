package br.com.zup.projetofinalzup.ui.favoritelist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import br.com.zup.projetofinalzup.ERROR
import br.com.zup.projetofinalzup.databinding.FragmentFavoriteBinding
import br.com.zup.projetofinalzup.ui.favoritelist.view.adapter.Adapter
import br.com.zup.projetofinalzup.ui.favoritelist.viewmodel.FavoriteListViewModel
import br.com.zup.projetofinalzup.ui.home.view.HomeActivity
import br.com.zup.projetofinalzup.ui.viewstate.ViewState

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    private val viewModel: FavoriteListViewModel by lazy {
        ViewModelProvider(this)[FavoriteListViewModel::class.java]}

    private val adapter: Adapter by lazy { Adapter(arrayListOf()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater,container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showRecycler()
        observers()
        (activity as HomeActivity).supportActionBar?.hide()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCharacterList()
    }

    private fun observers() {
        viewModel.state.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    adapter.updateList(it.data.toMutableList())}
                is ViewState.Error -> {
                    Toast.makeText(context, ERROR, Toast.LENGTH_LONG).show()}
                else -> {}
            }
        }
    }
    private fun showRecycler(){
        binding.rvMenu.adapter = adapter
        binding.rvMenu.layoutManager = GridLayoutManager(context,2)
    }
}