package com.example.internet_collection

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    lateinit var users: Users
    lateinit var requestQueue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        bu kod internet bor yooki yoqligini tekshiradi
        users = Users(this)
        val tv = findViewById<TextView>(R.id.tv_1)
        tv.setOnClickListener {
            if (users.isNetworkConnected()){
                tv.text= "internet bor"
            }else{
                tv.text = "internet yoq"
            }
        }*/

        //bu rasmniki chiqadi
        /*    requestQueue = Volley.newRequestQueue(this)
            getImmage()*/

        users = Users(this)
        requestQueue = Volley.newRequestQueue(this)
        getArrayGson()

    }

    fun getArrayGson() {
        val link = "https://api.github.com/users"
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, link, null,
            object : Response.Listener<JSONArray> {
                override fun onResponse(response: JSONArray?) {

                    findViewById<TextView>(R.id.tv_1)
                        .text = response.toString()
                    Log.d(TAG, "onResponse : ${response.toString()}")
                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                }
            })
        requestQueue.add(jsonArrayRequest)
    }
//bu rasmni olib keladi
    /*  fun getImmage() {
          val imageLink = "https://i.imgur.com/Nwk25LA.jpg"
          val imageRequest = ImageRequest(
              imageLink,
              object : Response.Listener<Bitmap> {
                  override fun onResponse(response: Bitmap?) {
                      findViewById<ImageView>(R.id.tv_1)
                          .setImageBitmap(response)
                  }
              }, 0, 0, ImageView.ScaleType.CENTER_CROP,
              Bitmap.Config.ARGB_8888,
              object : Response.ErrorListener {
                  override fun onErrorResponse(error: VolleyError?) {
                      Toast.makeText(this@MainActivity, "xato", Toast.LENGTH_SHORT).show()
                  }

              }
          )
          requestQueue.add(imageRequest)
      }*/
}