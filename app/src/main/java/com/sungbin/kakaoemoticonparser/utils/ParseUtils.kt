package com.sungbin.kakaoemoticonparser.utils

import com.sungbin.kakaoemoticonparser.model.EmoticonData
import com.sungbin.kakaoemoticonparser.utils.extensions.parse
import org.json.JSONObject

object ParseUtils {

    const val MOBILE_USER_AGENT = "Mozilla/5.0 (Linux; Android 10; SM-G977N Build/QP1A.190711.020; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/84.0.4147.125 Mobile Safari/537.36;KAKAOTALK 2108970"

    fun getSearchedData(html: String): ArrayList<EmoticonData>? {
        val data = ArrayList<EmoticonData>()
        return try {
            val json = html.replace("&quot;", "\"")
            val jsonContent = json.parse("SearchPage", "SearchPage-0", 1, false)
                .split("\" data-react-props=\"")[1]
                .split("\" data-react-cache-id=\"")[0]

            val jsonObject = JSONObject(jsonContent).getJSONArray("items")
            for (i in 0 until jsonObject.length()) {
                val content = jsonObject.getJSONObject(i)
                val artist = content.getString("artist")
                val haveMotion: Boolean
                val haveSound: Boolean
                content.getJSONObject("icon").run {
                    haveMotion = getBoolean("motion")
                    haveSound = getBoolean("sound")
                }
                val isBig = content.getBoolean("isBigEmo")
                val title = content.getString("title")
                val originTitle = content.getString("titleUrl")
                val url = "https://e.kakao.com/t/$originTitle"
                val thumbnailUrl = content.getString("titleDetailUrl")
                val item = EmoticonData(
                    title,
                    artist,
                    url,
                    thumbnailUrl,
                    isBig,
                    haveMotion,
                    haveSound,
                    originTitle
                )
                data.add(item)
            }
            if (data.isEmpty()) null
            else data
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}