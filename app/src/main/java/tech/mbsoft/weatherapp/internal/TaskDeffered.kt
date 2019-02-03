package tech.mbsoft.weatherapp.internal

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

/*******
Created by Mostasim on 09-Jan-19
Md Mostasim Billah
mostasim.10@gmail.com
mbSoft
 *******/

fun <T> Task<T>.asDeffered() : Deferred<T> {
    val deferred = CompletableDeferred<T>()

    this.addOnSuccessListener {result->
        deferred.complete(result)
    }

    this.addOnFailureListener {exception ->
        deferred.completeExceptionally(exception)
    }
    return deferred
}