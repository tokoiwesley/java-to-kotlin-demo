package com.sriyank.javatokotlindemo.models

import com.google.gson.annotations.SerializedName

class SearchResponse(
    @SerializedName("total_count") var totalCount: Int,
    var items: List<Repository>?
)
