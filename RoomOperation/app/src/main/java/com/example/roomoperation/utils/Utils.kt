package com.example.roomoperation.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

const val TAG = "ROOM"
fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

open class Param
// 创建一个扩展函数，将data class转换为map
fun <T : Param> T.toStr(): String {

    // 获取data class的所有属性
    val properties = this::class.memberProperties

    // 创建一个空的map来存储属性和值
    val map = mutableMapOf<String, Any?>()

    // 遍历属性并将它们添加到map中
    for (prop in properties) {
        prop.isAccessible = true
        map[prop.name] = prop.call(this)
    }
    return map.toString()
}


inline fun <reified T> fromJson(json: String): T {
    return Gson().fromJson(json, object : TypeToken<T>() {}.type)
}
//val str = map.toString().replace("{","").replace("}", "").split(", ")
//Log.d(TAG, "toStr: $str")
//str.forEach()