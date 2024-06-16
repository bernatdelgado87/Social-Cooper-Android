package app.mistercooper.domain_shared_common.arch.model

abstract class Failure : Throwable()

sealed class GlobalFailure : Failure() {
    data class GlobalError(val throwable: Throwable? = null) : GlobalFailure()
}
