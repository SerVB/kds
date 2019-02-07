package com.soywiz.kds

/**
 * A [Set] structure representing a set of [Int] without boxing.
 *
 * Internally uses [IntMap]. Implements [MutableSet].
 */
class IntSet : MutableSet<Int> {
    private var data = IntMap<Unit>()
    override val size get() = data.size

    override fun containsAll(elements: Collection<Int>): Boolean = elements.all { it in data }
    override fun isEmpty(): Boolean = size == 0
    override fun iterator(): MutableIterator<Int> {
        val iterator = data.keys.iterator()
        return object : MutableIterator<Int> {
            override fun hasNext(): Boolean = iterator.hasNext()
            override fun next(): Int = iterator.next()
            override fun remove() = TODO()
        }
    }

    override fun clear() = run { data.clear() }
    override fun add(element: Int): Boolean = data.set(element, Unit) == null
    override fun addAll(elements: Collection<Int>): Boolean = elements.any { add(it) }
    override fun removeAll(elements: Collection<Int>): Boolean = elements.any { remove(it) }
    override fun retainAll(elements: Collection<Int>): Boolean {
        val oldSize = this.size
        val other = IntMap<Unit>()
        for (it in elements) if (it in this) other[it] = Unit
        this.data = other
        return oldSize == this.size
    }

    fun addAll(vararg elements: Int) = run { for (item in elements) add(item) }
    fun addAll(elements: Iterable<Int>) = run { for (item in elements) add(item) }

    override operator fun contains(element: Int) = element in data
    override fun remove(element: Int) = data.remove(element)

    operator fun plusAssign(value: Int) = run { add(value); Unit }
    operator fun minusAssign(value: Int) = run { remove(value); Unit }

    override fun toString(): String = "[${data.keys.joinToString(", ")}]"
}

fun intSetOf(vararg values: Int) = IntSet().apply { for (value in values) add(value) }