package com.example.quizatron3000.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.quizatron3000.Botao
import com.example.quizatron3000.Estilos
import com.example.quizatron3000.R

@Composable
fun TelaHome(
    modifier: Modifier = Modifier,
    onClickIniciar: () -> Unit
) {
    val navController = rememberNavController();

    Column(modifier = modifier
        .fillMaxSize()
        .background(Estilos.Cores.azul),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = modifier
                .width(140.dp),
            painter = painterResource(R.drawable.logo),
            contentDescription = "Login"
        )

        Spacer(
            Modifier.height(30.dp)
        )

        Text(
            text = "Quizatron 3000",
            style = Estilos.Textos.Titulo
        )
        Spacer(
            Modifier.height(50.dp)
        )

        Botao(texto = "COMEÇAR!", classe = Estilos.Botoes.Amarelo, largura = 330.dp, altura = 80.dp) {
            onClickIniciar()
        }
    }
}