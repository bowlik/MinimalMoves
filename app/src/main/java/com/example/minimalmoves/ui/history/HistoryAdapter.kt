package com.example.minimalmoves.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minimalmoves.R
import com.example.minimalmoves.data.db.GameResultEntity

class HistoryAdapter(
    private var items: List<GameResultEntity>
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val startValue: TextView = view.findViewById(R.id.textStartValue)
        val moves: TextView = view.findViewById(R.id.textMoves)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = items[position]
        holder.startValue.text = "Start: ${item.startValue}"
        holder.moves.text = "Tahy: ${item.moves}"
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<GameResultEntity>) {
        items = newItems
        notifyDataSetChanged()
    }
}
