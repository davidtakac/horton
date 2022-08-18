package hr.dtakac.horton.domain.coroutines

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val io: CoroutineDispatcher
    val compute: CoroutineDispatcher
    val main: CoroutineDispatcher
}