package com.example.whistleapp.serverRepo.sections.issues

import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whistleapp.databinding.FragmentIssuesBinding


class IssuesSectionFragment : Fragment() {


    private lateinit var viewModel : IssuesViewModel
    private lateinit var bnd : FragmentIssuesBinding
    private lateinit var rcvAdapter: IssuesRcvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel  = ViewModelProviders.of(this)[IssuesViewModel::class.java]

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bnd = FragmentIssuesBinding.inflate(inflater, container,false)
        return bnd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if( isNetworkAvailable()){
            viewModel.requestIssues()
        }
        setupViews()
    }



    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context?.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private fun setupViews(){
        rcvAdapter = IssuesRcvAdapter()
        val layoutManager = LinearLayoutManager(context)
        bnd.rcvIssuesList.layoutManager = layoutManager
        bnd.rcvIssuesList.adapter = rcvAdapter


        viewModel.issues.observe(viewLifecycleOwner){ issuesList ->
            rcvAdapter.updateList(issuesList)
        }

    }




    companion object {

        @JvmStatic
        fun newInstance() =
            IssuesSectionFragment()

    }

}