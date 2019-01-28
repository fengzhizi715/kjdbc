package cn.netdiscovery.kjdbc.extension

import java.sql.ResultSet

/**
 * Created by tony on 2019-01-28.
 */
fun ResultSet.toAnyList(): List<Any?> = (1..this.metaData.columnCount)
        .map {
            this.getObject(it)
        }

fun ResultSet.asSequence() = generateSequence {
    if (this.next()) this
    else null
}

inline fun <T> ResultSet.selectFirst(block: (ResultSet) -> T) = if (this.next()) block(this) else null

fun ResultSet.selectFirst() = this.selectFirst(ResultSet::toAnyList)

inline fun ResultSet.selectEach(block: (ResultSet) -> Unit) {
    while (this.next()) {
        block(this)
    }
}

inline fun <T> ResultSet.selectAll(block: (ResultSet) -> T): List<T> {
    val list = mutableListOf<T>()
    while (this.next()) {
        list += block(this)
    }
    return list
}

fun ResultSet.selectAll() = this.selectAll(ResultSet::toAnyList)
