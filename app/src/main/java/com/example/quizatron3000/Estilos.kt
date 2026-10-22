package com.example.quizatron3000

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Estilos {
    object Cores {
        val azul = Color(0xFF61dcfb);
        val rosa = Color(0xFFfd92d2);
        val verde = Color(0xFF6fd7a3);
        val branco = Color(0xFFffffff);
        val preto = Color(0xFF1e1b06)
        val amarelo = Color(0xFFfedf30);
        val cinza = Color(0xFFf3f3f3)
    }

    object Textos {
        val Titulo = TextStyle (
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

        val TituloPergunta = TextStyle (
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )
    }

    data class EstiloBotao(
        val largura: Dp,
        val altura: Dp,
        val corFundo: Color,
        val corTexto: Color,
        val corBorda: Color,
        val arredondamento: Shape,
        val tamanhofonte: TextUnit
    );

    object Botoes {
        val Amarelo = EstiloBotao(
            largura = 200.dp,
            altura = 50.dp,
            corFundo = Cores.amarelo,
            corTexto = Cores.preto,
            corBorda = Cores.preto,
            arredondamento = RoundedCornerShape(100.dp),
            tamanhofonte = 23.sp
        )
    }

    data class EstiloContainer(
        val largura: Dp,
        val altura: Dp,
        val corFundo: Color,
        val corTexto: Color ?= null,
        val corBorda: Color ?= null,
        val arredondamento: Shape,
        val tamanhofonte: TextUnit ?= null
    )

    object Containers {
        val Verde = EstiloContainer(
            largura = 290.dp,
            altura = 70.dp,
            corFundo = Cores.verde,
            corTexto = Cores.preto,
            arredondamento = RoundedCornerShape(20.dp),
            corBorda = Cores.preto,
            tamanhofonte = 25.sp
        )

        val Branco = EstiloContainer(
            largura = 330.dp,
            altura = 385.dp,
            corFundo = Cores.branco,
            arredondamento = RoundedCornerShape(15.dp)
        )

        val Pergunta = EstiloContainer(
            largura = 280.dp,
            altura = 50.dp,
            corFundo = Cores.branco,
            corBorda = Cores.cinza,
            arredondamento = RoundedCornerShape(10.dp),
            corTexto = Cores.preto
        )
    }
}