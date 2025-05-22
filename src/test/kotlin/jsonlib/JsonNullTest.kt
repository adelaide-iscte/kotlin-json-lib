package jsonlib

import jsonlib.jsonTypeObjects.JsonNull
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonNullTest {

    @Test
    fun testToJsonString() {
        assertEquals("null", JsonNull.toJsonString())
    }

}
