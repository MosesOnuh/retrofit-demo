package com.example.ichadomosesdisepexam.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ichadomosesdisepexam.R
import com.example.ichadomosesdisepexam.databinding.FragmentSavedUsersBinding
import com.example.ichadomosesdisepexam.recyclerview.SavedUserAdapter
import com.google.android.material.snackbar.Snackbar

class SavedUsersFragment : Fragment() {
    private var _binding: FragmentSavedUsersBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: ProgramViewModel
    lateinit var savedUserAdapter: SavedUserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        savedUserAdapter.setOnItemClickListener {

            val bundle = Bundle().apply {
                putSerializable("userProfile", it)
            }

            findNavController().navigate(
                R.id.action_savedUsers_to_profileDetailsFragment,
                bundle
           )
        }


        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val user = savedUserAdapter.differ.currentList[position]
                viewModel.deleteUser(user)
                Snackbar.make(view, "Successfully deleted article", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.savedUserProfile(user)
                    }
                    show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.savedRecycler)
        }
        viewModel.getSavedUser().observe(viewLifecycleOwner, Observer {userProfile ->
            savedUserAdapter.differ.submitList(userProfile)
        })

    }
    private fun setupRecyclerView() {
        savedUserAdapter = SavedUserAdapter()
        binding.savedRecycler.apply {
            adapter = savedUserAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}