package com.demoxample.shaadicardsample.ui

import android.animation.ValueAnimator
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.DisplayMetrics
import android.view.View
import com.demoxample.shaadicardsample.R
import com.demoxample.shaadicardsample.databinding.ActivityMainBinding
import com.demoxample.shaadicardsample.model.User
import com.demoxample.shaadicardsample.network.Resource
import com.demoxample.shaadicardsample.network.Status

class MainActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var listObserver: Observer<Resource<ArrayList<User>?>>
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    private lateinit var adapter: UsersAdapter

    private var displayMetrics = DisplayMetrics()
    private var scale: Float = 0.0f
    private var distance: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        distance = 9000
        scale = resources.displayMetrics.density
        initObserver()
        getUserList()
    }

    private fun initObserver() {
        listObserver = Observer {
            when (it?.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    setAdapter(it.data)
                }
                else -> {
                    Snackbar.make(binding.root, getString(R.string.error_message), Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setAdapter(data: ArrayList<User>?) {
        data?.run {
            binding.rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = UsersAdapter(this, this@MainActivity)
            binding.rvUser.adapter = adapter
        }
    }

    private fun getUserList() {
        val params = HashMap<String, Int>()
        params["results"] = 10
        mainViewModel.getShaadiUsers(params).observe(this, listObserver)
    }

    override fun accept(view: View, position: Int) {
        slideRight(view, position)
    }

    override fun decline(view: View, position: Int) {
        slideLeft(view, position)
    }

    private fun slideRight(view: View, position: Int) {
        view.cameraDistance = distance * scale
        val width = displayMetrics.widthPixels
        val slideAnimator = ValueAnimator.ofFloat(0f, 1f)
        slideAnimator.addUpdateListener { valueAnimator ->
            if (valueAnimator.animatedValue == 0.5f) {
                view.elevation = view.elevation - view.elevation * (valueAnimator.animatedFraction + 0.4f)
            }
            view.x = valueAnimator.animatedFraction * width
            view.rotationY = 360f + valueAnimator.animatedFraction * 70f
            view.rotation = 360f + valueAnimator.animatedFraction * 30f
            if (valueAnimator.animatedValue == 1f) {
                adapter.notifyItemRemoved(position)
                binding.rvUser.removeView(view)
            }
        }
        slideAnimator.duration = 400
        slideAnimator.start()
    }

    private fun slideLeft(view: View, position: Int) {
        view.cameraDistance = distance * scale
        val width = displayMetrics.widthPixels
        val slideAnimator = ValueAnimator.ofFloat(0f, 1f)
        slideAnimator.addUpdateListener { valueAnimator ->
            if (valueAnimator.animatedFraction == 0.5f) {
                view.elevation = view.elevation - view.elevation * (valueAnimator.animatedFraction + 0.4f)
            }
            view.x = -valueAnimator.animatedFraction * width
            view.rotationY = 360f - valueAnimator.animatedFraction * 70f
            view.rotation = 360f - valueAnimator.animatedFraction * 30f
            if (valueAnimator.animatedValue == 1f) {
                adapter.notifyItemRemoved(position)
                binding.rvUser.removeView(view)
            }
        }
        slideAnimator.duration = 400
        slideAnimator.start()
    }


}
