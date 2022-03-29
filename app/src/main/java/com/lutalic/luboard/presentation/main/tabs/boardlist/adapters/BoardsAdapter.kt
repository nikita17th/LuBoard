package com.lutalic.luboard.presentation.main.tabs.boardlist.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lutalic.luboard.databinding.BoardPreviewViewBinding
import com.lutalic.luboard.model.boards.entities.Board

class BoardsAdapter(
    private val onBoardClickListener: Listener
) : RecyclerView.Adapter<BoardsAdapter.BoardViewHolder>() {
    private val boards: MutableList<Board> = mutableListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BoardViewHolder {
        val view = BoardPreviewViewBinding.inflate(LayoutInflater.from(viewGroup.context))
        view.bodyOfBoardCard.setOnClickListener { v: View ->
            onBoardClickListener.onBoardClick(v.tag as Board)
        }
        return BoardViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: BoardViewHolder, position: Int) {
        val board = boards[position]
        viewHolder.bind(board)
        viewHolder.binding.bodyOfBoardCard.tag = board
    }

    override fun getItemCount() = boards.size

    fun updateAdapter(newList: List<Board>) {
        boards.clear()
        boards.addAll(newList)
        notifyDataSetChanged() //TODO Is it possible to do better
    }

    class BoardViewHolder(val binding: BoardPreviewViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(board: Board) {
            binding.apply {
                titleBoard.text = board.name
                userCount.text = board.countUsers.toString()
            }
        }
    }

    interface Listener {
        fun onBoardClick(board: Board)
    }
}