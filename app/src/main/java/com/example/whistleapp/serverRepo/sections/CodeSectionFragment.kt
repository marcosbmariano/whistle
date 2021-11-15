package com.example.whistleapp.serverRepo.sections

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.whistleapp.databinding.FragmentCodeSectionBinding


class CodeSectionFragment : Fragment() {

    private lateinit var bnd : FragmentCodeSectionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bnd = FragmentCodeSectionBinding.inflate(inflater, container, false)
        return bnd.root
    }



    companion object {
        @JvmStatic
        fun newInstance() = CodeSectionFragment()
    }
}