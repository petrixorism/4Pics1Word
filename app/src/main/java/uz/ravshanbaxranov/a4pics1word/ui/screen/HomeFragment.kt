package uz.ravshanbaxranov.a4pics1word.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.delay
import uz.ravshanbaxranov.a4pics1word.R
import uz.ravshanbaxranov.a4pics1word.databinding.FragmentHomeBinding
import uz.ravshanbaxranov.a4pics1word.ui.viewmodel.HomeViewModel
import uz.ravshanbaxranov.a4pics1word.util.showSnackBar

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    private var stateList: ArrayList<Boolean>? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.stateLiveData.observe(viewLifecycleOwner, stateObserver)
        viewModel.getState()
        initialAnimation()


    }

    //---------- Observers -----------//

    private val stateObserver = Observer<List<Boolean>> {
        stateList = it as ArrayList<Boolean>
        initialClick()
    }

    //----------- Animation --------------//
    private fun initialAnimation() = lifecycleScope.launchWhenCreated {

        try {
            delay(500L)
            binding.constraintLayout.animate()
                .setDuration(800L)
                .alpha(1f)
            binding.newbieImg.animate()
                .setDuration(800L)
                .alpha(1f)
            delay(500L)
            binding.constraintLayout2.animate()
                .setDuration(800L)
                .alpha(1f)
            binding.middleImg.animate()
                .setDuration(800L)
                .alpha(1f)
            delay(500L)
            binding.constraintLayout3.animate()
                .setDuration(800L)
                .alpha(1f)
            binding.expertImg.animate()
                .setDuration(800L)
                .alpha(1f)
        } catch (e: Exception) {
            Log.d("TAGDF", "State : ${e.message}")
        }

    }


    //----------- Functions --------------//

    private fun initialClick() {
        binding.constraintLayout.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToPlaygroundFragment(
                    "Beginner"
                )
            )
        }
        stateList.let {
            if (stateList!![0]) {
                binding.stateLevel2ImgBtn.setBackgroundResource(R.drawable.ic_play)
                binding.constraintLayout2.setOnClickListener {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToPlaygroundFragment(
                            "Medium"
                        )
                    )
                }
            } else {
                binding.stateLevel2ImgBtn.setBackgroundResource(R.drawable.ic_lock)
                binding.constraintLayout2.setOnClickListener {
                    showSnackBar("Ushbu bosqich hozircha yopiq!")
                }

            }

            if (stateList!![1]) {
                binding.stateLevel3ImgBtn.setBackgroundResource(R.drawable.ic_play)
                binding.constraintLayout3.setOnClickListener {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToPlaygroundFragment(
                            "Expert"
                        )
                    )
//                    if (mInterstitialAd != null) {
//                        mInterstitialAd?.show(requireActivity())
//                    }
                }
            } else {
                binding.stateLevel3ImgBtn.setBackgroundResource(R.drawable.ic_lock)
                binding.constraintLayout3.setOnClickListener {
                    showSnackBar("Ushbu bosqich hozircha yopiq!")
                }

            }
        }

    }

}