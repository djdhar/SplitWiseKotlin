package user

class User (
    val firstName: String,
    val lastName: String,
    var id: Int
) {
    override fun toString(): String {
        return "User(firstName='$firstName', lastName='$lastName', id=$id)"
    }
}

