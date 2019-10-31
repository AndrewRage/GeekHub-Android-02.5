package com.github.andreyrage.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainRecyclerAdapter.AdapterCallback,
    View.OnClickListener {

    private val adapter by lazy { MainRecyclerAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.adapter = adapter

        add_device_item.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val device = Device(name_edit.text.toString(), sdk_edit.text.toString().toInt())
        adapter.addItem(device)
    }

    override fun onLongItemClick(device: Device) {
        adapter.deleteItem(device)
    }

}
