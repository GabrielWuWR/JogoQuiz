package com.example.quizatron3000.TelaResultado

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.quizatron3000.Botao
import com.example.quizatron3000.Container
import com.example.quizatron3000.Estilos
import com.example.quizatron3000.R

@Composable
fun TelaResultado(
    acertos: Int,
    total: Int,
    modifier: Modifier = Modifier.fillMaxSize(),
    irPraHome: ()-> Unit
) {
    Column(
        modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.width(80.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier.fillMaxWidth().background(Estilos.Cores.azul)
                .height(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Container(classe = Estilos.Containers.Verde, texto = "Bom trabalho!")

            Text(
                style = Estilos.Textos.TituloPergunta,
                text = "Você acertou $acertos de $total perguntas",
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        Botao(classe = Estilos.Botoes.Amarelo, texto = "JOGAR NOVAMENTE", largura = 330.dp, altura = 80.dp) {
            irPraHome()
        }

    }
}