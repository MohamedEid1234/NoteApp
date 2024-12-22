package er.codeforegypt.noteappmvvm.feature_note.presentation.notes

import er.codeforegypt.noteappmvvm.feature_note.domain.model.Note
import er.codeforegypt.noteappmvvm.feature_note.domain.util.NoteOrder
import er.codeforegypt.noteappmvvm.feature_note.domain.util.OrderType

data class NotesState(
    val notes : List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false

)
