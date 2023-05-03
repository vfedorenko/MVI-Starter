package by.vfedorenko.mvistarter.ui.home.favorites

import by.vfedorenko.starter.BaseViewModel
import by.vfedorenko.starter.MviStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    store: MviStore<FavoritesState>
) : BaseViewModel<FavoritesState>(store)
