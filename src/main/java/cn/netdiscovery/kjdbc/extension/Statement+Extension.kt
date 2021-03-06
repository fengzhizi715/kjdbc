package cn.netdiscovery.kjdbc.extension

import java.sql.ResultSet
import java.sql.Statement

/**
 * Created by tony on 2019-01-28.
 */
fun Statement.select(sql: String): ResultSet = this.executeQuery(sql)

inline fun <T> Statement.selectOne(sql: String, block: RSFunction<T>) =
        this.select(sql).use {
            it.selectOne(block)
        }

fun Statement.selectOne(sql: String) =
        this.select(sql).use {
            it.selectOne()
        }


inline fun Statement.selectEach(sql: String, block: (ResultSet) -> Unit) =
        this.select(sql).use {
            it.selectEach(block)
        }

inline fun <T> Statement.selectAll(sql: String, block: RSFunction<T>): List<T> =
        this.select(sql).use {
            it.selectAll(block)
        }

fun Statement.selectAll(sql: String): List<List<Any?>> =
        this.select(sql).use {
            it.selectAll()
        }