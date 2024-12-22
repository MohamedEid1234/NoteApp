package er.codeforegypt.noteappmvvm.feature_note.domain.use_case

import er.codeforegypt.noteappmvvm.feature_note.domain.model.Note
import er.codeforegypt.noteappmvvm.feature_note.domain.repository.NoteRepository
import er.codeforegypt.noteappmvvm.feature_note.domain.util.NoteOrder
import er.codeforegypt.noteappmvvm.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//8
//use case make code readable as they quickly reveile what inside
// it and view model use it directly and make code reusable
//bussness logic inside usecase if i made it i can use it in different viewmoodels
//but if dont make them i will need to duplicate the code in every viewmodel
//this is the most complex one  as i will use buissness logic here as i want to
// get notes flow ordered
//use case should contain one single fun which is public
class GetNotesUseCase(
    //i used the rep interface not repoimpl class as interface is replacable
    private val repository: NoteRepository
) {
    operator fun invoke(
        //i set the default value of the order type to Date descending
        noteOrder: NoteOrder=NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>>{
        return repository.getNotes().map {notes->
            when(noteOrder.orderType){
                OrderType.Ascending -> {
                    when(noteOrder){
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                    }

                }
                OrderType.Descending -> {
                    when(noteOrder){
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                        is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                    }
                }
            }
        }

    }
}