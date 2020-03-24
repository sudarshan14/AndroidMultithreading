package sid.angel.androidmultithreading.visibility

var sCount = 0
val consumer = Thread {
    var localValue = -1
    while (true) {
//        println("consumer loop continues?? $sCount")
// the value of sCount at some point is not coming from memory and is cached in the loop. so the cosnumer loop values
//        are not printed.
        if (localValue != sCount) {
            println("Consumer: Count change detected : count $sCount")
            localValue = sCount
        }
        if (sCount >= 5) {
            break
        }

    }
    // Log.d(TAG, "main: thread sleeping")
    println("Consumer terminated")
//    Thread.sleep(10000)
}


val producer = Runnable {

    run {
        var localValue = sCount
        while (sCount < 5) {
            localValue++
            println("Producer incrementing count to $localValue  ${Thread.currentThread().name}")
            sCount = localValue
            Thread.sleep(1000)
        }


        println("Producer terminated ${Thread.currentThread().name}")
    }


}
val thread2 = Thread {
    println("hello2" + Thread.currentThread().id)
    for (i in 1..10) {
        println(i)
        Thread.sleep(1000)
        if (Thread.interrupted()) {
            return@Thread
        }
    }
}
private val TAG = "MyThread"
fun main() {

//    Consumer().start()
//    try {
//        Thread.sleep(100)
//    } catch (e: InterruptedException) {
//        return
//    }
//    Producer().start()

    consumer.start()
    Thread.sleep(1000)
    producer.run()
//    thread2.run()
}

internal class Consumer : Thread() {
    override fun run() {
        var localValue = -1
        while (true) {
            if (localValue != sCount) {
                System.out.println("Consumer: detected count change $sCount")
                localValue = sCount
            }
            if (sCount >= 5) {
                break
            }
        }
        println("Consumer: terminating")
    }
}


internal class Producer : Thread() {
    override fun run() {
        while (sCount < 5) {
            var localValue = sCount
            localValue++
            println("Producer: incrementing count to $localValue")
            sCount = localValue
            try {
                sleep(1000)
            } catch (e: InterruptedException) {
                return
            }
        }
        println("Producer: terminating")
    }
}