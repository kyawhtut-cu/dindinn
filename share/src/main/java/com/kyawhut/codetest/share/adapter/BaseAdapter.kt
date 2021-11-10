package com.kyawhut.codetest.share.adapter

import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * @author kyawhtut
 * @date 11/8/21
 */
abstract class BaseAdapter<D>(
    protected val itemClickListener: (Int, D) -> Unit = { _, _ -> }
) : RecyclerView.Adapter<BaseViewHolder<*, D>>(), Filterable {

    private val itemList: MutableList<D> = mutableListOf()
    private val originalList: MutableList<D> = mutableListOf()
    open val predicate: (D, String) -> Boolean = { _, _ -> true }

    override fun onBindViewHolder(holder: BaseViewHolder<*, D>, position: Int) {
        holder.data = itemList[position]
        holder.bind()
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(data: D) {
        itemList.add(data)
        originalList.add(data)
        notifyItemInserted(itemList.size)
    }

    fun addItem(index: Int, data: D) {
        itemList.add(index, data)
        originalList.add(index, data)
        notifyItemInserted(index)
    }

    fun addAll(data: List<D>) {
        itemList.addAll(itemList.size, data)
        originalList.addAll(originalList.size, data)
        notifyItemRangeInserted(itemList.size - data.size, data.size)
    }

    fun addAll(index: Int, data: List<D>) {
        itemList.addAll(index, data)
        originalList.addAll(index, data)
        notifyItemRangeInserted(index, data.size)
    }

    fun update(index: Int, data: D) {
        itemList.removeAt(index)
        itemList.add(index, data)
        originalList.removeAt(index)
        originalList.add(index, data)
        notifyItemChanged(index)
    }

    fun get(index: Int): D? {
        return itemList[index]
    }

    fun get(predicate: (D) -> Boolean): D {
        return itemList.first(predicate)
    }

    fun indexOf(data: D): Int {
        return itemList.indexOf(data)
    }

    fun indexOfFirst(predicate: (D) -> Boolean): Int {
        return itemList.indexOfFirst(predicate)
    }

    fun clear() {
        val totalCount = itemList.size
        itemList.clear()
        originalList.clear()
        notifyItemRangeRemoved(0, totalCount)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val filteredResults = if (constraint.isEmpty()) {
                    originalList
                } else {
                    getFilteredResults(
                        constraint.toString().toLowerCase(
                            Locale.ENGLISH
                        )
                    ).toMutableList()
                }

                val results = FilterResults()
                results.values = filteredResults
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemList.clear()
                itemList.addAll(results?.values as MutableList<D>)
                notifyDataSetChanged()
            }

        }
    }

    protected fun getFilteredResults(constraint: String): List<D> {
        return originalList.filter {
            predicate(it, constraint)
        }
    }

}
