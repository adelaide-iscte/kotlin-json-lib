package jsonlib.visitor

interface Visitable {
    fun accept(visitor: JsonVisitor)
}