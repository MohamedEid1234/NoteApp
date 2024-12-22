package er.codeforegypt.noteappmvvm.feature_note.domain.repository

import er.codeforegypt.noteappmvvm.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

//this come after 1 Note 2 NoteDao 3 NoteDataBase 4 NoteRepository
//the reason i make it interface is that it is quick in testing as i dont need
// to use my real data base in testing but only fake one and use cases take it as
// it dont care from where data come from

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>
    suspend fun getNoteById(id: Int): Note?
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)
}