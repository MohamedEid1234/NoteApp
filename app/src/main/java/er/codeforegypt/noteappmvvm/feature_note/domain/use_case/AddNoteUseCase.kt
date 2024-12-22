package er.codeforegypt.noteappmvvm.feature_note.domain.use_case

import er.codeforegypt.noteappmvvm.feature_note.domain.model.InvalidNoteException
import er.codeforegypt.noteappmvvm.feature_note.domain.model.Note
import er.codeforegypt.noteappmvvm.feature_note.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository

) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if (note.title.isBlank()){
            throw InvalidNoteException("tilte of the note can't be empty")
        }
        if (note.content.isBlank()){
            throw InvalidNoteException("content of the note can't be empty")
        }
        repository.insertNote(note)

    }
}