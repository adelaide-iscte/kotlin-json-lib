package jsonlib.visitor

import jsonlib.jsonTypeObjects.JsonArray
import jsonlib.jsonTypeObjects.JsonObject
import jsonlib.jsonTypeObjects.JsonBoolean
import jsonlib.jsonTypeObjects.JsonNull
import jsonlib.jsonTypeObjects.JsonNumber
import jsonlib.jsonTypeObjects.JsonString

interface JsonVisitor {
    fun visit(jsonArray: JsonArray)
    fun visit(jsonBoolean: JsonBoolean)
    fun visit(jsonObject: JsonObject)
    fun visit(jsonNull: JsonNull)
    fun visit(jsonNumber: JsonNumber)
    fun visit(jsonString: JsonString)

}