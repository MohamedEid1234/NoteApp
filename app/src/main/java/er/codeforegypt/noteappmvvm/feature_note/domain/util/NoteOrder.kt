package er.codeforegypt.noteappmvvm.feature_note.domain.util
//7
sealed class NoteOrder(val orderType: OrderType) {
    class Title( orderType: OrderType) : NoteOrder(orderType)
    class Date( orderType: OrderType) : NoteOrder(orderType)
    class Color( orderType: OrderType) : NoteOrder(orderType)

    //i make this so as when i click ascending radiobutton (ordertype)
    // the Noteorder dont change
    fun copy(orderType: OrderType): NoteOrder{
return when(this){
    is Title -> Title(orderType)
    is Date -> Date(orderType)
    is Color -> Color(orderType)
}
    }
}

