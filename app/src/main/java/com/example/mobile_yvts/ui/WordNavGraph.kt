package com.example.mobile_yvts.ui

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.mobile_yvts.ui.home.HomeScreen
import com.example.mobile_yvts.ui.word.WordEdit
import com.example.mobile_yvts.ui.word.Settings
import com.example.mobile_yvts.data.Word
import com.example.mobile_yvts.ui.home.DisplayMode

@Composable
fun WordNavGraph(
    navController: NavHostController,
    words: List<Word>,
    currentWord: Word?,
    displayMode: DisplayMode,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onDelete: () -> Unit,
    onSaveWord: (String, String) -> Unit,
    onUpdateWord: (Int, String, String) -> Unit,
    onDisplayModeChange: (DisplayMode) -> Unit
) {
    NavHost(navController = navController, startDestination = Navigations.Home.route) {
        composable(Navigations.Home.route) {
            HomeScreen(
                word = currentWord,
                displayMode = displayMode,
                onPrevious = onPrevious,
                onNext = onNext,
                onAddClick = { navController.navigate(Navigations.Add.route) },
                onEditClick = {
                    currentWord?.let {
                        navController.navigate(Navigations.Edit.createRoute(it.id))
                    }
                },
                onDeleteClick = onDelete,
                onSettingsClick = { navController.navigate(Navigations.Settings.route) }
            )
        }

        composable(Navigations.Add.route) {
            WordEdit(
                word = null,
                onSave = { eng, mon ->
                    onSaveWord(eng, mon)
                    navController.popBackStack()
                },
                onCancel = { navController.popBackStack() }
            )
        }

        composable(
            route = Navigations.Edit.route,
            arguments = listOf(navArgument("wordId") { type = NavType.IntType })
        ) { backStackEntry ->
            val wordId = backStackEntry.arguments?.getInt("wordId")
            val word = words.find { it.id == wordId }

            WordEdit(
                word = word,
                onSave = { eng, mon ->
                    if (word != null) {
                        onUpdateWord(word.id, eng, mon)
                    }
                    navController.popBackStack()
                },
                onCancel = { navController.popBackStack() }
            )
        }

        composable(Navigations.Settings.route) {
            Settings(
                currentMode = displayMode,
                onSave = onDisplayModeChange,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
