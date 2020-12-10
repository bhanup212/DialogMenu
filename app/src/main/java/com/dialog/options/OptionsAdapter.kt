package com.dialog.options

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.dialog.options.databinding.OptionRowLayoutBinding

class OptionsAdapter(
    private val optionClickListener: OptionClickListener,
    private val options: ArrayList<String>
) : RecyclerView.Adapter<OptionsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OptionRowLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = options[position]
        holder.bindData(title)
        holder.binding.dividerView.isVisible = position != options.size-1
        holder.binding.root.setOnClickListener {
            optionClickListener.onClick(title, position)
        }
    }

    override fun getItemCount(): Int {
        return options.size
    }

    inner class ViewHolder(val binding: OptionRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(title: String) {
            binding.itemTv.text = title
        }
    }
}