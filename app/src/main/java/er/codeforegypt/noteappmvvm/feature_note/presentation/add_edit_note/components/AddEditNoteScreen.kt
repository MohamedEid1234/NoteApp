package er.codeforegypt.noteappmvvm.feature_note.presentation.add_edit_note.components

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import er.codeforegypt.noteappmvvm.feature_note.domain.model.Note
import er.codeforegypt.noteappmvvm.feature_note.presentation.add_edit_note.AddEditNoteEvent
import er.codeforegypt.noteappmvvm.feature_note.presentation.add_edit_note.AddEditNoteViewModel
import er.codeforegypt.noteappmvvm.feature_note.presentation.add_edit_note.UiEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: AddEditNoteViewModel = hiltViewModel()
){
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val noteBackGroundColorAnimatable = remember {
        Animatable(
            Color(if (noteColor!=-1)noteColor else viewModel.noteColor.value)
        )
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {event->
            when(event){
                UiEvent.SaveNote -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = " Note Saved ")
                }
                is UiEvent.ShowSnackbar -> {
                   navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onEvent(AddEditNoteEvent.SaveNote) },
                backgroundColor = MaterialTheme.colorScheme.primary) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "save note")
            }

        },
        scaffoldState = scaffoldState

    ) {val padding = it
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(noteBackGroundColorAnimatable.value)
            .padding(16.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ){
                Note.noteColors.forEach { color->
                    val colorInt = color.toArgb()
                    Box(modifier = Modifier
                        .size(50.dp)
                        .shadow(15.dp, CircleShape)
                        .clip(CircleShape)
                        .background(color)
                        .border(
                            width = 3.dp,
                            color = if (viewModel.noteColor.value == colorInt) Color.Black
                            else Color.Transparent
                        )
                        .clickable {
                            scope.launch {
                                noteBackGroundColorAnimatable.animateTo(
                                    targetValue = Color(colorInt),
                                    animationSpec = tween(
                                        durationMillis = 500
                                    )
                                )
                            }
                            viewModel.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
                        })
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(text = titleState.text,
                hint = titleState.hint,
                isHintVisible =titleState.isHintVisible ,
                onValueChange ={viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))},
                onFocusChange = {viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))},
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = contentState.text,
                hint = contentState.hint,
                isHintVisible =contentState.isHintVisible ,
                onValueChange ={viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))},
                onFocusChange = {viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))},
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxHeight())

        }

    }


}