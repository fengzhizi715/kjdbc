package cn.netdiscovery.kjdbc.extension

import java.sql.ResultSet

/**
 * Created by tony on 2019-01-28.
 */
typealias RSBlock<T> = (ResultSet) -> T

fun ResultSet.toList(): List<Any?> = (1..this.metaData.columnCount)
        .map {
            this.getObject(it)
        }

fun ResultSet.asSequence() = generateSequence {
    if (this.next()) this
    else null
}

inline fun <T> ResultSet.selectOne(block: RSBlock<T>) = if (this.next()) block(this) else null

fun ResultSet.selectOne() = this.selectOne(ResultSet::toList)

inline fun ResultSet.selectEach(block: (ResultSet) -> Unit) {
    while (this.next()) {
        block(this)
    }
}

inline fun <T> ResultSet.selectAll(block: RSBlock<T>): List<T> {
    val list = mutableListOf<T>()
    while (this.next()) {
        list += block(this)
    }
    return list
}

fun ResultSet.selectAll() = this.selectAll(ResultSet::toList)
