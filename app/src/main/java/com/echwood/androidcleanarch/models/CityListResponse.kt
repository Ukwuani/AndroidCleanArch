package com.echwood.androidcleanarch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
/**
 * @author  UkB.
 * @version v1
 */

@Generated("org.jsonschema2pojo")
data class CityListResponse (
    @SerializedName("data")
    @Expose
    var data: ArrayList<CityListData>,
    @SerializedName("message")
    @Expose
    var  message: String,
    @SerializedName("status")
    @Expose
    var status: Int
)