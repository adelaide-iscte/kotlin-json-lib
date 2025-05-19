package jsonlib

import jsonlib.visitor.JsonVisitorImpl
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class JsonVisitorImplTest {

    @Test
    fun testVisImpl(){
        val visitor = JsonVisitorImpl()
        val visitor1 = JsonVisitorImpl()
        assertEquals(visitor, visitor1)
    }
}