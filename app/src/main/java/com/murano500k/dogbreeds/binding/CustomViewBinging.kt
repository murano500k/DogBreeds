package com.murano500k.dogbreeds.binding

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.flexbox.*
import com.murano500k.dogbreeds.R
import com.murano500k.dogbreeds.gone
import com.murano500k.dogbreeds.viewmodel.LoadingState


@SuppressLint("WrongConstant")
@BindingAdapter(value = ["setImageUrl"])
fun ImageView.bindImageUrl(url: String?) {
    if (url != null && url.isNotBlank()) {


        Glide.with(context)
            .load(url)
            .centerCrop()
            .apply(
                RequestOptions()

                    .placeholder(R.drawable.ic_baseline_hourglass_empty_24)
                    .error(R.drawable.ic_baseline_broken_image_24)
            )
            .into(this)
    }
}


@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}


@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run {

        this.layoutManager = FlexboxLayoutManager(context).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            //alignItems = AlignItems.STRETCH
            justifyContent = JustifyContent.SPACE_EVENLY

        }
        this.adapter = adapter
    }
}

@BindingAdapter(value = ["setupVisibility"])
fun ProgressBar.progressVisibility(loadingState: LoadingState?) {
    loadingState?.let {
        isVisible = when(it.status) {
            LoadingState.Status.RUNNING -> true
            LoadingState.Status.SUCCESS -> false
            LoadingState.Status.FAILED -> false
        }
    }
}
@BindingAdapter("gone")
fun bindGone(view: View, shouldBeGone: Boolean) {
    view.gone(shouldBeGone)
}