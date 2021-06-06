package ru.nightgoat

import addIf
import distinctAndFilter
import indexOfOrNull
import orEmptyMutable
import orEmptyMutableMap
import org.junit.Assert
import org.junit.Test
import sumByDoubleSafe

class CollectionsExtTest {

    @Test
    fun orEmptyMutable_list_test_1() {
        val list = listOf(1, 2, 3)
        Assert.assertEquals(mutableListOf(1, 2, 3), list.orEmptyMutable())
    }

    @Test
    fun orEmptyMutable_list_test_2() {
        val list: List<Int>? = null
        Assert.assertEquals(mutableListOf<Int>(), list.orEmptyMutable())
    }

    @Test
    fun orEmptyMutable_set_test_1() {
        val set = setOf(1, 2, 3)
        Assert.assertEquals(mutableSetOf(1, 2, 3), set.orEmptyMutable())
    }

    @Test
    fun orEmptyMutable_set_test_2() {
        val set: Set<Int>? = null
        Assert.assertEquals(mutableSetOf<Int>(), set.orEmptyMutable())
    }

    @Test
    fun orEmptyMutable_map_test_1() {
        val map = mapOf(
            1 to 1,
            2 to 2
        )
        Assert.assertEquals(mutableMapOf(1 to 1, 2 to 2), map.orEmptyMutable())
    }

    @Test
    fun orEmptyMutable_map_test_2() {
        val map: Map<Int, Int>? = null
        Assert.assertEquals(mutableMapOf<Int, Int>(), map.orEmptyMutable())
    }

    @Test
    fun orEmptyMutableMap_test_1() {
        val list = listOf<Pair<Int, Int>>(Pair(1, 1), Pair(2, 2))
        Assert.assertEquals(mutableMapOf(1 to 1, 2 to 2), list.orEmptyMutableMap())
    }

    @Test
    fun orEmptyMutableMap_test_2() {
        val list: List<Pair<Int, Int>>? = null
        Assert.assertEquals(mutableMapOf<Int, Int>(), list.orEmptyMutableMap())
    }

    @Test
    fun indexOfOrNull_test_1() {
        val list = listOf(1, 2, 3)
        Assert.assertEquals(0, list.indexOfOrNull(1))
    }

    @Test
    fun indexOfOrNull_test_2() {
        val list = listOf(1, 2, 3)
        Assert.assertNull(list.indexOfOrNull(4))
    }

    @Test
    fun addIf_test_1() {
        val list = mutableListOf(1, 2, 3)
        list.addIf(list.isNotEmpty()) { 4 }
        Assert.assertEquals(list.size, 4)
    }

    @Test
    fun addIf_test_2() {
        val list = mutableListOf(1, 2, 3)
        list.addIf(list.size > 3) { 4 }
        Assert.assertEquals(list.size, 3)
    }

    @Test
    fun addIf_test_3() {
        val list = mutableListOf(1, 2, 3)
        list.addIf(4) {
            it.isNotEmpty()
        }
        Assert.assertEquals(list.size, 4)
    }

    @Test
    fun addIf_test_4() {
        val list = mutableListOf(1, 2, 3)
        list.addIf(4) {
            it.size > 3
        }
        Assert.assertEquals(list.size, 3)
    }

    @Test
    fun distinctAndFilter_test_1() {
        val list = mutableListOf(Pair(1, 2), Pair(1, 3), Pair(2, 4))
        val filtered = list.distinctAndFilter(
            distinctBy = {
                it.first
            },
            filterBy = {
                it.second < 4
            }
        )
        Assert.assertEquals(mutableListOf(Pair(1, 2)), filtered)
    }

    @Test
    fun sumByDoubleSafe_test_1() {
        val list = listOf(0.1, 0.2)
        Assert.assertEquals(0.3, list.sumByDoubleSafe { it }, 0.0)
    }
}