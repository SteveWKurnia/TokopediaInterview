package com.tokopedia.filter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.tokopedia.filter.R
import kotlinx.android.synthetic.main.product_item.*
import java.util.ArrayList

class FilterDialogFragment: BottomSheetDialogFragment() {

    private lateinit var listener: FilterInteraction

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.product_item, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listener = (activity as ProductActivity)
        setupSaveButton()
        receiveBundle()
    }

    private fun setupSaveButton() {
        btn_save_filter?.setOnClickListener {
            sendFilterToCallingActivity()
            if (getMinPriceFilter() > getMaxPriceFilter()) {
                Toast.makeText(context, "Price filter is facing problems!", Toast.LENGTH_SHORT).show()
            } else {
                dismiss()
            }
        }
    }

    private fun sendFilterToCallingActivity() {
        val bundle = Bundle()
        bundle.apply {
            putStringArrayList("checked_chips", getLocationFilter())
            putInt("min_price", getMinPriceFilter())
            putInt("max_price", getMaxPriceFilter())
        }
        listener.onSave(bundle)
    }

    private fun getLocationFilter(): ArrayList<String> {
        val chips = ArrayList<String>()

        val checkedChips = chip_group?.checkedChipIds
        if (checkedChips != null) {
            for (checkedChip in checkedChips) {
                val chip = view?.findViewById<Chip>(checkedChip)
                chips.add(chip?.text.toString())
            }
        }

        return chips
    }

    private fun getMinPriceFilter() = if(et_min_price?.text.toString() != "") et_min_price?.text.toString().toInt() else 0

    private fun getMaxPriceFilter() = if(et_max_price?.text.toString() != "") et_max_price?.text.toString().toInt() else 999999999

    private fun receiveBundle() {
        val bundle = arguments
        if (bundle != null) {
            chip_1?.text = bundle.getString("chip1")
            chip_2?.text = bundle.getString("chip2")
        }
    }

    interface FilterInteraction {
        fun onSave(bundle: Bundle)
    }

    companion object {

        fun show(fragmentManager: FragmentManager, chip1: String, chip2: String) {
            val dialogFragment = FilterDialogFragment()
            dialogFragment.apply {
                arguments = Bundle().apply {
                    putString("chip1", chip1)
                    putString("chip2", chip2)
                }
            }
            dialogFragment.show(fragmentManager, dialogFragment.tag)
        }

    }
}