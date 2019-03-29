package com.brion.subwayproject.route.model

class RouteMatching {
    lateinit var totalTime: String
    lateinit var startEndTime:String
    lateinit var steps:String
    lateinit var transfer:String
    lateinit var price:String
    lateinit var paths:Array<Road>

    class Road{
        lateinit var from:String
        lateinit var to:String
        lateinit var lineNumber:String
        lateinit var fastTransferLoc:String
        lateinit var walkingTime:String
    }

    fun loadInfo(mapper: RouteMapper) {
        totalTime = mapper.route.totalTime
        var timeString = mapper.route.writeDate.split(" ")[1].split(":")
        var h1 = timeString[0]
        var m1 = timeString[1]
        var m2 = (m1 + totalTime).toInt()
        var h2 = h1.toInt()
        if(m2 > 60){
            m2 -= 60
            h2 += 1
        }
        if(h2 > 23) h2 -= 24
        startEndTime = "$h1:$m1~$h2:$m2"
    }
}