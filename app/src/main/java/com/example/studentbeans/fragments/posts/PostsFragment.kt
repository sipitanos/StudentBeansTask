package com.example.studentbeans.fragments.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentbeans.viewmodel.PostsViewModel
import com.example.studentbeans.R
import com.example.studentbeans.databinding.FragmentImagesBinding

class PostsFragment : Fragment() {

    private lateinit var adapter: PostsRecyclerAdapter //adapter for the recycler view
    private val viewModel: PostsViewModel by activityViewModels()// Koin Injected viewModel
    //view binding
    private var _binding: FragmentImagesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImagesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        // Checks if the post lists has changed in order to populate the recyclerview

        val f = viewModel.getList().value
        if (f != null) {
            adapter = PostsRecyclerAdapter(f)
            binding.recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
        else{
            viewModel.makeAPICall()
        }

        viewModel.getList().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter = PostsRecyclerAdapter(it)
                binding.recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()  //send signal to the PostRecyclerAdapter to redraw the recycler view
            }
        }

        // go back to Login
        binding.photosToolbar.setNavigationOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.loginFragment)
            // user is logged out so need to remove all from the livedata
            viewModel.destroyList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}