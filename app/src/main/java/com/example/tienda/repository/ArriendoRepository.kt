package com.example.tienda.repository

import com.example.tienda.model.dao.ArriendoDao
import com.example.tienda.model.Arriendo
import kotlinx.coroutines.flow.Flow

class ArriendoRepository(private val arriendoDao: ArriendoDao) {
    fun getAll(): Flow<List<Arriendo>> = arriendoDao.getAll()

    suspend fun getById(id: Int): Arriendo? = arriendoDao.getById(id)

    suspend fun insert(arriendo: Arriendo): Long = arriendoDao.insert(arriendo)

    suspend fun update(arriendo: Arriendo) = arriendoDao.update(arriendo)

    suspend fun delete(arriendo: Arriendo) = arriendoDao.delete(arriendo)
}
