package er.codeforegypt.noteappmvvm.feature_note.domain.use_case
//11
//if i font make this and i have alot of view models i will put all
// the 4 usecases in constructor of all viewmodel
// whitch will be big so i put all in single data class
// then i put it in the constructor
//this is the class that i will inject in viewmodel
data class NoteUseCases (
    val getNotes: GetNotesUseCase,
    val deleteNote: DeleteNoteUseCase,
    val addNote: AddNoteUseCase,
    val getNote: GetNoteUseCase


)