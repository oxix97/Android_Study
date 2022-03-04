package com.example.mvvm.test


import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class TestDatabase : RoomDatabase() {
    abstract fun testDataDao(): TestDataDao

    companion object {
        private var INSTANCE: TestDatabase? = null

        fun getInstance(context: Context): TestDatabase? {
            if (INSTANCE == null) {
                synchronized(TestDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TestDatabase::class.java, "contact"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}
/*
클래스 이름 위에 @Database 어노테이션을 이용해 entity를 정의하고 SQLite 버전을 지정한다.
또한 데이터베이스 인스턴스를 싱글톤으로 사용하기 위해, companion object 에 만들어주었다.

getInstance 함수는 여러 스레드가 접근하지 못하도록 synchronized로 설정한다.
여기서 실질적으로 Room.databaseBuilder 로 인스턴스를 생성하고,
fallbackToDestructiveMigration 을 통해 데이터베이스가 갱신될 때 기존의 테이블을 버리고 새로 사용하도록 설정했다.
 */
