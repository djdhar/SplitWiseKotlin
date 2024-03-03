package group

import transaction.Transaction
import user.User

class Group(
    val userList: MutableMap<User, Double>,
    var id: Int,
    var listOfTransaction: List<Transaction>
)
{
     fun calculateCredit() {
        for (transcation in listOfTransaction) {
            val giverAmount = userList.getOrDefault(transcation.giver, 0.00)
            val receiverAmount = userList.getOrDefault(transcation.receiver, 0.00)
            userList.put(transcation.giver, giverAmount - transcation.amount)
            userList.put(transcation.receiver, receiverAmount + transcation.amount)
        }
    }

    override fun toString(): String {
        return "Group(userList=$userList)"
    }

    fun simplify() : MutableList<Transaction> {
        val transactionList = mutableListOf<Transaction>()
        val listOfReceiver = mutableListOf<User>()
        val listOfGiver = mutableListOf<User>()
        for ((user, credit) in userList) {
            if(credit > 0) {
                listOfReceiver.add(user)
            }
            if(credit < 0) {
                listOfGiver.add(user)
            }
        }

        while (listOfGiver.isNotEmpty()) {
            val myGiver = listOfGiver.last()
            if(listOfReceiver.isEmpty()) {
                println(listOfGiver)
                for(giver in listOfGiver) {
                    println(userList.get(giver))
                }
            }
            val myReceiver = listOfReceiver.last()
            val amount = -userList.getOrDefault(myGiver, 0.00)
            transactionList.add(
                Transaction(myGiver, myReceiver, amount)
            )
            userList.put(myGiver, 0.00)
            userList.put(myReceiver, userList.getOrDefault(myReceiver, 0.00) - amount)
            if(isEuqalToZero(userList.getOrDefault(myReceiver, 0.00))) {
                listOfReceiver.removeLast()
                listOfGiver.removeLast()
            } else if (userList.getOrDefault(myReceiver, 0.00) < 0.00) {
                listOfReceiver.removeLast()
                listOfGiver.removeLast()
                listOfGiver.add(myReceiver)
            } else {
                listOfGiver.removeLast()
            }
        }
        return transactionList;
    }

    private fun isEuqalToZero(amountDiff: Double): Boolean {
        if(amountDiff < 0.0001) return true;
        return false;
    }
}

