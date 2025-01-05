package er.codeforegypt.noteappmvvm.feature_note.domain.use_case

import er.codeforegypt.noteappmvvm.feature_note.data.repository.FakeRepository
import er.codeforegypt.noteappmvvm.feature_note.domain.model.Note
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetNoteUseCaseTest {
    private lateinit var getNote: GetNoteUseCase
    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        getNote = GetNoteUseCase(fakeRepository)

        runBlocking {
            fakeRepository.insertNote(
                Note(
                    id = 1,
                    title = "Note 1",
                    content = "Content 1",
                    timestamp = 1000L,
                    color = 0
                )
            )

        }

    }

    @Test
    fun `getNote returns the correct note`() = runBlocking {
        // Arrange
        val noteId = 1
        val expectedNote = Note(
            id = 1,
            title = "Note 1",
            content = "Content 1",
            timestamp = 1000L,
            color = 0
        )
        // Act
        val result = getNote(noteId)
        // Assert
        assertEquals(result , expectedNote)


    }
}