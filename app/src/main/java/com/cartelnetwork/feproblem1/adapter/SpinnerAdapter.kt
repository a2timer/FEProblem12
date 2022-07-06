package com.cartelnetwork.feproblem1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.cartelnetwork.feproblem1.R


class SpinnerAdapter(val context: Context,val resource: Int,val list: ArrayList<SpinnerItem>) :
    BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {
       return list.size
    }

    override fun getItem(p0: Int): Any {
        return resource
    }

    override fun getItemId(p0: Int): Long {
       return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View
        val vh: ItemHolder
        if (p1 == null) {
            view = inflater.inflate(R.layout.spinner_layout, p2, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = p1
            vh = view.tag as ItemHolder
        }
        vh.label.text = list.get(p0).planetName


       // vh.img.setBackgroundResource(id)

        return view
    }
    private class ItemHolder(row: View?) {
        val label: TextView
        val img: ImageView

        init {
            label = row?.findViewById(R.id.txt_spinner_name) as TextView
            img = row?.findViewById(R.id.img_spinner) as ImageView
        }
    }
}