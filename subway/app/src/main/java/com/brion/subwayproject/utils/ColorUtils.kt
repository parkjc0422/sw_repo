package com.brion.subwayproject.utils

import android.content.Context
import android.support.annotation.ColorRes
import com.brion.subwayproject.R

/**
 * Created by jucherpark on 31/03/2019.
 */
@ColorRes
fun getColor(context: Context,
             stationLine:String):Int{
    var color:Int = context.resources.getColor(R.color.line1)
    if(stationLine.equals("1"))
        color = context.resources.getColor(R.color.line1)
    if(stationLine.equals("2"))
        color = context.resources.getColor(R.color.line2)
    if(stationLine.equals("3"))
        color = context.resources.getColor(R.color.line3)
    if(stationLine.equals("4"))
        color = context.resources.getColor(R.color.line4)
    if(stationLine.equals("5"))
        color = context.resources.getColor(R.color.line5)
    if(stationLine.equals("6"))
        color = context.resources.getColor(R.color.line6)
    if(stationLine.equals("7"))
        color = context.resources.getColor(R.color.line7)
    if(stationLine.equals("8"))
        color = context.resources.getColor(R.color.line8)
    if(stationLine.equals("9"))
        color = context.resources.getColor(R.color.line9)
    if(stationLine.equals("line_u")) // 의정보 경전철
        color = context.resources.getColor(R.color.line_u)
    if(stationLine.equals("in")) // incheon 1
        color = context.resources.getColor(R.color.line_i1)
    if(stationLine.equals("incheon")) // incheon 2
        color = context.resources.getColor(R.color.line_i2)
    if(stationLine.equals("chun"))// 경춘선
        color = context.resources.getColor(R.color.line_g)
    if(stationLine.equals("kyung")) // 경의 중앙선
        color = context.resources.getColor(R.color.line_k)
    if(stationLine.equals("kong"))//공황 철도
        color = context.resources.getColor(R.color.line_a)
    if(stationLine.equals("suin")) // 수인선
        color = context.resources.getColor(R.color.line_su)
    if(stationLine.equals("bun")) // 분당선
        color = context.resources.getColor(R.color.line_b)
    if(stationLine.equals("sinbun"))    // 신분당선
        color = context.resources.getColor(R.color.line_s)
    if(stationLine.equals("kyungkang")) // 경강선
        color = context.resources.getColor(R.color.line_kk)
    if(stationLine.equals("ever")) // 에버라인
        color = context.resources.getColor(R.color.line_e)
    if(stationLine.equals("sinsul")) // 우이신설
        color = context.resources.getColor(R.color.line_w)
    if(stationLine.equals("suehe"))     // 서해선
        color = context.resources.getColor(R.color.line_sh)

    return color
}