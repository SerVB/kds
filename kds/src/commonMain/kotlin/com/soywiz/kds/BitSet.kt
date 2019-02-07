package com.soywiz.kds

import com.soywiz.kds.internal.*

/**
 * Fixed size [BitSet]. Similar to a [BooleanArray] but tightly packed to reduce memory usage.
 */
class BitSet(override val size: Int) : Collection<Boolean> {
    val data = IntArray(size divCeil 4)

    private fun part(index: Int) = index ushr 5
    private fun bit(index: Int) = index and 0x1f

    operator fun get(index: Int): Boolean = ((data[part(index)] ushr (bit(index))) and 1) != 0
    operator fun set(index: Int, value: Boolean) {
        val i = part(index)
        val b = bit(index)
        if (value) {
            data[i] = data[i] or (1 shl b)
        } else {
            data[i] = data[i] and (1 shl b).inv()
        }
    }

    fun set(index: Int): Unit = set(index, true)
    fun unset(index: Int): Unit = set(index, false)

    fun clear(): Unit = data.fill(0)

    override fun contains(element: Boolean): Boolean = (0 until size).any { this[it] == element }
    override fun containsAll(elements: Collection<Boolean>): Boolean = when {
        elements.contains(true) && !this.contains(true) -> false
        elements.contains(false) && !this.contains(false) -> false
        else -> true
    }

    override fun isEmpty(): Boolean = size == 0
    override fun iterator(): Iterator<Boolean> = (0 until size).map { this[it] }.iterator()
}