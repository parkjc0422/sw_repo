package com.brion.subwayproject.route.provider

import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException
import java.lang.reflect.Type
import javax.xml.datatype.DatatypeConstants.SECONDS
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


class RouteProvider {
    fun getRouteService () :RouteService{
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        var retrofit = Retrofit.Builder().baseUrl("http://www.seoulmetro.co.kr/kr/")
            .addConverterFactory(ToStringConverterFactory())
            .client(okHttpClient)
            .build()

        return retrofit.create(RouteService::class.java)
    }


    inner class ToStringConverterFactory : Converter.Factory() {
        private val MEDIA_TYPE = MediaType.parse("text/plain")


        override fun responseBodyConverter(
            type: Type, annotations: Array<Annotation>,
            retrofit: Retrofit
        ): Converter<ResponseBody, *>? {
            return if (String::class.java == type) {
                object : Converter<ResponseBody, String> {
                    @Throws(IOException::class)
                    override fun convert(value: ResponseBody): String? {
                        return value.string()
                    }
                }
            } else null
        }

        override fun requestBodyConverter(
            type: Type, parameterAnnotations: Array<Annotation>,
            methodAnnotations: Array<Annotation>, retrofit: Retrofit
        ): Converter<*, RequestBody>? {

            return if (String::class.java == type) {
                object : Converter<String, RequestBody>{
                    @Throws(IOException::class)
                    override fun convert(value: String): RequestBody?{
                        return RequestBody.create(MEDIA_TYPE, value)
                    }
                }
            } else null
        }
    }
}