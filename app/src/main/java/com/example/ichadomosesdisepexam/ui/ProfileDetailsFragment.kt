package com.example.ichadomosesdisepexam.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.ichadomosesdisepexam.R
import com.example.ichadomosesdisepexam.databinding.FragmentProfileDetailsBinding
import com.google.android.material.snackbar.Snackbar


class ProfileDetailsFragment : Fragment() {

    private var _binding: FragmentProfileDetailsBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: ProgramViewModel
    private val args: ProfileDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel


        binding.userImage.load(args.userProfile.profileImage) {
            error(R.drawable.ic_baseline_account_circle_24)
            placeholder(R.drawable.ic_baseline_account_circle_24)
        }
        binding.userName.text = args.userProfile.name.toString()
        binding.userFollowers.text = args.userProfile.followers.toString()
        binding.userFollowing.text  = args.userProfile.following.toString()
        val addButton = binding.favoriteButton
        addButton.setOnClickListener {
            viewModel.savedUserProfile(args.userProfile)
            Snackbar.make(view, "User Successfully Saved", Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_profileDetailsFragment_to_savedUsers)
        }


    }
}
