package cloud.hendra.ranpur.utils.state

sealed class GuardState {
    object Authenticated : GuardState()
    object Unauthenticated : GuardState()
    object Loading : GuardState()
    object Idle: GuardState()
    data class Error(val message: String?) : GuardState()
}