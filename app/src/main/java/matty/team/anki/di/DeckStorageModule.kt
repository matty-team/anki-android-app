package matty.team.anki.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import matty.team.anki.data.DeckStorage
import matty.team.anki.data.InMemoryDeckStorage

@Module
@InstallIn(SingletonComponent::class)
abstract class DeckStorageModule {
    @Binds
    abstract fun bindDeckStorage(deckStorageImpl: InMemoryDeckStorage): DeckStorage
}
