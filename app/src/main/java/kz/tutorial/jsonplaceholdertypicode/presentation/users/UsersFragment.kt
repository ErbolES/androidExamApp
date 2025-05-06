package kz.tutorial.jsonplaceholdertypicode.presentation.users

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kz.tutorial.jsonplaceholdertypicode.R
import kz.tutorial.jsonplaceholdertypicode.databinding.FragmentUsersBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : Fragment(R.layout.fragment_users) {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UsersViewModel by viewModel()
    private val adapter = UsersAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentUsersBinding.bind(view)

        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUsers.adapter = adapter

        lifecycleScope.launch {
            viewModel.user.collectLatest { user ->
                user?.let { adapter.submitList(listOf(it)) }
            }
        }

        viewModel.loadUser(10)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}