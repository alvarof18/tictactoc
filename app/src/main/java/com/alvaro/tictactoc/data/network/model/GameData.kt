package com.alvaro.tictactoc.data.network.model

import com.alvaro.tictactoc.ui.model.GameModel
import com.alvaro.tictactoc.ui.model.PlayerModel
import com.alvaro.tictactoc.ui.model.PlayerType
import java.util.Calendar

data class GameData(
    val board: List<Int>? = null,
    val gameId: String? = null,
    val player1:PlayerData? = null,
    val player2:PlayerData? = null,
    val playerTurn:PlayerData? = null,

) {
    fun toModel():GameModel {
        return GameModel(
            board = board?.map { PlayerType.getPlayerById(it) } ?: mutableListOf(),
            gameId = gameId.orEmpty(),
            player1 = player1!!.toModel(),
            player2 = player2?.toModel(),
            playerTurn = playerTurn!!.toModel()
        )
    }
}

data class PlayerData(
    val userId: String? = Calendar.getInstance().timeInMillis.hashCode().toString(),
    val playerType: Int? = null

){
    fun toModel():PlayerModel{
        return PlayerModel(userId = userId!!, playerType = PlayerType.getPlayerById(playerType))

    }
}
