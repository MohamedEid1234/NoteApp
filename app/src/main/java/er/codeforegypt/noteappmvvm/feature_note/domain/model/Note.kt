package er.codeforegypt.noteappmvvm.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import er.codeforegypt.noteappmvvm.ui.theme.BabyBlue
import er.codeforegypt.noteappmvvm.ui.theme.LightGreen
import er.codeforegypt.noteappmvvm.ui.theme.RedOrange
import er.codeforegypt.noteappmvvm.ui.theme.RedPink
import er.codeforegypt.noteappmvvm.ui.theme.Violet

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null


){
    companion object{
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message)