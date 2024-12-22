package er.codeforegypt.noteappmvvm.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import er.codeforegypt.noteappmvvm.feature_note.domain.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object{
        const val DATABASE_NAME = "notes_db"
    }
}

//after that make repo as it directly access data source either from database or api and take it and
// choose which one to forward to usecases and usecases dont care from where the data come it use it directly