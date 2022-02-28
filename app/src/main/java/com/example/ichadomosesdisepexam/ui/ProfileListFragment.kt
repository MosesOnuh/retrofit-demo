package com.example.ichadomosesdisepexam.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ichadomosesdisepexam.R
import com.example.ichadomosesdisepexam.databinding.FragmentProfileListBinding
import com.example.ichadomosesdisepexam.recyclerview.UserAdapter
import com.example.ichadomosesdisepexam.utils.Resource


class ProfileListFragment : Fragment() {

    private var _binding: FragmentProfileListBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: ProgramViewModel
    lateinit var userAdapter: UserAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        userAdapter.setOnItemClickListener {
            val weblink = it.login
            viewModel.fetchUser(weblink)

            viewModel.userProfile.observe(viewLifecycleOwner, Observer { response ->
                when (response) {
                    is Resource.Success -> {
                        hideProgressBar()
                        response.data?.let { userProfileResponse ->
                            val bundle = Bundle().apply {
                                putSerializable("userProfile", userProfileResponse)
                            }
                            findNavController().navigate(
                                R.id.action_profileListFragment_to_profileDetailsFragment,
                                bundle
                            )
                        }
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        response.message?.let { message ->
                        }
                    }
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                }
            })


        }

        viewModel.UsersList.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { userListResponse ->
                        userAdapter.differ.submitList(userListResponse.items)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
//                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

    }
    private fun hideProgressBar() {
        var progressBar = binding.paginationProgressBar
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        var progressBar = binding.paginationProgressBar
        progressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter()
        binding.gitUsersRecycler.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
