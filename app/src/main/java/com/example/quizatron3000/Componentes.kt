package com.example.quizatron3000

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Botao(
    modifier: Modifier = Modifier,
    texto: String,
    classe: Estilos.EstiloBotao,
    altura: Dp? = null,
    largura: Dp? = null,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.size(
            width = largura ?: classe.largura,
            height = altura ?: classe.altura
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = classe.corFundo,
            contentColor = classe.corTexto
        ),
        shape = classe.arredondamento,
        border = BorderStroke(width = 1.5.dp, color = classe.corBorda)
    ) {
        Text(
            text = texto,
            fontSize = classe.tamanhofonte
        )
    }
}

@Composable
fun Container(
    modifier: Modifier = Modifier,
    texto: String? = null,
    classe: Estilos.EstiloContainer,
    altura: Dp? = null,
    largura: Dp? = null,
    conteudo: @Composable BoxScope.() -> Unit = {}
) {
    val bordaFinal = classe.corBorda ?: Color(0x00000000)

    Box(
        modifier = modifier
            .size(
                width = largura ?: classe.largura,
                height = altura ?: classe.altura
            )
            .background(classe.corFundo, shape = classe.arredondamento)
            .border(width = 1.5.dp, color = bordaFinal, shape = classe.arredondamento),
        contentAlignment = Alignment.Center
    ) {
        if (!texto.isNullOrEmpty()) {
            Text(
                text = texto,
                color = classe.corTexto ?: Estilos.Cores.preto,
                fontSize = classe.tamanhofonte ?: 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        conteudo();
    }
}