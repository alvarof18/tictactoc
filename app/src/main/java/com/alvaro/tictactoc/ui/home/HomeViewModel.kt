package com.alvaro.tictactoc.ui.home

import androidx.lifecycle.ViewModel
import com.alvaro.tictactoc.data.network.FirebaseService
import com.alvaro.tictactoc.data.network.model.GameData
import com.alvaro.tictactoc.data.network.model.PlayerData
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val firebaseService: FirebaseService) :
    ViewModel() {

    fun onCreateGame(navigationTo: (String, String, Boolean) -> Unit) {

        val game = createNewGame()
        val gameId = firebaseService.createGame(game)
        val userId = game.player1?.userId.orEmpty()
        val owner:Boolean = true
        navigationTo(gameId, userId, owner)
    }

    fun onJoinGame(gameId: String, navigationTo: (String, String, Boolean) -> Unit) {
        val owner = false
        navigationTo(gameId, createNewUserId(), owner)
    }

    private fun createNewUserId(): String {
        return Calendar.getInstance().timeInMillis.hashCode().toString()
    }

    private fun createNewGame(): GameData {
        //Es lo mismo que List(9){0} = listOf(0,0,0,0,0,0,0,0,0)
        val currentPlayer = PlayerData(playerType = 1)
        return GameData(
            board = List(9) { 0 },
            player1 = currentPlayer,
            playerTurn = currentPlayer,
            player2 = null
        )

    }
}