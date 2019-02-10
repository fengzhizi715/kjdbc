package cn.netdiscovery.kjdbc.extension

import java.sql.PreparedStatement
import java.sql.ResultSet

/**
 * Created by tony on 2019-01-28.
 */
operator fun <T> PreparedStatement.set(index: Int, data: T): Unit = this.setObject(index, data)

fun PreparedStatement.setObjects(vararg params: Any?) {
    params.forEachIndexed { index, v ->
        this[index+1]=v
    }
}

fun PreparedStatement.update(vararg params: Any?): Int {
    this.setObjects(*params)
    return this.executeUpdate()
}

fun PreparedStatement.select(vararg params: Any?): ResultSet {
    this.setObjects(*params)
    return this.executeQuery()
}

fun PreparedStatement.addBatchItem(vararg params: Any?) {
    this.setObjects(*params)
    this.addBatch()
}

inline fun <T> PreparedStatement.selectOne(vararg params: Any?, block: (ResultSet) -> T) =
        this.select(*params).use {
            it.selectOne(block)
        }

fun PreparedStatement.selectOne(vararg params: Any?) =
        this.select(*params).use {
            it.selectOne()
        }

inline fun PreparedStatement.selectEach(vararg params: Any?, block: (ResultSet) -> Unit) =
        this.select(*params).use {
            it.selectEach(block)
        }

inline fun <T> PreparedStatement.selectAll(vararg params: Any?, block: (ResultSet) -> T): List<T> =
        this.select(*params).use {
            it.selectAll(block)
        }

fun PreparedStatement.selectAll(vararg params: Any?): List<List<Any?>> =
        this.select(*params).use {
            it.selectAll()
        }