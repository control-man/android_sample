package com.jinss.android.androidsample.retrofit2

import retrofit2.Call
import retrofit2.http.*


internal interface APIInterface {
    @POST("api/users")
    fun createUser(@Body user: User?): Call<User?>?

    @GET("api/users?")
    fun doGetUserList(@Query("page") page: String?): Call<UserList?>?

    @FormUrlEncoded
    @POST("api/users?")
    fun doCreateUserWithField(
        @Field("name") name: String?,
        @Field("job") job: String?
    ): Call<UserList?>?
}
