package com.sriyank.javatokotlindemo.models

import com.google.gson.annotations.SerializedName

class SearchResponse {
    @SerializedName("total_count")
    var totalCount = 0
    var items: List<Repository>? = null
}
