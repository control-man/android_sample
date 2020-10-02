package com.jinss.android.androidsample.livedata.eventsequence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import com.jinss.android.androidsample.R
import kotlinx.android.synthetic.main.activity_event_sequence.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EventSequenceActivity : AppCompatActivity() {
//    private val onNowChannel: LiveData<String> by lazy {
//        _onNowChannelUpdated.switchMap {updated ->
//            myLatestUpdatedData.distinctUntilChanged().map {myData ->
//                myData
//            }
//        }
//    }
    private val onNowChannel: LiveData<String> by lazy {
        myLatestUpdatedData.switchMap { myData ->
            Log.d("jh2174", "data is refreshed!")
            _onNowChannelUpdated.map { updated ->
                Log.d("jh2174", "update on now channel!")
                myData
            }
        }
    }
    private var _onNowChannelUpdated: MutableLiveData<Boolean> = MutableLiveData(false)
    private var myLatestUpdatedData: MutableLiveData<String> = MutableLiveData("Init Data")

    private var testValue:String = "123"
    private var testValueLiveData: MutableLiveData<String> = MutableLiveData("First String")


    private var testValue2 = LiveChannelData(arrayListOf<Channel>())
    private var testValueLiveData2: MutableLiveData<LiveChannelData> = MutableLiveData(LiveChannelData(arrayListOf()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_sequence)

        change_channel.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                delay(3000)
                myLatestUpdatedData.value = "updated my data!!!"
            }
            _onNowChannelUpdated.value = true
        }

        change_channel2.setOnClickListener {
            _onNowChannelUpdated.value = true
        }

        onNowChannel.observe(this) {
            Log.d("jh2174", "$it")
            observered_data.text = it
        }

        testValueLiveData.observe(this) {
            test_value_textview.text = "$it ${it.hashCode()}"
        }
        GlobalScope.launch(Dispatchers.Main) {
            delay(3000)
            testValueLiveData.value = testValue // 123 //기본형의 liveData는 깊은복사가 이루어지는것으로 보임.
            delay(3000)
            testValue = "456"
            Log.d("jh2174", "testvalue = ${testValueLiveData.value}") //123
            delay(3000)
            testValueLiveData.value = "live data value changed!"
            Log.d("jh2174", "testvalue = ${testValue}") //456
        }

        GlobalScope.launch(Dispatchers.Main) {
            delay(3000)
            testValue2.channelList.add(Channel("channel1", 1))
            testValueLiveData2.value = testValue2 // 사용자 정의형 클래스 데이터는 얕은복사가 이루어지는것으로 보임.
            delay(3000)
            Log.d("jh2174", "testvalue222 1= ${testValueLiveData2.value}") // channel1 예상했던대로
            delay(3000)
            testValue2.channelList[0].channelName = "changed channel1"
            Log.d("jh2174", "testvalue222 2= ${testValueLiveData2.value}") // changed channel1 이나옴.
            delay(3000)
            testValueLiveData2.value!!.channelList[0].channelName = "changed channel2"
            Log.d("jh2174", "testvalue222 3= ${testValue2.channelList[0].channelName}") // changed channel2 이 나옴

            // 아래 둘의 해시코드는 일치한다. 즉 같은 객체를 물고있다.
            Log.d("jh2174", "1 ${testValue2.channelList.hashCode()} ${testValueLiveData2.value.hashCode()}")

            delay(1000)
            // LiveChannelData 인스턴스를 새로생성해서 value에 집어넣더라도, 멤버필드자체는 예전의 것을 그대로 쓰기에(testValue2 와 일치)
            // 사실상 testValue2와 testValueLiveData2.value는 같은 객체를 가르키게된다.
            testValueLiveData2.value = LiveChannelData(testValueLiveData2.value!!.channelList)
            Log.d("jh2174", "2 ${testValue2.channelList.hashCode()} ${testValueLiveData2.value.hashCode()}")
            Log.d("jh2174", "22 ${testValue2.channelList.hashCode()} ${testValueLiveData2.value!!.channelList.hashCode()}")

            delay(1000)
            // 새로운 객체를 생성해서 liveData value에 over write 하게되면 관계는 끊어지게됨 (해시코드가 다르게 나오는거 확인가능)
            val temp = LiveChannelData(arrayListOf())
            temp.channelList.add(Channel("new channel", 2))
            testValueLiveData2.value = temp
            Log.d("jh2174", "3 ${testValue2.channelList.hashCode()} ${testValueLiveData2.value.hashCode()}")
        }
    }
}

data class LiveChannelData(val channelList: ArrayList<Channel>)
data class Channel(var channelName: String, var channelNumber: Int)