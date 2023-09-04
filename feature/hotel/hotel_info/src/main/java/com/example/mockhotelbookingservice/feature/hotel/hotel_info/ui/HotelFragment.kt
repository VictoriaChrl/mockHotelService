package com.example.mockhotelbookingservice.feature.hotel.hotel_info.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mockhotelbookingservice.feature.hotel.hotel_info.adapters.PeculiarityAdapter
import com.example.mockhotelbookingservice.feature.hotel.hotel_info.adapters.PhotoAdapter
import com.example.mockhotelbookingservice.feature.hotel.hotel_info.databinding.FragmentHotelBinding
import com.example.mockhotelbookingservice.feature.hotel.hotel_info.presentation.HotelInfoViewModel
import com.example.mockhotelbookingservice.feature.hotel.hotel_info.presentation.HotelUiState
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HotelFragment : Fragment() {

    private var _binding: FragmentHotelBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: HotelInfoViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentHotelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[HotelInfoViewModel::class.java]
        viewModel.getHotelInfo()

        viewModel.state.observe(viewLifecycleOwner, ::processState)

    }

    private fun loadPeculiarities(list: List<String>){
        val peculiarities = PeculiarityAdapter()
        binding.peculiarityList.apply{
            adapter = peculiarities
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        peculiarities.submitList(list)
    }

    private fun processState(state: HotelUiState) {
        when (state) {
            is HotelUiState.Complete -> renderCompleteState(state)
            HotelUiState.Error.NoInternet -> renderErrorNoInternetState()
            is HotelUiState.Error.Unknown -> renderErrorUnknownState()
            HotelUiState.Initial -> Unit
            HotelUiState.Loading -> renderLoadingState()
        }
    }

    private fun renderCompleteState(state: HotelUiState.Complete) {
        loadPeculiarities(state.info.aboutTheHotel.peculiarities)
        binding.apply {
            viewPager.adapter = PhotoAdapter(state.info.imageUrls,requireContext())
            TabLayoutMediator(tabLayout,viewPager){_,_->}.attach()
            rating.text = state.info.rating.toString()
            ratingName.text = state.info.ratingName
            hotelName.text = state.info.name
            hotelAddress.text = state.info.address
            hotelPrice.text = state.info.minimalPrice.toString()
            hotelPriceSum.text = state.info.priceForIt
            aboutHotelContentText.text = state.info.aboutTheHotel.description
        }
    }

    private fun renderErrorNoInternetState() {

    }

    private fun renderErrorUnknownState() {

    }

    private fun renderLoadingState() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}