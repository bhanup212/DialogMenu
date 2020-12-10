package com.dialog.options

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DefaultItemAnimator
import com.dialog.options.databinding.FragmentOptionsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OptionsFragment : BottomSheetDialogFragment(), OptionClickListener {

    private lateinit var binding: FragmentOptionsBinding
    private lateinit var optionsAdapter: OptionsAdapter
    private val options: ArrayList<String> = ArrayList()
    private var negativeText: String? = null
    private var optionClickListener: OptionClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE,R.style.FullDialog)
        dialog?.window?.let { window ->
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setNegativeText()
    }

    private fun setAdapter() {
        optionsAdapter = OptionsAdapter(this, options)
        binding.optionsRv.itemAnimator = DefaultItemAnimator()
        binding.optionsRv.adapter = optionsAdapter
        optionsAdapter.notifyDataSetChanged()
    }

    private fun setNegativeText(){
        negativeText?.let { txt ->
            binding.negativeLayout.itemTv.text = txt
            binding.negativeLayout.itemTv.setTextColor(Color.RED)
        } ?: kotlin.run {
            binding.negativeCv.isVisible = false
        }

        binding.negativeCv.setOnClickListener {
            this.dismiss()
            optionClickListener?.onNegativeButtonClick()
        }
    }

    fun showOptions(
        fragmentManager: FragmentManager,
        options: ArrayList<String>,
        negativeText: String?,
        optionClickListener: OptionClickListener,
    ) {
        this.optionClickListener = optionClickListener
        this.options.addAll(options)
        this.negativeText = negativeText
        show(fragmentManager, OptionsFragment::class.java.simpleName)
    }

    override fun onClick(text: String, position: Int) {
        this.dismiss()
        optionClickListener?.onClick(text, position)
    }

    override fun onNegativeButtonClick() {
        // TODO
    }
}