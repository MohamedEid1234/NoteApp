package er.codeforegypt.noteappmvvm.feature_note.data.repository

import er.codeforegypt.noteappmvvm.feature_note.data.data_source.NoteDao
import er.codeforegypt.noteappmvvm.feature_note.domain.model.Note
import er.codeforegypt.noteappmvvm.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

//5
//as this is simple app and there is no much logic here i can directly
// use fun from dao without any implementation but i f u have big app use api
//u will need this
class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}
