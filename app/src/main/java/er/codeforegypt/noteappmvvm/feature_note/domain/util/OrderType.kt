package er.codeforegypt.noteappmvvm.feature_note.domain.util
//6
sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}