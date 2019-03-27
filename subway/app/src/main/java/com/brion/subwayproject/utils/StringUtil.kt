package com.brion.subwayproject.utils

import java.text.NumberFormat
import java.util.*

fun krCurrency(money:Int) = "${NumberFormat.getCurrencyInstance(Locale.KOREA).format(money).substring(1)} 원"