package com.brion.subwayproject.http

fun getRequestBody (startId:String, arrivalId:String) :String = "departureId=$startId&stopId=&arrivalId=$arrivalId&sKind=1&sTime=&weekTag=&__encrypted="
fun getRequestBodyWithType (startId:String,
                            arrivalId:String,
                            type:String) :String = "departureId=$startId&stopId=&arrivalId=$arrivalId&sKind=$type&sTime=&weekTag=&__encrypted="