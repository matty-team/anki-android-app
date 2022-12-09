package matty.team.anki.ui.vm

sealed class ViewModelState<T>(val nullData: T? = null, val error: String? = null) {
    class NotInit<T> : ViewModelState<T>()
    class Ready<T>(val data: T) : ViewModelState<T>(data)
    class Error<T>(error: String) : ViewModelState<T>(error = error)
}
