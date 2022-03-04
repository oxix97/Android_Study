package com.example.mvvm.test

import android.app.Application
import androidx.lifecycle.LiveData

class TestRepository(application: Application) {
    private val testDatabase = TestDatabase.getInstance(application)!!
    private val testDataDao: TestDataDao = testDatabase.testDataDao()
    private val testData: LiveData<List<TestData>> = testDataDao.getAll()

    fun getAll(): LiveData<List<TestData>> {
        return testData
    }

    fun insert(data: TestData) {
        try {
            val thread = Thread(Runnable {
                testDataDao.insert(data)
            })
            thread.start()
        } catch (e: Exception) {
        }
    }

    fun delete(data: TestData) {
        try {
            val thread = Thread(Runnable {
                testDataDao.delete(data)
            })
            thread.start()
        } catch (e: Exception) {
        }
    }

}

/*
ViewModel에서 DB에 접근을 요청할 때 수행할 함수를 만들어둔다.
주의할 점은 Room DB를 메인 스레드에서 접근하려 하면 크래쉬가 발생한다.
 에러 메세지는 다음과 같다.
Cannot access database on the main thread since it may potentially lock the UI for a long period of time.
(메인 UI 화면이 오랫동안 멈춰있을 수도 있기 때문에, 메인 쓰레드에서는 데이터베이스에 접근할 수 없습니다.)
따라서 별도의 스레드에서 Room의 데이터에 접근해야 한다.
 */