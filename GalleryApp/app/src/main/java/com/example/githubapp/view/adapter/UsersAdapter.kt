package com.example.githubapp.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubapp.R
import com.example.githubapp.data.model.UserData
import kotlinx.android.synthetic.main.grid_view_item.view.*


class UsersAdapter(var context: Context): RecyclerView.Adapter<UsersAdapter.GalleryViewHolder>()  {
    private var mUsersList: ArrayList<UserData>? = null

    fun setUsersList(usersList: ArrayList<UserData>) {
        mUsersList = usersList
    }
    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewItem: View = inflater.inflate(R.layout.grid_view_item, parent, false)
        var viewHolder = GalleryViewHolder(viewItem)
        return viewHolder!!

    }

    override fun getItemCount(): Int {
        return mUsersList?.size ?: 0
    }
    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        Log.e("adapter","name"+ " " + mUsersList?.get(position)?.name+ " avatar" + mUsersList?.get(position)?.avatar)
        holder.tvAuthorName.text = mUsersList?.get(position)?.name
        holder.tvStars.text= mUsersList?.get(position)?.stars?.toLowerCase()+ " Pages"
        Glide.with(context).load(mUsersList?.get(position)?.avatar).apply(RequestOptions.centerCropTransform()).into(holder.image)
    }



    fun add(userData: UserData?) {
        if (userData != null) {
            mUsersList?.add(userData)
        }
         mUsersList?.size?.minus(1)?.let { notifyItemInserted(it) }
    }

    fun addAll(usersList: ArrayList<UserData>) {
        for (result in usersList) {
            add(result)
        }
    }

    fun getItem(position: Int): UserData? {
        return mUsersList?.get(position)

    }

   inner class GalleryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvAuthorName = view.userName
        val tvStars = view.userStars
        val image= view.userImage
    }

}