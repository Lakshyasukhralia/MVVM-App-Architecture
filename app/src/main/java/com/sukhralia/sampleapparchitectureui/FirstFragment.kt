package com.sukhralia.sampleapparchitectureui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.sukhralia.sampleapparchitectureui.databinding.FragmentFirstBinding
import com.sukhralia.sampleapparchitectureui.utils.MyTimer
import com.sukhralia.sampleapparchitectureui.viewmodels.MyViewModel
import com.sukhralia.sampleapparchitectureui.viewmodels.MyViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var myViewModel: MyViewModel
    private lateinit var myViewModelFactory: MyViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentFirstBinding>(inflater,R.layout.fragment_first,container,false)

        myViewModelFactory =
            MyViewModelFactory(
                15
            )
        Log.i("myTag","Initialize view model")
        myViewModel = ViewModelProviders.of(this,myViewModelFactory).get(MyViewModel::class.java)

        binding.myViewModel = myViewModel
        binding.lifecycleOwner = this

        val myTimer =
            MyTimer(this.lifecycle)
        myTimer.startTimer()

//        myViewModel.currentTime.observe(this, Observer { currentTime->
//            binding.timer.text = currentTime
//        })
//
//        myViewModel.score.observe(this, Observer { newScore->
//            binding.hello.text = newScore.toString()
//        })


        binding.next.setOnClickListener {
            view!!.findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment())
            myViewModel.addScore()
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}