package com.github.andreyrage.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_main.view.*

/**
 * Created by rage on 31.10.19
 */

class MainRecyclerAdapter(private val callback: AdapterCallback) : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>(){
    interface AdapterCallback {
        fun onLongItemClick(device: Device)
    }

    private val items = mutableListOf<Device>()

    fun addItem(device: Device) {
        items.add(device)
        notifyItemInserted(items.size)
    }

    fun deleteItem(device: Device) {
        val position = items.indexOf(device)
        items.remove(device)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(device = items[position])
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view),
        View.OnLongClickListener {

        private lateinit var device: Device

        init {
            view.setOnLongClickListener(this)
        }

        fun onBind(device: Device) {
            view.name_edit.text = device.name
            this.device = device
        }

        override fun onLongClick(v: View?): Boolean {
            callback.onLongItemClick(device)
            return true
        }
    }
}