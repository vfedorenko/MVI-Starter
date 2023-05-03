package by.vfedorenko.mvistarter.data.entities

data class Book(
    val id: Int,
    val title: String,
    val body: String,
    val isFavorite: Boolean
) {
    companion object {
        val empty = Book(
            id = 0,
            title = "",
            body = "",
            isFavorite = false
        )

        fun stub(id: Int = 0) = Book(
            id = id,
            title = "List Item title",
            body = "List Item body",
            isFavorite = false
        )
    }
}
