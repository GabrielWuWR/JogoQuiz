package com.example.quizatron3000.Transicao

import androidx.annotation.Nullable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.quizatron3000.Estilos
import kotlinx.coroutines.delay

@Composable
fun Transicao(modifier: Modifier = Modifier, cor: Color, texto: String, corTexto: Color ?= null, tempo: Long, vaiPara: () -> Unit) {

    //Isso aqui roda assim que a tela carregar
    LaunchedEffect(Unit) {
        delay(tempo) //No caso vai esperar um tempo
        vaiPara()  //E aí vai pra tela que foi configurada
    }

    Box(
        modifier = modifier.fillMaxSize()
            .background(color = cor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = texto,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = corTexto ?: Estilos.Cores.branco,
            textAlign = TextAlign.Center,
            lineHeight = 60.sp
        )
    }
}
