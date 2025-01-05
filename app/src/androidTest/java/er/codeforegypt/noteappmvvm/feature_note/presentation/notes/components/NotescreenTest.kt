package er.codeforegypt.noteappmvvm.feature_note.presentation.notes.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import er.codeforegypt.noteappmvvm.core.util.TestTags
import er.codeforegypt.noteappmvvm.di.AppModule
import er.codeforegypt.noteappmvvm.feature_note.presentation.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

//to do instrumental test cases first create app module test that include inMemoryDatabaseBuilder instead of
// real database then hilt test runner and add it in build.gradle


@HiltAndroidTest
@UninstallModules(AppModule::class)
class NotescreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalAnimationApi::class)
    @Before
    fun setUp() {
        hiltRule.inject()

    }
    //test case to insure that order section is not displayed firstly
    @Test
    fun clickToggleOrderSection_isVisible(){
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).assertDoesNotExist()
        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).assertIsDisplayed()
    }
    @Test
    fun closeOrderSection_disappear(){
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).assertDoesNotExist()
        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).assertIsDisplayed()
        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).assertDoesNotExist()



    }

}