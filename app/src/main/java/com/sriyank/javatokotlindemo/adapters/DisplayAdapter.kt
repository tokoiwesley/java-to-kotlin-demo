package com.sriyank.javatokotlindemo.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sriyank.javatokotlindemo.R
import com.sriyank.javatokotlindemo.adapters.DisplayAdapter.MyViewHolder
import com.sriyank.javatokotlindemo.app.Util
import com.sriyank.javatokotlindemo.models.Repository
import kotlinx.android.synthetic.main.list_item.view.*

class DisplayAdapter(context: Context, items: List<Repository>) :
    RecyclerView.Adapter<MyViewHolder>() {
    private var mData: List<Repository>
    private val inflater: LayoutInflater
    private val mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = mData[position]
        holder.setData(current, position)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun swap(data: List<Repository>) {
        if (data.size == 0) Util.showMessage(mContext, "No Items Found")
        mData = data
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var pos= 0
        private var current: Repository? = null

        init {
            itemView.imgBookmark.setOnClickListener { bookmarkRepository(current) }
            itemView.setOnClickListener {
                val url = current!!.htmlUrl
                val webpage = Uri.parse(url)
                val intent = Intent(Intent.ACTION_VIEW, webpage)
                if (intent.resolveActivity(mContext.packageManager) != null) {
                    mContext.startActivity(intent)
                }
            }
        }

        fun setData(current: Repository, position: Int) {
            itemView.txvName.text = current.name
            itemView.txvLanguage.text = current.language.toString()
            itemView.txvForks.text = current.forks.toString()
            itemView.txvWatchers.text = current.watchers.toString()
            itemView.txvStars.text = current.stars.toString()
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

    companion object {
        private val TAG = DisplayAdapter::class.java.simpleName
    }

    init {
        inflater = LayoutInflater.from(context)
        mData = items
        mContext = context
    }
}