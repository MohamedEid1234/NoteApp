package er.codeforegypt.noteappmvvm.feature_note.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import er.codeforegypt.noteappmvvm.core.util.TestTags
import er.codeforegypt.noteappmvvm.di.AppModule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

//simulate user interaction
@HiltAndroidTest
@UninstallModules(AppModule::class)
class NotesEndToEndTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalAnimationApi::class)
    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun saveNewNote_editAfterwards(){
        //click fab to add note
        composeRule.onNodeWithContentDescription("Add").performClick()
        //enter note then add it
        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextInput("test-title")
        composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).performTextInput("test-content")
        composeRule.onNodeWithContentDescription("Save").performClick()
        //ensure the note is added
        composeRule.onNodeWithText("test-title").assertIsDisplayed()
        composeRule.onNodeWithText("test-title").performClick()
        //click the note to edit it and save
        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).assertTextEquals("test-title")
        composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).assertTextEquals("test-content")
        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextInput("2")
        composeRule.onNodeWithContentDescription("Save").performClick()
        //ensure the note is edited
        composeRule.onNodeWithText("test-title2").assertIsDisplayed()


    }
    @Test
    fun saveNewNotes_orderByTitleDescending() {
        for (i in 1..3) {
            composeRule.onNodeWithContentDescription("Add").performClick()
            //enter note then add it
            composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextInput(i.toString())
            composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).performTextInput(i.toString())
            composeRule.onNodeWithContentDescription("Save").performClick()

        }
        composeRule.onNodeWithText("1").assertIsDisplayed()
        composeRule.onNodeWithText("2").assertIsDisplayed()
        composeRule.onNodeWithText("3").assertIsDisplayed()

        composeRule.onNodeWithContentDescription("Sort").performClick()
        /*to click radiobutton in test case u should add this to it
           modifier = Modifier.semantics {
                        contentDescription = text
                    }*/
        /*to write in text add this to text field (Modifier.textTag("NOTE_ITEM")) */
        /*anything with content description u can click it*/
        /*to ensure text is displayed composeRule.onNodeWithText("test-title").assertIsDisplayed()*/
        /*if u have items (notes) u can enumerate them like array
         by composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[0].assertTextContains("3") */
        /*u can delay by  composeRule.waitUntil(timeoutMillis = 500L) {**your action**}*/
        composeRule.onNodeWithContentDescription("Title").performClick()
        composeRule.onNodeWithContentDescription("Descending").performClick()

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[0].assertTextContains("3")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[1].assertTextContains("2")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[2].assertTextContains("1")
    }


    @Test
    fun deleteNote_Deleted_Restore_Restored() {
        composeRule.onNodeWithContentDescription("Add").performClick()
        // Add a new note
        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextInput("test-title")
        composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).performTextInput("test-content")
        composeRule.onNodeWithContentDescription("Save").performClick()

        // Ensure the note is displayed
        composeRule.onNodeWithText("test-title").assertIsDisplayed()

        // Delete the note
        composeRule.onNodeWithContentDescription("Delete").performClick()

        // Ensure the undo Snackbar is displayed
        composeRule.onNodeWithText("undo")
            .assertIsDisplayed()
            .performClick()

        // Wait for the note to be restored
        composeRule.waitUntil(timeoutMillis = 500L) {
            composeRule.onNodeWithText("test-title").isDisplayed()
        }
    }



}