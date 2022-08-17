package br.com.zup.projetofinalzup.ui.cart.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.projetofinalzup.R
import br.com.zup.projetofinalzup.data.model.MenuItem
import br.com.zup.projetofinalzup.databinding.FragmentCartBinding
import br.com.zup.projetofinalzup.ui.cart.adapter.CartAdapter
import br.com.zup.projetofinalzup.ui.cart.viewmodel.CartViewModel
import br.com.zup.projetofinalzup.ui.home.view.HomeActivity
import br.com.zup.projetofinalzup.ui.viewstate.Status

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel
    private lateinit var factory: CartViewModel.CartModelFactory
    private val adapter: CartAdapter by lazy { CartAdapter(arrayListOf(), this::goToDetail) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        factory = CartViewModel.CartModelFactory()
        viewModel = ViewModelProvider(this,factory).get(CartViewModel::class.java)

        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCartList()

        binding.bvCloseOrder.setOnClickListener{
            NavHostFragment.findNavController(this).navigate(R.id.action_cartFragment_to_endFragment)
        }

        viewModel.cartState.observe(viewLifecycleOwner, Observer{
            when(it.status){
                Status.SUCCESS -> {
                    binding.rvCart.adapter = adapter
                    binding.rvCart.layoutManager = LinearLayoutManager(context)
                    adapter.updateList(it.data as MutableList<MenuItem>)
                    binding.rvCart.isVisible = true
                    binding.pbLoading.isVisible = false
                }
                Status.LOADING -> {
                    binding.pbLoading.isVisible = true
                    binding.rvCart.isVisible = false
                }
                Status.ERROR -> {
                    Toast.makeText( context,"${it.message}", Toast.LENGTH_LONG).show()
                    binding.pbLoading.isVisible = false
                }
            }
        })
    }

    fun goToDetail(item: MenuItem){
        val bundle = bundleOf("ITEM_KEY" to item)
        NavHostFragment.findNavController(this).navigate(R.id.action_cartFragment_to_detailFragment,bundle)
    }
}