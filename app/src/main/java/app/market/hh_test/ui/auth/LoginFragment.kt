package app.market.hh_test.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import app.market.hh_test.R
import app.market.hh_test.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

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

        binding.tvBtnContinue.setOnClickListener {
            val email = binding.etEmail.text.toString()
            if (email.isEmpty()){
                Toast.makeText(requireContext(), R.string.EmailError, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            val action = LoginFragmentDirections.actionLoginFragmentToVerifyFragment(email)
//            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}