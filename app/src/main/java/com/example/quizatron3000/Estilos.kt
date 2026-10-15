package com.example.quizatron3000

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object Estilos {
    object Cores {
        val azul = Color(0xFF61dcfb);
        val rosa = Color(0xFFfd92d2);
        val verde = Color(0xFF6fd7a3);
        val branco = Color(0xFFffffff)
    }

    object Textos {
        val Titulo = TextStyle (
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
    }
}