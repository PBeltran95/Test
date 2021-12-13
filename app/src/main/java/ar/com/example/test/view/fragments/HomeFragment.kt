package ar.com.example.test.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ar.com.example.test.R
import ar.com.example.test.data.models.Person
import ar.com.example.test.databinding.FragmentHomeBinding
import ar.com.example.test.presentation.PersonViewModel
import ar.com.example.test.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<PersonViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        setupObservers()
        setupButtons()
    }

    private fun setupButtons() {

        binding.btnSave.setOnClickListener {
            showErrorMessageToAge()
            captureAndSendValues()
            setupObservers()
        }
        binding.btnNavigate.setOnClickListener {
            navigate()
        }
    }

    private fun navigate() {
        findNavController().navigate(R.id.action_homeFragment_to_usersScreenFragment)
    }

    private fun showErrorMessageToAge() {
        viewModel.ageIsValid.observe(viewLifecycleOwner){
            if (!it) toast(requireContext(), "Edad invalida")
        }
    }


    private fun captureAndSendValues() {
        with(binding){
            val name = etName.text.toString().trim()
            val lastName = etLastName.text.toString().trim()
            val age = etAge.text.toString().trim()
            val dni = etDni.text.toString().trim()

            viewModel.validateFields(name,lastName,age,dni)
        }
    }



    private fun setupObservers() {
        viewModel.fieldsAreValid.observe(viewLifecycleOwner){ validUser ->
            if (validUser){
                saveUser()
            }else{
                toast(requireContext(), "Campos invalidos")
            }
        }
    }


    private fun saveUser() {
        with(binding){
            val name = etName.text.toString()
            val lastName = etLastName.text.toString()
            val age = etAge.text.toString()
            val dni = etDni.text.toString()
            try {
                viewModel.checkDB(Person(dni.toInt(), name,lastName,age.toInt()))
                observeDbSize()
            }catch (e:Exception){
                toast(requireContext(), "Nose pudo guardar, Error: ${e.message}")
            }

        }
    }

    private fun observeDbSize() {
        viewModel.dataBaseIsFull.observe(viewLifecycleOwner){
            if (it) toast(requireContext(), "Database is full") else toast(requireContext(), "Usuario guardado")
        }
    }
}