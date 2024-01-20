interface Spec<T> {

    fun appliesTo(t: T): Boolean
}

abstract class BooleanSpec<T> : Spec<T> {

    fun or(s: Spec<T>): BooleanSpec<T> = OrSpecification(this, s)
    fun and(s: Spec<T>): BooleanSpec<T> = AndSpecification(this, s)
    fun not(): BooleanSpec<T> = NotSpecification(this)
}

class OrSpecification<T>(
        private val s1: Spec<T>,
        private val s2: Spec<T>
) : BooleanSpec<T>() {

    override fun appliesTo(t: T): Boolean =
            s1.appliesTo(t) || s2.appliesTo(t)
}

class AndSpecification<T>(
        private val s1: Spec<T>,
        private val s2: Spec<T>
) : BooleanSpec<T>() {

    override fun appliesTo(t: T): Boolean =
            s1.appliesTo(t) && s2.appliesTo(t)
}

class NotSpecification<T>(
        private val s1: Spec<T>
) : BooleanSpec<T>() {

    override fun appliesTo(t: T): Boolean = !s1.appliesTo(t)
}

data class Person(
        val age: Int,
        val name: String,
        val height: Int
)

class IsUnder18Spec : BooleanSpec<Person>() {
    override fun appliesTo(t: Person): Boolean =
            t.age < 18
}

class IsTallSpec : BooleanSpec<Person>() {
    override fun appliesTo(t: Person): Boolean =
            t.height > 170
}

class IsLongName : BooleanSpec<Person>() {
    override fun appliesTo(t: Person): Boolean =
            t.name.length > 3
}
