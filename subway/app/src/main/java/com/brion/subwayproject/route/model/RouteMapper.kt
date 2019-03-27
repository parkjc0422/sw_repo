package com.brion.subwayproject.route.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by jucherpark on 27/03/2019.
 */
class RouteMapper{

    enum class RouteType {
        NextNode, NoTransferNode, TransferNode
    }
    lateinit var route:Route


    lateinit var routeType:RouteType
}