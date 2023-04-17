package com.breakapp.apv2.ui.configs.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.breakapp.apv2.R
import com.breakapp.apv2.databinding.ItemUserListBinding
import com.breakapp.apv2.retrofit.pojos.Users

class AdapterListUsersConfig(
    private val context: Context,
    private val agendalist: List<Users>,
    private val itemClickListener: OnUserClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnUserClickListener {
        fun onUserClick(pokemon: Users, position: Int)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_user_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(agendalist[position], position)
        }
    }

    override fun getItemCount(): Int {
        return agendalist.size
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Users>(itemView) {
        override fun bind(item: Users, position: Int) {
            val binding = ItemUserListBinding.bind(itemView)

            binding.tvNameUser.text = item.name
            binding.tvPhoneUser.text = "Celular: "+item.phone
            binding.tvGeneroUser.text = "Sexo: "+item.gender

//            Toast.makeText(context,""+
//                    img
//                , Toast.LENGTH_SHORT).show()

            itemView.setOnClickListener {
                itemClickListener.onUserClick(item, position)
            }
        }

    }
}