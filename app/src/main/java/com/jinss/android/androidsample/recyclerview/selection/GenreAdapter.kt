package com.jinss.android.androidsample.recyclerview.selection

import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jinss.android.androidsample.R
import kotlinx.android.synthetic.main.layout_recyclerview_genre_item.view.*

class GenreAdapter :  ListAdapter<Genre, NormalItemViewHolder>(GenreDiffUtil()) {

    var selectedPosition = 0
    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id.hashCode().toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NormalItemViewHolder {
        println("create viewholder $selectedPosition")
        return NormalItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_recyclerview_genre_item, parent, false)
        ).apply {
            setOnClickListener {
                notifyItemChanged(selectedPosition)
                selectedPosition = absoluteAdapterPosition
                notifyItemChanged(selectedPosition)
            }
        }
    }
    override fun onBindViewHolder(holder: NormalItemViewHolder, position: Int) {
        println("onBindViewHolder current position: $position selectedPosition $selectedPosition")
        holder.bind(getItem(position))
        holder.textView.isSelected = (position == selectedPosition)
    }

    override fun getItemCount() = currentList.size


    // 아이템이 변화였을때 불리는 콜백
    // 여기서 과거와 현재의 사이즈를 비교해서 적절히 넣어주는 로직을 추가하면 될거같다.
    override fun onCurrentListChanged(
        previousList: MutableList<Genre>,
        currentList: MutableList<Genre>
    ) {
        println("onCurrentListChanged ${previousList.size} ${currentList.size}")

        var diff = 0
        if (currentList.size > 0 && previousList.size == 0) {
            diff = 0
            println("diff 0")
        }  else if (previousList.size != currentList.size) {
            diff = currentList.size - previousList.size
            println("diff $diff")
        }
        selectedPosition += diff
        println("new selection position:$selectedPosition")
        notifyItemChanged(selectedPosition) // onBind가 안불리는 애들은 이런식으로 직접 update 시켜주어야 한다.
        super.onCurrentListChanged(previousList, currentList)
    }
}

class NormalItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView: TextView = itemView.findViewById(R.id.genre_id)

    fun bind(genre: Genre) {
        textView.text = genre.id
    }

    fun setOnClickListener(onClick: (v: View)->Unit) {
        textView.setOnClickListener(onClick)
    }
}

// 설명 잘되어있음.
// https://zion830.tistory.com/86
class GenreDiffUtil : DiffUtil.ItemCallback<Genre>() {
    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem == newItem
    }
}