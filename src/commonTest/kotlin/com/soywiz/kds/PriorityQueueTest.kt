package com.soywiz.kds

import kotlin.test.*

class PriorityQueueTest {
    @Test
    fun test() {
        val pq = PriorityQueue<Int>()
        pq.add(10)
        pq.add(15)
        pq.add(5)
        assertEquals(3, pq.size); assertEquals(5, pq.removeHead())
        assertEquals(2, pq.size); assertEquals(10, pq.removeHead())
        assertEquals(1, pq.size); assertEquals(15, pq.removeHead())
        assertEquals(0, pq.size)
    }

    @Test
    fun testInt() {
        val pq = IntPriorityQueue()
        pq.add(10)
        pq.add(15)
        pq.add(5)
        assertEquals(3, pq.size); assertEquals(5, pq.removeHead())
        assertEquals(2, pq.size); assertEquals(10, pq.removeHead())
        assertEquals(1, pq.size); assertEquals(15, pq.removeHead())
        assertEquals(0, pq.size)
    }

    @Test
    fun test2() {
        data class Demo(val value: Long)

        val pq = PriorityQueue<Demo>(Comparator { a, b -> a.value.compareTo(b.value) })

        val a = Demo(10)
        val b = Demo(15)
        val c = Demo(5)

        pq.add(a)
        pq.add(b)
        pq.add(c)
        assertEquals(3, pq.size); assertEquals(c, pq.removeHead())
        assertEquals(2, pq.size); assertEquals(a, pq.removeHead())
        assertEquals(1, pq.size); assertEquals(b, pq.removeHead())
        assertEquals(0, pq.size)
    }
}