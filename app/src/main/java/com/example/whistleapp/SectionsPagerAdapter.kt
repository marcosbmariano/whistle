package com.example.whistleapp2.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.whistleapp.R
import com.example.whistleapp.serverRepo.sections.CodeSectionFragment
import com.example.whistleapp.serverRepo.sections.issues.IssuesSectionFragment


class SectionsPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    val sectionsArray: Array<String> = activity.resources.getStringArray(R.array.sections)

    override fun getItemCount(): Int {
        return sectionsArray.size
    }

    override fun createFragment(position: Int): Fragment {
        return when(sectionsArray[position]){
            sectionsArray[0] ->{
                codeSectionFragment
            }

            sectionsArray[1] ->{
                issuesSectionFragment
            }

            else -> {
                issuesSectionFragment
            }
        }
    }

    companion object{
        val codeSectionFragment : CodeSectionFragment by
        lazy (LazyThreadSafetyMode.PUBLICATION){ CodeSectionFragment.newInstance() }

        val issuesSectionFragment : IssuesSectionFragment by
        lazy (LazyThreadSafetyMode.PUBLICATION) { IssuesSectionFragment.newInstance()}
    }

}