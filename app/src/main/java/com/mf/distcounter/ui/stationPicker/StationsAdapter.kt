package com.mf.distcounter.ui.stationPicker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mf.distcounter.databinding.KeywordItemBinding
import com.mf.domain.consts.STATIONS_COUNT_TO_GET
import com.mf.domain.models.stationView.StationInfoModel

class StationsAdapter(
    private val processItemClick: StationItemClickListener,
    private var items: List<StationInfoModel>
) :
    RecyclerView.Adapter<StationsAdapter.ViewHolder>() {
    private var lastLoadPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationsAdapter.ViewHolder =
        ViewHolder(KeywordItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: StationsAdapter.ViewHolder, position: Int) =
        holder.bind(items[position])


    inner class ViewHolder(private val binding: KeywordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StationInfoModel) {
            binding.item = item
            binding.itemClick = processItemClick
            binding.executePendingBindings()
            if (adapterPosition > lastLoadPosition && itemCount < adapterPosition + 10) {
                lastLoadPosition = adapterPosition
                processItemClick.processAdapterLoad(adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun submitList(itemList: List<StationInfoModel>) {
        DiffUtil.calculateDiff(StationDiffCallback(items, itemList)).also {
            setAdapter(itemList)
            it.dispatchUpdatesTo(this)
        }
    }

    private fun setAdapter(itemList: List<StationInfoModel>) {
        if (itemCount == itemList.size || itemList.size == STATIONS_COUNT_TO_GET) {
            lastLoadPosition = 0
        }
        items = itemList
    }
}

class StationDiffCallback(
    private val oldList: List<StationInfoModel>,
    private val newList: List<StationInfoModel>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition].keyword == oldList[oldItemPosition].keyword

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition] == oldList[oldItemPosition]
}