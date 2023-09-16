package it.nicolapiriottu.demo.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import it.nicolapiriottu.demo.R
import it.nicolapiriottu.demo.databinding.FragmentLoginBinding
import it.nicolapiriottu.demo.ui.main.MainActivity

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    /**
     * ViewModel
     * */
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        setupView()
        setupObserver()
        return binding.root
    }

    private fun setupView() {
        getPassword()
        getEmail()

        binding.login.setOnClickListener {
            viewModel.login(requireActivity())
        }

        binding.signup.setOnClickListener {
            //viewModel.signup()
            Toast.makeText(requireContext(), "coming soon", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupObserver() {
        viewModel.useCaseLiveData.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { useCase ->

                when (useCase) {
                    LoginViewModel.StartUseCase.GoToHome -> {
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }

                    is LoginViewModel.StartUseCase.ShowError -> {
                        Toast.makeText(
                            requireContext(), useCase.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun getEmail() {
        binding.emailEt.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.setEmail(s.toString())
                }

                override fun afterTextChanged(s: Editable?) {}

            }
        )
    }

    private fun getPassword() {
        binding.passwordEt.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.setPassword(s.toString())
                }

                override fun afterTextChanged(s: Editable?) {}

            }
        )
    }

}