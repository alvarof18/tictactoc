package com.alvaro.tictactoc.ui.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alvaro.tictactoc.R
import com.alvaro.tictactoc.ui.theme.Accent
import com.alvaro.tictactoc.ui.theme.Orange1
import com.alvaro.tictactoc.ui.theme.Orange2
import com.alvaro.tictactoc.ui.theme.background


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigationToGame: (String, String, Boolean) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                background
            )
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Header()
        Body(
            onCreateGame = { homeViewModel.onCreateGame(navigationToGame) },
            onJoinGame = { homeViewModel.onJoinGame(gameId = it, navigationToGame) })
    }
}

@Composable
fun Body(onCreateGame: () -> Unit, onJoinGame: (String) -> Unit) {

    var createGame by remember { mutableStateOf(true) }
    Card(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(24.dp),
        border = BorderStroke(2.dp, Orange1),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = background
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Switch(
                checked = createGame,
                onCheckedChange = { createGame = it },
                colors = SwitchDefaults.colors(checkedThumbColor = Orange2)
            )

            AnimatedContent(targetState = createGame, label = "") {

                when (it) {
                    true -> CreateGame(onCreateGame)
                    false -> JoinGame(onJoinGame)
                }

            }
            Spacer(Modifier.height(24.dp))

        }
    }
}

@Composable
fun Header() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            Modifier
                .size(200.dp)
                .padding(12.dp)
                .clip(RoundedCornerShape(24.dp))
                .border(2.dp, color = Orange1, RoundedCornerShape(24.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.applogo),
                contentDescription = "logo",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            )
        }
        Text(
            text = "Firebase",
            fontSize = 32.sp,
            color = Orange1,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "3 en Raya",
            fontSize = 32.sp,
            color = Orange2,
            fontWeight = FontWeight.Bold
        )


    }


}

@Composable
fun CreateGame(onCreateGame: () -> Unit) {
    Button(
        onClick = { onCreateGame() },
        colors = ButtonDefaults.buttonColors(backgroundColor = Orange1)
    ) {
        Text(text = "Create Game")
    }

}

@Composable
fun JoinGame(onJoinGame: (String) -> Unit) {

    var text by remember {
        mutableStateOf("")
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Accent,
                focusedBorderColor = Orange1,
                unfocusedBorderColor = Accent,
                cursorColor = Orange1

            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onJoinGame(text) },
            enabled = text.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Orange1)
        ) {
            Text(text = "Join Game", color = Accent)
        }

    }
}
