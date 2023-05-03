package by.vfedorenko.mvistarter.ui.bookdetails

import by.vfedorenko.starter.BaseViewModel
import by.vfedorenko.starter.MviStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    store: MviStore<BookDetailsState>
) : BaseViewModel<BookDetailsState>(store)
