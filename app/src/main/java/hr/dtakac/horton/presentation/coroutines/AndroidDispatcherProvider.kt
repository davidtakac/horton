package hr.dtakac.horton.presentation.coroutines

import hr.dtakac.horton.domain.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AndroidDispatcherProvider : DispatcherProvider {
    override val compute: CoroutineDispatcher
        get() = Dispatchers.Default
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
}