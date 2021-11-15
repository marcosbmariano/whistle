package com.example.whistleapp.serverRepo.sections.issues

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whistleapp.databinding.IssueItemBinding
import com.example.whistleapp.serverRepo.data.IssueItem
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class IssuesRcvAdapter : RecyclerView.Adapter<IssuesRcvAdapter.VH>() {
    private val issuesList = mutableListOf<IssueItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = IssueItemBinding.inflate(LayoutInflater.from(parent.context))
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.populateView(issuesList[position])
    }

    override fun getItemCount(): Int {
        return issuesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(issuesResult: ArrayList<IssueItem>){
        issuesList.clear()
        issuesList.addAll(issuesResult.toList())
        notifyDataSetChanged()
    }


    class VH(binding: IssueItemBinding) : RecyclerView.ViewHolder(binding.root){
        private val bnd : IssueItemBinding = binding

        fun populateView(issue : IssueItem){
            bnd.txtTitle.text = issue.title
            bnd.txtSeconLabel.text = getSecondaryLabelText(issue)

        }



        //open == size
        //title
        //opened
        //createdAt=2021-11-11T22:12:33Z
        //number=7361
        //state=open
        //login=Chaostical
        //link htmlUrl=https://github.com/ReactiveX/RxJava/issues/736


        private fun getDateOfOpening(date: String) :String {
            val currentTime = Date()
            //very hackish this date parsing
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val issueDate = dateFormat.parse(date.split("T")[0])
            val calendar = Calendar.getInstance(dateFormat.timeZone)
            calendar.time = currentTime
            var days = calendar.get(Calendar.DAY_OF_YEAR)
            calendar.time = issueDate
            days -= calendar.get(Calendar.DAY_OF_YEAR)


            return when {
                days == 1 -> {
                    " yesterday"
                }
                days < 30 -> {
                    "$days days ago"
                }
                else -> {
                    val resultFormat = SimpleDateFormat("MMM dd", Locale.US)
                    resultFormat.format(issueDate!!)
                }
            }

        }

        private fun getSecondaryLabelText(issue : IssueItem) : String{
            return "#${issue.number} " +
                    "opened ${getDateOfOpening(issue.createdAt)} by ${issue.user.login}"
        }
    }


}