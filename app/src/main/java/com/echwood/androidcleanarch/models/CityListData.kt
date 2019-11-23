package com.echwood.androidcleanarch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
/**
 * @author  UkB.
 * @version v1
 */

@Generated("org.jsonschema2pojo")
data class CityListData (

    @SerializedName("id")
    @Expose
    val id:String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("background")
    @Expose
    val background: String
)