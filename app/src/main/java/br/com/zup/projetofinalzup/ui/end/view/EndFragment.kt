package br.com.zup.projetofinalzup.ui.end.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.projetofinalzup.R
import br.com.zup.projetofinalzup.databinding.FragmentEndBinding

class EndFragment : Fragment() {
    private lateinit var binding: FragmentEndBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEndBinding.inflate(inflater, container,false)

        binding.bvNewOrder.setOnClickListener{
            NavHostFragment.findNavController(this).navigate(R.id.action_endFragment_to_menuFragment)
        }

        return binding.root
    }
}