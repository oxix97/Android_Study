package com.example.mvvm.test2.VModel

import androidx.lifecycle.LiveData
import com.example.mvvm.test2.model.AppDatabase
import com.example.mvvm.test2.model.Entity

//이것도 정확히 어떤 역할 하는지 모름 알아봐야함
class Repository(mDatabase: AppDatabase) {
    private val dao = mDatabase.dao()
    val allUsers: LiveData<List<Entity>> = dao.getAll()

    companion object {
        private var sInstance: Repository? = null
        fun getInstance(database: AppDatabase): Repository {
            return sInstance
                ?: synchronized(this) {
                    val instance = Repository(database)
                    sInstance = instance
                    instance
                }
        }
    }

    suspend fun insert(entity: Entity) {
        dao.insert(entity)
    }

    suspend fun delete(entity: Entity) {
        dao.delete(entity)
    }

}