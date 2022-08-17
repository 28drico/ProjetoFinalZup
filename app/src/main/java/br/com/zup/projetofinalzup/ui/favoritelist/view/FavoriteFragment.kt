package br.com.zup.projetofinalzup.ui.favoritelist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.projetofinalzup.databinding.FragmentFavoriteBinding
import br.com.zup.projetofinalzup.ui.favoritelist.view.adapter.FavoritedListAdapter
import br.com.zup.projetofinalzup.ui.favoritelist.viewmodel.FavoriteListViewModel
import br.com.zup.projetofinalzup.ui.home.view.HomeActivity
import br.com.zup.projetofinalzup.ui.viewstate.Status

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: FavoriteListViewModel
    private lateinit var factory: FavoriteListViewModel.FavoriteListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        factory = FavoriteListViewModel.FavoriteListViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(FavoriteListViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavoritedList()

        viewModel.favState.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.rvFavorite.adapter = FavoritedListAdapter(it.data!!)
                    binding.rvFavorite.layoutManager = LinearLayoutManager(context)
                    binding.rvFavorite.isVisible = true
                    binding.pbLoading.isVisible = false
                }
                Status.LOADING -> {
                    binding.pbLoading.isVisible = true
                    binding.rvFavorite.isVisible = false
                }
                Status.ERROR -> {
                    Toast.makeText(context, "${it.message}", Toast.LENGTH_LONG).show()
                    binding.pbLoading.isVisible = false
                }
            }
        })
    }
}