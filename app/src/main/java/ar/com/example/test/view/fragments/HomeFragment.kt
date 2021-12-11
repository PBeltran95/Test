package ar.com.example.test.view.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
        setupFields()
        setupObservers()
        setupButtons()
    }

    private fun setupButtons() {

        binding.btnSave.setOnClickListener {
            saveUser()
        }
        binding.btnNavigate.setOnClickListener {

        }
    }


    private fun setupFields() {
        with(binding){
            etName.doAfterTextChanged { captureAndSendValues() }
            etLastName.doAfterTextChanged { captureAndSendValues() }
            etAge.doAfterTextChanged { captureAndSendValues() }
            etDni.doAfterTextChanged { captureAndSendValues() }
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
        viewModel.enableButtons.observe(viewLifecycleOwner){ enableButtons ->
            enableOrDisableButtons(enableButtons)
        }
    }

    private fun enableOrDisableButtons(enableButtons: Boolean) {
        if (enableButtons){
            with(binding){
                btnSave.isEnabled = true
                btnSave.setBackgroundColor(
                    ContextCompat.getColor(requireContext(),
                        R.color.design_default_color_primary
                ))
                btnNavigate.isEnabled = true
                btnNavigate.setBackgroundColor(
                    ContextCompat.getColor(requireContext(),
                        R.color.design_default_color_primary
                    ))
            }
        }else{
            with(binding){
                btnSave.isEnabled = false
                btnSave.setBackgroundColor(
                    ContextCompat.getColor(requireContext(),
                        R.color.gray
                    ))
                btnNavigate.isEnabled = false
                btnNavigate.setBackgroundColor(
                    ContextCompat.getColor(requireContext(),
                        R.color.gray
                    ))
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
                viewModel.savePerson(Person(dni.toInt(), name,lastName,age.toInt()))
                toast(requireContext(), "Usuario guardado")
            }catch (e:Exception){
                toast(requireContext(), "Nose pudo guardar, Error: ${e.message}")
            }

        }
    }
}