package cn.netdiscovery.kjdbc.extension

import java.sql.ResultSet
import javax.sql.DataSource

/**
 * Created by tony on 2019-01-28.
 */
fun DataSource.update(sql: String, vararg params: Any?) =
        this.connection.use {
            it.update(sql, *params)
        }

inline fun <T> DataSource.selectOne(sql: String, vararg params: Any?, block: RSFunction<T>) =
        this.connection.use {
            it.selectOne(sql, *params, block = block)
        }

fun DataSource.selectOne(sql: String, vararg params: Any?) =
        this.connection.use {
            it.selectOne(sql, *params)
        }

inline fun DataSource.selectEach(sql: String, vararg params: Any?, block: (ResultSet) -> Unit) =
        this.connection.use {
            it.selectEach(sql, *params, block = block)
        }

inline fun <T> DataSource.selectAll(sql: String, vararg params: Any?, block: RSFunction<T>) =
        this.connection.use {
            it.selectAll(sql, *params, block = block)
        }

fun DataSource.selectAll(sql: String, vararg params: Any?) =
        this.connection.use {
            it.selectAll(sql, *params)
        }