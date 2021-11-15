package com.example.whistleapp.serverRepo.sections.issues

import androidx.lifecycle.ViewModel
import com.example.whistleapp.serverRepo.GitRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.whistleapp.serverRepo.data.IssuesResult


class IssuesViewModel : ViewModel() {


    private val gitService = GitRepo.gitService
    private val compositeDisposable = CompositeDisposable()


    private val issueMutableLiveData = MutableLiveData<IssuesResult>()
    val issues :  LiveData<IssuesResult>
        get() = issueMutableLiveData



    fun requestIssues(){
        compositeDisposable.add(
        gitService.getIssues()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->

                if( result != null){
                    issueMutableLiveData.value = result
                }
            }

        )
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}
