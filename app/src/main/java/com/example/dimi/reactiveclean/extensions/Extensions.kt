package com.example.dimi.reactiveclean.extensions

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.joda.time.DateTime

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun View.visible(visible: Boolean) {
    this.visibility = when(visible) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun String.displayToast(context: Context?) {
    return Toast.makeText(context, this, Toast.LENGTH_LONG).show()
}

fun DateTime.toDisplayable(): String = this.toString("dd-MM-yyyy HH:mm:ss")