package com.dariobrux.pokemon.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.dariobrux.pokemon.R
import com.dariobrux.pokemon.common.loadImage
import com.dariobrux.pokemon.common.withAlpha
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import com.dariobrux.pokemon.databinding.FragmentInfoBinding
import com.dariobrux.pokemon.ui.util.StatsSpaceItemDecoration
import com.dariobrux.pokemon.ui.util.TypeSpaceItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class InfoFragment : DialogFragment() {

    private val viewModel: InfoViewModel by viewModel()

    // Binding
    private var _binding: FragmentInfoBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val pokemon = arguments?.getSerializable("pokemon") as? PokemonEntity ?: return

        with(binding) {
            card.transitionName = pokemon.name

            img.loadImage(requireContext(), pokemon.image) {
                infoContainerRoot.setBackgroundColor(it)
                card.setCardBackgroundColor(it)
                toolbar.setBackgroundColor(it)
            }

            txtNumber.text = getString(R.string.format_number, pokemon.id)

            txt.text = pokemon.name.capitalize(Locale.getDefault())

            recyclerTypes.let {
                it.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                it.addItemDecoration(TypeSpaceItemDecoration(requireContext().resources.getDimensionPixelSize(R.dimen.regular_padding)))
                it.adapter = TypeAdapter(requireContext(), pokemon.types)
            }

            recyclerStats.let {
                it.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                it.addItemDecoration(StatsSpaceItemDecoration(requireContext().resources.getDimensionPixelSize(R.dimen.regular_padding)))
                it.adapter = StatsAdapter(requireContext(), pokemon.stats)
            }
        }

        sharedElementEnterTransition = TransitionInflater.from(this.context).inflateTransition(R.transition.transition)
        sharedElementReturnTransition = TransitionInflater.from(this.context).inflateTransition(R.transition.transition)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}