package cn.netdiscovery.kjdbc.extension

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

/**
 * Created by tony on 2019-01-28.
 */
inline fun <T> Connection.useStatement(block: (Statement) -> T) = this.createStatement().use(block)

inline fun <T> Connection.usePreparedStatement(sql: String, block: (PreparedStatement) -> T) = this.prepareStatement(sql).use(block)

fun Connection.update(sql: String) =
        this.useStatement {
            it.executeUpdate(sql)
        }

fun Connection.update(sql: String, vararg params: Any?) =
        this.usePreparedStatement(sql) {
            it.update(*params)
        }

inline fun <T> Connection.selectOne(sql: String, block: RSFunction<T>) =
        this.useStatement {
            it.selectOne(sql, block)
        }

inline fun <T> Connection.selectOne(sql: String, vararg params: Any?, block: RSFunction<T>) =
        this.usePreparedStatement(sql) {
            it.selectOne(*params, block = block)
        }

fun Connection.selectOne(sql: String) =
        this.useStatement {
            it.selectOne(sql)
        }

fun Connection.selectOne(sql: String, vararg params: Any?) =
        this.usePreparedStatement(sql) {
            it.selectOne(*params)
        }

inline fun Connection.selectEach(sql: String, block: (ResultSet) -> Unit) =
        this.useStatement {
            it.selectEach(sql, block)
        }

inline fun Connection.selectEach(sql: String, vararg params: Any?, block: (ResultSet) -> Unit) =
        this.usePreparedStatement(sql) {
            it.selectEach(*params, block = block)
        }

inline fun <T> Connection.selectAll(sql: String, block: RSFunction<T>) =
        this.useStatement {
            it.selectAll(sql, block)
        }

inline fun <T> Connection.selectAll(sql: String, vararg params: Any?, block: RSFunction<T>) =
        this.usePreparedStatement(sql) {
            it.selectAll(*params, block = block)
        }

fun Connection.selectAll(sql: String) =
        this.useStatement {
            it.selectAll(sql)
        }

fun Connection.selectAll(sql: String, vararg params: Any?) =
        this.usePreparedStatement(sql) {
            it.selectAll(*params)
        }