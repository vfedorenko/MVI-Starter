package by.vfedorenko.mvistarter.core

fun <T> filledList(item: T, size: Int): List<T> = (0 until size).map { item }
fun <T> filledListIndexed(size: Int, body: (Int) -> T): List<T> = (0 until size).map(body)
