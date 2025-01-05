package er.codeforegypt.noteappmvvm.feature_note.domain.use_case

import er.codeforegypt.noteappmvvm.feature_note.data.repository.FakeRepository
import er.codeforegypt.noteappmvvm.feature_note.domain.model.InvalidNoteException
import er.codeforegypt.noteappmvvm.feature_note.domain.model.Note
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class AddNoteUseCaseTest{
    private lateinit var addNoteUseCase: AddNoteUseCase
    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        addNoteUseCase = AddNoteUseCase(fakeRepository)
    }

    @Test
    fun `invoke should insert note when title and content are valid`() = runBlocking {
        // Arrange
        val validNote = Note(
            id = 1,
            title = "Test Note",
            content = "This is a valid note.",
            timestamp = System.currentTimeMillis(),
            color = 0
        )

        // Act
        addNoteUseCase(validNote)

        // Assert
        val notes = fakeRepository.getNotes().toList().first()
        assertTrue(notes.contains(validNote))
    }

    @Test
    fun `invoke should throw InvalidNoteException when title is blank`() {
        // Arrange
        val invalidNote = Note(
            id = 2,
            title = "",
            content = "Valid content",
            timestamp = System.currentTimeMillis(),
            color = 0
        )

        // Act & Assert
        assertThrows(InvalidNoteException::class.java) {
            runBlocking {
                addNoteUseCase(invalidNote)
            }
        }
    }

    @Test
    fun `invoke should throw InvalidNoteException when content is blank`() {
        // Arrange
        val invalidNote = Note(
            id = 3,
            title = "Valid title",
            content = "",
            timestamp = System.currentTimeMillis(),
            color = 0
        )

        // Act & Assert
        assertThrows(InvalidNoteException::class.java) {
            runBlocking {
                addNoteUseCase(invalidNote)
            }
        }
    }

    @Test
    fun `invoke should not add note to repository when exception is thrown`() = runBlocking {
        // Arrange
        val invalidNote = Note(
            id = 4,
            title = "",
            content = "Some content",
            timestamp = System.currentTimeMillis(),
            color = 0
        )

        // Act
        try {
            addNoteUseCase(invalidNote)
        } catch (e: InvalidNoteException) {
            // Expected
        }

        // Assert
        val notes = fakeRepository.getNotes().toList().first()
        assertFalse(notes.contains(invalidNote))
    }
}