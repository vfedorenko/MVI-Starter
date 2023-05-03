package by.vfedorenko.mvistarter.ui.home.books

import by.vfedorenko.starter.BaseViewModel
import by.vfedorenko.starter.MviStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    store: MviStore<BooksState>
) : BaseViewModel<BooksState>(store)
