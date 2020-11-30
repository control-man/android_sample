package com.jinss.android.androidsample.retrofit2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jinss.android.androidsample.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * retrofit2 download
 * https://riptutorial.com/ko/retrofit2 - 사용법
 * https://github.com/square/retrofit - 공식 github
 * 참고코드
 * https://medium.com/@joycehong0524/android-studio-retrofit2-%EA%B8%B0%EB%B3%B8-%EC%82%AC%EC%9A%A9%EB%B2%95-retrofit-%EC%9D%98%EB%AC%B8%EC%A0%90-%ED%92%80%EC%96%B4%ED%97%A4%EC%B9%98%EA%B8%B0-%EC%8A%A4%EC%95%95-f150db436add
 */

class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        val apiInterface = ApiClient.client!!.create(APIInterface::class.java)
        /**
         * Create new user
         */
        val user = User("morpheus", "leader")
        val call1: Call<User?>? = apiInterface.createUser(user)

        call1?.enqueue(object : Callback<User?> {
            override fun onResponse(call: Call<User?>?, response: Response<User?>) {
                val user1: User? = response.body()
                Toast.makeText(
                    applicationContext,
                    user1?.name + " " + user1?.job + " " + user1?.id + " " + user1?.createdAt,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<User?>, t: Throwable?) {
                call.cancel()
            }
        })
    }
}
