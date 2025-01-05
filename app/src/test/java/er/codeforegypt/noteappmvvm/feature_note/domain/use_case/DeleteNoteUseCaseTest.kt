package er.codeforegypt.noteappmvvm.feature_note.domain.use_case

import er.codeforegypt.noteappmvvm.feature_note.data.repository.FakeRepository
import er.codeforegypt.noteappmvvm.feature_note.domain.model.Note
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DeleteNoteUseCaseTest {
    private lateinit var deleteNoteUseCase: DeleteNoteUseCase
    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        deleteNoteUseCase = DeleteNoteUseCase(fakeRepository)

        // Pre-populate the repository with some notes for testing
        runBlocking {
            fakeRepository.insertNote(
                Note(
                    id = 1,
                    title = "Note 1",
                    content = "Content 1",
                    timestamp = System.currentTimeMillis(),
                    color = 0
                )
            )
            fakeRepository.insertNote(
                Note(
                    id = 2,
                    title = "Note 2",
                    content = "Content 2",
                    timestamp = System.currentTimeMillis(),
                    color = 1
                )
            )
        }
    }

    @Test
    fun `deleteNote removes the correct note from repository`() = runBlocking {
        // Arrange
        val noteToDelete = Note(
            id = 1,
            title = "Note 1",
            content = "Content 1",
            timestamp = System.currentTimeMillis(),
            color = 0
        )

        // Act
        deleteNoteUseCase(noteToDelete)

        // Assert
        val notes = fakeRepository.getNotes().toList().first()
        assertFalse(notes.contains(noteToDelete))
    }

    @Test
    fun `deleteNote does not remove other notes`() = runBlocking {
        // Arrange
        val noteToDelete = Note(
            id = 1,
            title = "Note 1",
            content = "Content 1",
            timestamp = System.currentTimeMillis(),
            color = 0
        )

        // Act
        deleteNoteUseCase(noteToDelete)

        // Assert
        val notes = fakeRepository.getNotes().toList().first()
        assertTrue(notes.any { it.id == 2 })
    }

    @Test
    fun `deleteNote on non-existent note does not crash`() = runBlocking {
        // Arrange
        val nonExistentNote = Note(
            id = 3,
            title = "Non-existent Note",
            content = "This note does not exist in the repository",
            timestamp = System.currentTimeMillis(),
            color = 2
        )

        // Act
        deleteNoteUseCase(nonExistentNote)

        // Assert
        val notes = fakeRepository.getNotes().toList().first()
        assertFalse(notes.contains(nonExistentNote))
    }
}
