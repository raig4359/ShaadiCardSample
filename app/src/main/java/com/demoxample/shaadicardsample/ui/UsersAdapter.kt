package com.demoxample.shaadicardsample.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.demoxample.shaadicardsample.R
import com.demoxample.shaadicardsample.databinding.LayoutUserCardBinding
import com.demoxample.shaadicardsample.model.User

/**
 * Created by Gaurav on 13-03-2019.
 */
class UsersAdapter(private val users: ArrayList<User>, private val listener: ItemClickListener) :
    RecyclerView.Adapter<UsersAdapter.Companion.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding: LayoutUserCardBinding = LayoutUserCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return UserViewHolder(binding, listener = listener)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.setBinding(users)
    }

    companion object {

        private const val COMMA_SEPARATOR = ", "
        private const val SPACE = " "

        class UserViewHolder(private val binding: LayoutUserCardBinding, private val listener: ItemClickListener) :
            RecyclerView.ViewHolder(binding.root) {

            fun setBinding(users: ArrayList<User>) {

                binding.btDecline.setOnClickListener {
                    users.removeAt(layoutPosition)
                    listener.decline(binding.root, layoutPosition)
                }
                binding.btAccept.setOnClickListener {
                    users.removeAt(layoutPosition)
                    listener.accept(binding.root, layoutPosition)
                }

                Glide.with(binding.root.context)
                    .load(users[layoutPosition].picture?.large)
                    .apply(
                        RequestOptions().circleCrop().placeholder(
                            if (users[layoutPosition].gender!!.equals("female", true))
                                R.drawable.ic_woman_default
                            else
                                R.drawable.ic_man_default
                        )
                    ).transition(DrawableTransitionOptions.withCrossFade(400))
                    .into(binding.ivUser)

                binding.tvUserName.text = users[layoutPosition].run { name?.first + SPACE + name?.last }.capitalize()

                val userSummary = users[layoutPosition].run {
                    dob?.age.toString().plus(SPACE)
                        .plus(binding.root.context.getString(R.string.yrs))
                        .plus(COMMA_SEPARATOR)
                        .plus(location?.street).plus(COMMA_SEPARATOR)
                        .plus(location?.city).plus(COMMA_SEPARATOR)
                        .plus(location?.state).plus(COMMA_SEPARATOR)
                        .plus(location?.postcode)
                }

                binding.tvProfileSummary.text = userSummary

                binding.executePendingBindings()
            }
        }
    }

}