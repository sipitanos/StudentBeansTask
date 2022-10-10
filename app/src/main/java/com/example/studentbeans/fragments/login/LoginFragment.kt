package com.example.studentbeans.fragments.login

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.studentbeans.R
import com.example.studentbeans.databinding.FragmentLoginBinding
import com.example.studentbeans.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // These three are simply for easier access and not typing it over and over
        val email = binding.editTextTextEmailAddress
        val password = binding.editTextTextPassword
        val emailErrorMessage = binding.incorrectEmailMessage

        email.text = viewModel.getEmail().toEditable()
        password.text = viewModel.getPassword().toEditable()

        //initialisations in event of rotation
        email.background = ContextCompat.getDrawable(requireActivity(),R.drawable.edit_text_drawable_default)
        password.background = ContextCompat.getDrawable(requireActivity(), R.drawable.edit_text_drawable_default)
        /*
           if email is focused, show blue
           if not focused, check if empty, if not empty,
           check if incorrect email format to paint red, else default
         */
        email.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus){
                email.background = ContextCompat.getDrawable(requireActivity(),
                    R.drawable.edit_text_drawable_selected
                )
                emailErrorMessage.visibility = View.GONE
            }
            else{
                email.background = ContextCompat.getDrawable(requireActivity(),
                    R.drawable.edit_text_drawable_default
                )
                if(email.text.isNotEmpty()){
                    if (!correctEmailFormat(email.text.toString())){
                        email.background = ContextCompat.getDrawable(requireActivity(),
                            R.drawable.edit_text_drawable_error
                        )
                        emailErrorMessage.visibility = View.VISIBLE
                    }
                }
            }
        }

        // Save every letter, in case of rotation or moving to other fragment
        email.doOnTextChanged { _, _, _, _ ->
            viewModel.setEmail(email.text.toString())
            // This is mostly if login button is pressed and the email was wrong but was in focus
            // it might not lose focus so it has to be checked here if we correct it without losing focus
            if(correctEmailFormat(email.text.toString())){
                email.background = ContextCompat.getDrawable(requireActivity(), R.drawable.edit_text_drawable_selected)
                emailErrorMessage.visibility = View.GONE
            }
        }
        password.doOnTextChanged { _, _, _, _ ->
            viewModel.setPassword(password.text.toString())
        }

        // change background depending on password focus
        password.setOnFocusChangeListener{_, hasFocus ->
            // We do not care for correct password format so simply change the background every time the focus changes
            if (hasFocus){
                password.background = ContextCompat.getDrawable(requireActivity(), R.drawable.edit_text_drawable_selected)
            }
            else {
                password.background = ContextCompat.getDrawable(requireActivity(), R.drawable.edit_text_drawable_default)
            }
        }

        /*
        When login button is pressed, first check if any is empty and provide appropriate message
        if both have something, check for correct email format and show message if error
        if all ok, first remove password from viewmodel for safety and then go to the other fragment
         */
        binding.loginButton.setOnClickListener{

            if (email.text.isEmpty() || password.text.isEmpty()){
                Toast.makeText(context, "The email and password fields cannot be empty", Toast.LENGTH_SHORT).show()
            }else{
                if (correctEmailFormat(email.text.toString())){ // Only care about email format, password just must not be empty
                    viewModel.setPassword("") // for safety remove password completely
                    Navigation.findNavController(binding.root).navigate(R.id.imagesFragment)
                }
                else { // Wrong email, password is ok
                    Toast.makeText(context, "Please provide a valid email", Toast.LENGTH_SHORT).show()
                    email.background = ContextCompat.getDrawable(requireActivity(),R.drawable.edit_text_drawable_error)
                    emailErrorMessage.visibility = View.VISIBLE
                }
            }
        }
    }

    // Clean way to set text to TextView from String
    private fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

     private fun correctEmailFormat(text: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches() // simple pattern match for email
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}