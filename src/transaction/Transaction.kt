package transaction

import user.User

class Transaction (
    val giver: User,
    val receiver: User,
    val amount: Double
) {
    override fun toString(): String {
        return "Transaction(giver=$giver, receiver=$receiver, amount=$amount)"
    }
}