package com.anandwana001.weatherappsx.ui

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.anandwana001.weatherappsx.R
import com.anandwana001.weatherappsx.databinding.ActivityHomeBinding
import com.anandwana001.weatherappsx.databinding.ForecastItemBinding
import com.anandwana001.weatherappsx.utils.BindableAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

  private lateinit var binding: ActivityHomeBinding
  private val homeViewModel: HomeViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView<ActivityHomeBinding>(
      this,
      R.layout.activity_home
    )
    binding.apply {
      lifecycleOwner = this@HomeActivity
      viewModel = homeViewModel
    }

    binding.forecastList.apply {
      adapter = createRecyclerViewAdapter()
    }

    homeViewModel.forecastList.observe(this, {
      if (it.isNotEmpty()) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_bottom_up)
        binding.forecastList.startAnimation(animation)
      }
    })
  }

  private fun createRecyclerViewAdapter(): BindableAdapter<ForecastItemViewModel> {
    return BindableAdapter.AdapterBuilder
      .newBuilder<ForecastItemViewModel>()
      .registerView(
        inflateDataBinding = ForecastItemBinding::inflate,
        setViewModel = ForecastItemBinding::setViewModel
      ).build()
  }
}
