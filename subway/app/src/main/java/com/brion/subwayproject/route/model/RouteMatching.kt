package com.brion.subwayproject.route.model

import android.content.Context
import com.brion.subwayproject.utils.getColor

class RouteMatching(val context: Context) {
    lateinit var totalTime: String
    lateinit var startEndTime:String
    lateinit var steps:String
    lateinit var transfer:String
    lateinit var price:String
    lateinit var paths:MutableList<Road>
    lateinit var transfers:MutableList<TransferRoad>

    class Road{
        var from = ""
        var to= ""
        var lineColor = -1
    }

    class TransferRoad {
        var fastTransferLoc = ""
        var walkingTime = ""
    }

    fun loadInfo(mapper: RouteMapper) {
        totalTime = "${mapper.route.totalTime}"
        var timeString = mapper.route.writeDate.split(" ")[1].split(":")
        var h1 = timeString[0].toInt()
        var m1 = timeString[1].toInt()
        var totalMin = totalTime.toInt()
        var h2 = h1 + (totalMin / 60)
        var m2 = m1 + (totalMin % 60)
        if(m1 >= 60) {
            h1 += 1
            m1 -= 60
        }
        startEndTime = "$h1:$m1~$h2:$m2"

        steps = "${mapper.trasferRoute.sPath.pathList.size - mapper.trasferRoute.sTransfer.transferList.size}"
        transfer = "${mapper.trasferRoute.sTransfer.transferList.size}"
        price = "${mapper.route.price}"


        paths = mutableListOf()
        var items = mutableListOf<MutableList<Road>>()
        var item = mutableListOf<Road>()
        mapper.trasferRoute.sPath.pathList.forEach {
            if(it.pathType.equals("spath")) {
                var i = Road()
                i.from = it.startStationName
                i.to = it.endStationName
                i.lineColor = getColor(context, it.line)
                item.add(i)
            } else {
                items.add(item)
                item = mutableListOf<Road>()
            }
        }
        items.add(item)
        items.forEach {
            var road = Road()
            road.from = it.first().from
            road.to = it.last().to
            road.lineColor = it.last().lineColor
            paths.add(road)
        }


        transfers = mutableListOf<TransferRoad>()
        mapper.trasferRoute.sTransfer.transferList.forEach {
            var i = TransferRoad()
            i.walkingTime = "환승 (도보 ${it.timeavg}분)"
            //빠른 환승 10-4 05:42"
            i.fastTransferLoc = "빠른 환승 ${it.train}-${it.door}"
            transfers.add(i)
        }
    }
}