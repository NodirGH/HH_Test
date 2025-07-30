package app.market.hh_test.ui.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import app.market.hh_test.R
import app.market.hh_test.databinding.FragmentLoginBinding
import app.market.toolkit.string_ext.setTextWithHtml
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvRegister.setTextWithHtml(getString(R.string.NoAccount))

        binding.tvBtnLogin.setOnClickListener {
            if (authViewModel.isLoginButtonEnabled.value == true) {
                authViewModel.login()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }

        authViewModel.loginText = binding.etEmail.text.toString()
        authViewModel.passwordText = binding.etPassword.text.toString()

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                authViewModel.loginText = text.toString()
                authViewModel.checkLoginButton()
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                authViewModel.passwordText = text.toString()
                authViewModel.checkLoginButton()
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        binding.ivVK.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/"))
            startActivity(intent)
        }

        binding.ivOK.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ok.ru/"))
            startActivity(intent)
        }

        authViewModel.isLoginButtonEnabled.observe(viewLifecycleOwner) { isEnabled ->
            if (isEnabled) {
                binding.tvBtnLogin.setBackgroundResource(R.drawable.bg_button)
            } else {
                binding.tvBtnLogin.setBackgroundResource(R.drawable.bg_light_grey)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}