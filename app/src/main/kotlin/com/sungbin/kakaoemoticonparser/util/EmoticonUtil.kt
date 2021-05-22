package com.sungbin.kakaoemoticonparser.util

import android.content.Context
import android.os.AsyncTask
import com.sungbin.kakaoemoticonparser.model.EmoticonData
import com.sungbin.sungbintool.util.StorageUtil
import com.sungbin.sungbintool.util.Util
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

object EmoticonUtil {

    fun getEmotionCode(html: String) =
        html.split("'\${IMAGE_URL}': \"")[1].split("dw/")[1].split(".")[0].toLong()

    fun getEmoticonList(id: Long): ArrayList<String>? {
        val list = ArrayList<String>()
        val suffix = if (id in 2211001..2230013) ".emot" else ".thum"
        return try {
            for (i in 1..100) {
                val address = "https://item.kakaocdn.net/dw/$id${suffix}_${getDigitNum(i, 3)}.png"
                val html = Util.getHtml(address) ?: break
                if (html.contains("error") || html.contains("unsupported")) break
                else {
                    list.add(address)
                }
            }
            list
        } catch (ignored: Exception) {
            null
        }
    }

    private fun getDigitNum(number1: Int, number2: Int) =
        ("0".repeat(number2 - number1.toString().length)) + number1.toString()

    fun download(context: Context, item: EmoticonData, url: String, index: Int) {
        val rootPath = "${StorageUtil.sdcard}/KakaoEmoticons/${item.title}"
        StorageUtil.createFolder("KakaoEmoticons/${item.title}", true)
        val path = "$rootPath/${item.originTitle}_$index.png"
        File(path).createNewFile()
        ImageDownloadTask(context, path, url).execute()
    }

    private class ImageDownloadTask constructor(
        private val context: Context,
        private val path: String,
        private val url: String
    ) :
        AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg params: Void?): Void? {
            try {
                val conn = URL(url).openConnection() as HttpURLConnection
                val len = conn.contentLength
                val tmpByte = ByteArray(len)
                val `is` = conn.inputStream
                val fos = FileOutputStream(path)

                while (true) {
                    val read = `is`.read(tmpByte)
                    if (read <= 0) {
                        break
                    }
                    fos.write(tmpByte, 0, read)
                }

                `is`.close()
                fos.close()
                conn.disconnect()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null
        }

        override fun onPostExecute(result: Void?) {
            MediaScanner.instance(context).mediaScanning(path)
            return
        }

    }
}