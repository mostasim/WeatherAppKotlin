package tech.mbsoft.weatherapp.internal

import kotlinx.coroutines.*

/*******
Created by Mostasim on 31-Dec-18
Md Mostasim Billah
mostasim.10@gmail.com
mbSoft
 *******/

fun <T> lazyDeffered(block : suspend CoroutineScope.() -> T) : Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async (start = CoroutineStart.LAZY){
            block.invoke(this)
        }
    }
}