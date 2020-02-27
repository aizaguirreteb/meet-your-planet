package com.iesvirgendelcarmen.meetyourplanet.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.iesvirgendelcarmen.meetyourplanet.MainActivity
import com.iesvirgendelcarmen.meetyourplanet.R
import com.iesvirgendelcarmen.meetyourplanet.model.SystemViewModel
import com.iesvirgendelcarmen.meetyourplanet.model.User
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {


    private lateinit var loginDone: LoginDone
    private val viewModel: SystemViewModel by lazy {
        ViewModelProviders.of(this).get(SystemViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            doLogin()
        }
        viewModel.loginLiveData.observe(viewLifecycleOwner, Observer {
            loginDone.onLoginDone(it)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LoginDone) {
            loginDone = context
        }
    }

    private fun doLogin() {
        val email = etFormEmailLogin.text.toString()
        val password = etFormPassLogin.text.toString()
        viewModel.login(User(email, password, ""))

    }


    interface LoginDone {
        fun onLoginDone(status: Boolean)
    }
}