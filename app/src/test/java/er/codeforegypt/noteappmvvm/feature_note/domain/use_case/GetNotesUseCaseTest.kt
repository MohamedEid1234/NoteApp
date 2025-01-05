package er.codeforegypt.noteappmvvm.feature_note.domain.use_case

import com.google.common.truth.Truth.assertThat
import er.codeforegypt.noteappmvvm.feature_note.data.repository.FakeRepository
import er.codeforegypt.noteappmvvm.feature_note.domain.model.Note
import er.codeforegypt.noteappmvvm.feature_note.domain.util.NoteOrder
import er.codeforegypt.noteappmvvm.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNotesUseCaseTest{
    private lateinit var getNotes : GetNotesUseCase
    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setUp(){
        fakeRepository = FakeRepository()
        getNotes = GetNotesUseCase(fakeRepository)

        val notesToInsert = mutableListOf<Note>()

        ('a'..'z').forEachIndexed { index, c ->
            notesToInsert.add(
                Note(
                    title = c.toString(),
                    content = c.toString(),
                    timestamp = index.toLong(),
                    color = index
            )
            )
        }

        notesToInsert.shuffle() //make it random

        runBlocking {
            notesToInsert.forEach { fakeRepository.insertNote(it) }
        } // put it in coroutine as it is flow


    }


    @Test
    fun OrderNotesByTitleascendingReturnCorrectOrder () = runBlocking{
        val notes = getNotes(NoteOrder.Title(OrderType.Ascending)).first()


        for (i in 0..notes.size - 2){
            assertThat(notes[i].title < notes[i+1].title)
        }


    }
    @Test
    fun OrderNotesByTitledescendingReturnCorrectOrder () = runBlocking{
        val notes = getNotes(NoteOrder.Title(OrderType.Descending)).first()


        for (i in 0..notes.size - 2){
            assertThat(notes[i].title > notes[i+1].title)
        }


    }
    @Test
    fun OrderNotesByDateascendingReturnCorrectOrder () = runBlocking{
        val notes = getNotes(NoteOrder.Date(OrderType.Ascending)).first()


        for (i in 0..notes.size - 2){
            assertThat(notes[i].timestamp < notes[i+1].timestamp)
        }


    }
    @Test
    fun OrderNotesByDatedescendingReturnCorrectOrder () = runBlocking{
        val notes = getNotes(NoteOrder.Date(OrderType.Descending)).first()


        for (i in 0..notes.size - 2){
            assertThat(notes[i].timestamp > notes[i+1].timestamp)
        }


    }
    @Test
    fun OrderNotesByColorascendingReturnCorrectOrder () = runBlocking{
        val notes = getNotes(NoteOrder.Color(OrderType.Ascending)).first()


        for (i in 0..notes.size - 2){
            assertThat(notes[i].color < notes[i+1].color)
        }


    }
    @Test
    fun OrderNotesByColordescendingReturnCorrectOrder () = runBlocking{
        val notes = getNotes(NoteOrder.Color(OrderType.Descending)).first()


        for (i in 0..notes.size - 2){
            assertThat(notes[i].color > notes[i+1].color)
        }


    }


}