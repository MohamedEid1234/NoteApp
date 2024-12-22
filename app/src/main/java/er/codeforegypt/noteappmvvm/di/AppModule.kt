package er.codeforegypt.noteappmvvm.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import er.codeforegypt.noteappmvvm.feature_note.data.data_source.NoteDataBase
import er.codeforegypt.noteappmvvm.feature_note.data.repository.NoteRepositoryImpl
import er.codeforegypt.noteappmvvm.feature_note.domain.repository.NoteRepository
import er.codeforegypt.noteappmvvm.feature_note.domain.use_case.AddNoteUseCase
import er.codeforegypt.noteappmvvm.feature_note.domain.use_case.DeleteNoteUseCase
import er.codeforegypt.noteappmvvm.feature_note.domain.use_case.GetNoteUseCase
import er.codeforegypt.noteappmvvm.feature_note.domain.use_case.GetNotesUseCase
import er.codeforegypt.noteappmvvm.feature_note.domain.use_case.NoteUseCases
import javax.inject.Singleton

//13
//yhis appmodule is for dependency injection an testing as it provide
// fake repository for testing
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app:Application):NoteDataBase{
        return Room.databaseBuilder(
            app,
            NoteDataBase::class.java,
            NoteDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db:NoteDataBase):NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases{
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNoteUseCase(repository),
            getNote = GetNoteUseCase(repository)

        )

    }
}