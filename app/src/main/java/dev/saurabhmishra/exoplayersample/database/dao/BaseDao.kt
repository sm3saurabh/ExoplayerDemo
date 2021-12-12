package dev.saurabhmishra.exoplayersample.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertViaReplace(entity: T)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(entities: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllViaReplace(entities: List<T>)

    @Update
    fun update(entity: T)

    @Delete
    fun delete(entity: T)
}