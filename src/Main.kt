import group.Group
import transaction.Transaction
import user.User
import java.util.Random
import java.util.UUID

fun main() {
        repeat(100) {
            drive()
        }

}

fun drive() {
//    val a = User("A", "A", 1)
//    val b = User("B", "B", 2)
//    val c = User("C", "C", 3)
//    val transaction1 = Transaction(a, b, 10.00);
//    val transaction2 = Transaction(a, c, 100.00);
//    val transaction3 = Transaction(b, c, 134.00);
//    val transaction4 = Transaction(b, a, 145.00);
//    val transaction5 = Transaction(c, b, 109.00);
//    val transaction6 = Transaction(c, b, 450.00);
//    val group = Group(
//        mutableMapOf( Pair(a,0.00), Pair(b,0.00), Pair(c,0.00)),
//        1,
//        mutableListOf(
//            transaction1, transaction2, transaction3, transaction4, transaction5, transaction6
//        )
//    )
    val userList = mutableMapOf<User, Double>();
    var i = 0;
    repeat(1000) {
        userList.put(User(UUID.randomUUID().toString(), UUID.randomUUID().toString(), i), 0.00)
        i = i+1
    }

    val transactionList = mutableListOf<Transaction>()
    repeat(5000) {
        val g = kotlin.random.Random.nextInt(100);
        val r = kotlin.random.Random.nextInt(100);
        transactionList.add(Transaction(
            userList.toList()[g].first,
            userList.toList()[r].first,
            kotlin.random.Random.nextDouble(1.00, 1000.00)))
    }

    var group = Group(userList, 1, transactionList)

    group.calculateCredit()
    var transactionListReturned = group.simplify()
    println(transactionListReturned.size)
}