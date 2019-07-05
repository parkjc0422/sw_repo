package com.brion.subwayproject.route.model

import com.google.gson.annotations.SerializedName;
import java.io.Serializable

open class JsonInfoModel :Serializable {

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("bucket")
    lateinit var bucket: String

    @SerializedName("generation")
    lateinit var generation: String

    @SerializedName("metageneration")
    lateinit var metageneration: String

    @SerializedName("contentType")
    lateinit var contentType: String

    @SerializedName("timeCreated")
    lateinit var timeCreated: String

    @SerializedName("updated")
    lateinit var updated: String

    @SerializedName("storageClass")
    lateinit var storageClass: String

    @SerializedName("size")
    lateinit var size: String

    @SerializedName("md5Hash")
    lateinit var md5Hash: String

    @SerializedName("contentEncoding")
    lateinit var contentEncoding: String

    @SerializedName("contentDisposition")
    lateinit var contentDisposition: String

    @SerializedName("crc32c")
    lateinit var crc32c: String

    @SerializedName("etag")
    lateinit var etag: String

    @SerializedName("downloadTokens")
    lateinit var downloadTokens: String
}
