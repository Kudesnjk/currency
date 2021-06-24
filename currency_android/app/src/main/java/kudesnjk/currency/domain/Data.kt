package kudesnjk.currency.domain

sealed class Data<T> {
    data class Content<T>(val content: T) : Data<T>()
    data class Error<T>(val throwable: Throwable, val content: T?) : Data<T>()
    data class Loading<T>(val content: T?) : Data<T>()

    companion object {
        inline fun <reified T> content(content: T): Data<T> = Content(content)
        inline fun <reified T> error(throwable: Throwable, content: T? = null): Data<T> = Error(throwable, content)
        inline fun <reified T> loading(content: T? = null): Data<T> = Loading(content)
    }
}