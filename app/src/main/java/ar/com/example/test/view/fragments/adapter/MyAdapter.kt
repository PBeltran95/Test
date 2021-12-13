package ar.com.example.test.view.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.com.example.test.R
import ar.com.example.test.data.models.Person
import ar.com.example.test.databinding.UsersItemBinding

class MyAdapter(): RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    var listItems = mutableListOf<Person>()

    fun setData(newList: MutableList<Person>){
        this.listItems = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.users_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItems[position]
        with(holder){
            binding.tvName.text = item.name
            binding.tvLastName.text = item.lastName
            binding.tvAge.text = item.age.toString()
            binding.tvDni.text = item.dni.toString()
        }
    }

    override fun getItemCount(): Int = listItems.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = UsersItemBinding.bind(view)
    }

}