package com.example.githubapp.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.Utils.ViewState
import com.example.githubapp.data.model.UserData
import com.example.githubapp.view.adapter.UsersAdapter
import com.example.githubapp.viewmodel.UsersViewModel


class UsersListFragment : Fragment() {
    var rv: RecyclerView? = null
    var progressBar: ProgressBar? = null
    private lateinit var linearLayoutManager: LinearLayoutManager


    private lateinit var galleryAdapter:UsersAdapter
    private lateinit var  viewModel: UsersViewModel


    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString()
                    + " must implement MovieListListener")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.users_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        galleryAdapter = UsersAdapter(activity as Context);
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = view.findViewById<View>(R.id.recyclerview) as RecyclerView
        progressBar= view.findViewById<View>(R.id.exception_progressbar) as ProgressBar
        progressBar?.visibility = View.VISIBLE
        view.findViewById<View>(R.id.empty_view).visibility=View.VISIBLE
        linearLayoutManager= LinearLayoutManager(activity)
        rv!!.layoutManager = linearLayoutManager;
        rv!!.hasFixedSize()

    }


    fun getViewModelInstance() {
        viewModel = activity?.run { ViewModelProviders.of(this).get(UsersViewModel::class.java) }
                ?: throw Exception("Invalid Activity")

    }

     override fun onResume() {
        super.onResume()
         getViewModelInstance()
         getFirstPageData()
    }

     fun getFirstPageData() {

         viewModel.getUsers().observe(getViewLifecycleOwner(),  Observer {
             when (it) {
                 is ViewState.Success -> {
                     progressBar?.visibility = View.GONE
                     view?.findViewById<View>(R.id.empty_view)?.visibility=View.GONE
                     galleryAdapter.setUsersList(it.data as ArrayList<UserData>)
                     rv!!.adapter = galleryAdapter}
                 is ViewState.Loading -> {progressBar?.visibility = View.GONE
                     view?.findViewById<View>(R.id.empty_view)?.visibility=View.GONE}
                 is ViewState.Error -> {
                     progressBar?.visibility = View.GONE
                     view?.findViewById<View>(R.id.empty_view)?.visibility=View.GONE

                     Toast.makeText(activity,"Something went wrong ¯\\_(ツ)_/¯ => ${it.message}",Toast.LENGTH_SHORT)}
             }

          })
    }







}