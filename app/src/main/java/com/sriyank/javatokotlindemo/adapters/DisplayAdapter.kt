package com.sriyank.javatokotlindemo.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sriyank.javatokotlindemo.R
import com.sriyank.javatokotlindemo.adapters.DisplayAdapter.MyViewHolder
import com.sriyank.javatokotlindemo.app.Util
import com.sriyank.javatokotlindemo.app.toast
import com.sriyank.javatokotlindemo.models.Repository
import kotlinx.android.synthetic.main.list_item.view.*

class DisplayAdapter(private val context: Context, private var repositoryList: List<Repository>) :
    RecyclerView.Adapter<MyViewHolder>() {
    companion object {
        private val TAG = DisplayAdapter::class.java.simpleName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = repositoryList[position]
        holder.setData(current, position)
    }

    override fun getItemCount(): Int = repositoryList.size

    fun swap(dataList: List<Repository>) {
        if (dataList.isEmpty())
            context.toast("No Items Found")
        repositoryList = dataList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var pos = 0
        private var current: Repository? = null

        init {
            itemView.imgBookmark.setOnClickListener { bookmarkRepository(current) }
            itemView.setOnClickListener {
                current?.let {
                    val url = current!!.htmlUrl
                    val webpage = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, webpage)
                    if (intent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(intent)
                    }
                }
            }
        }

        fun setData(current: Repository?, position: Int) {
            current?.let {
                itemView.txvName.text = current.name
                itemView.txvLanguage.text = current.language
                itemView.txvForks.text = current.forks.toString()
                itemView.txvWatchers.text = current.watchers.toString()
                itemView.txvStars.text = current.stars.toString()
            }
            this.pos = position
            this.current = current
        }

        private fun bookmarkRepository(current: Repository?) {

            /*Realm realm = Realm.getDefaultInstance();
			realm.executeTransactionAsync(new Realm.Transaction() {
				@Override
				public void execute(@NonNull Realm realm) {
					realm.copyToRealmOrUpdate(current);
				}
			}, new Realm.Transaction.OnSuccess() {
				@Override
				public void onSuccess() {
					Util.showMessage(mContext, "Bookmarked Successfully");
				}
			}, new Realm.Transaction.OnError() {
				@Override
				public void onError(Throwable error) {
					Log.i(TAG, error.toString());
					Util.showMessage(mContext, "Error Occurred");
				}
			});*/
        }
    }
}
