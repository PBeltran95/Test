package ar.com.example.test.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ar.com.example.test.R
import ar.com.example.test.data.models.Person
import ar.com.example.test.databinding.FragmentUsersScreenBinding
import ar.com.example.test.presentation.PersonViewModel
import ar.com.example.test.view.fragments.adapter.MyAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersScreenFragment : Fragment(R.layout.fragment_users_screen) {

    private lateinit var binding: FragmentUsersScreenBinding
    private val adapter by lazy { MyAdapter() }
    private val viewModel by viewModels<PersonViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUsersScreenBinding.bind(view)
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModel.getAllPersons().observe(viewLifecycleOwner){
            setupRecyclerView(it)
        }
    }

    private fun setupRecyclerView(it: List<Person>?) {
        binding.rvUsers.adapter = adapter
        adapter.setData(it!!.toMutableList())
    }
}