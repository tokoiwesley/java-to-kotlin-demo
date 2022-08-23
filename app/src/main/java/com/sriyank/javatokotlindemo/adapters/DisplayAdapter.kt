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
import com.sriyank.javatokotlindemo.adapters.DisplayAdapter
import com.sriyank.javatokotlindemo.adapters.DisplayAdapter.MyViewHolder
import com.sriyank.javatokotlindemo.app.Util
import com.sriyank.javatokotlindemo.models.Repository

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
        private val name: TextView
        private val language: TextView
        private val stars: TextView
        private val watchers: TextView
        private val forks: TextView
        private var position = 0
        private val imgBookmark: ImageView
        private var current: Repository? = null
        fun setData(current: Repository, position: Int) {
            name.text = current.name
            language.text = current.language.toString()
            forks.text = current.forks.toString()
            watchers.text = current.watchers.toString()
            stars.text = current.stars.toString()
            this.position = position
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

        init {
            name = itemView.findViewById(R.id.txvName)
            language = itemView.findViewById(R.id.txvLanguage)
            stars = itemView.findViewById(R.id.txvStars)
            watchers = itemView.findViewById(R.id.txvWatchers)
            forks = itemView.findViewById(R.id.txvForks)
            imgBookmark = itemView.findViewById(R.id.img_bookmark)
            imgBookmark.setOnClickListener { bookmarkRepository(current) }
            itemView.setOnClickListener {
                val url = current!!.htmlUrl
                val webpage = Uri.parse(url)
                val intent = Intent(Intent.ACTION_VIEW, webpage)
                if (intent.resolveActivity(mContext.packageManager) != null) {
                    mContext.startActivity(intent)
                }
            }
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