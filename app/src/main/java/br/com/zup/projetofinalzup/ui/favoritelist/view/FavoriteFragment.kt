package br.com.zup.projetofinalzup.ui.favoritelist.view


import androidx.fragment.app.Fragment

class FavoriteFragment : Fragment() {/**
    private lateinit var binding: FragmentFavoriteBinding

    private val viewModel: FavoriteListViewModel by lazy {
        ViewModelProvider(this)[FavoriteListViewModel::class.java]}

    private val adapter: FavoritedListAdapter by lazy { FavoritedListAdapter(arrayListOf()) }

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
        viewModel.getFavoritedList()
    }

    private fun observers() {
        viewModel.favoriteState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    adapter.updateList(it.data.toMutableList())}
                is ViewState.Error -> {
                    Toast.makeText(context, ERROR, Toast.LENGTH_LONG).show()}
                else -> {}
            }
        }
        viewModel.disfavorState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    Toast.makeText(
                        context,
                        "item cardapio foi favoritado com sucesso!",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
    }
    private fun showRecycler(){
        binding.rvMenu.adapter = adapter
        binding.rvMenu.layoutManager = GridLayoutManager(context,2)
    }
    private fun goToDetail(menu: MenuItem) {
        val bundle = bundleOf("BLA" to menu)
        NavHostFragment.findNavController(this).navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
    }

    private fun disfavorItem(item: MenuItem) {
        viewModel.disfavorItem(item)
    }**/
}