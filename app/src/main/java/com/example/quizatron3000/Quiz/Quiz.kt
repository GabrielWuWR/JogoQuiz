package com.example.quizatron3000.Quiz

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizatron3000.Container
import com.example.quizatron3000.Estilos
import com.example.quizatron3000.R
import com.example.quizatron3000.Transicao.Transicao
import kotlinx.coroutines.delay

@Composable
fun TelaQuiz(
    modifier: Modifier = Modifier,
    viewModel: QuizViewModel = viewModel(),
    telaFinal: (acertos: Int, total: Int) -> Unit,
) {
    val pergunta by viewModel.perguntaAtual.observeAsState()
    val perguntaAtual = pergunta

    val textoHeader by viewModel.textoHeader.observeAsState()
    val textoHeaderAtual = textoHeader

    val acertos by viewModel.acertos.observeAsState(initial = 0)
    val totalPerguntas = viewModel.perguntas.size

    var alternativaSelecionada by remember { mutableStateOf<QuizViewModel.Alternativa?>(null) }
    var mostrarSucesso by remember { mutableStateOf(false) }
    var mostrarErro by remember { mutableStateOf(false) }
    var mostrandoRespostas by remember { mutableStateOf(false) }
    var mostrarProximaTransicao by remember { mutableStateOf(false) }

    val palavrasSucesso = remember {
        listOf(
            "Aí sim em",
            "Mestre do conhecimento",
            "Continue assim",
            "Muito bem",
            "Tá lembrando muito meu começo",
            "Parabéns"
        )
    }
    val palavrasErro = remember {
        listOf(
            "Quase lá",
            "Mais sorte na próxima vez...",
            "Todos erram.",
            "Ainda bem que não sou você kkkkkk",
            "Resposda errada",
            "Tente de novo tá quase lá em"
        )
    }

    val palavrasContinuar = remember {
        listOf(
            "Vamos para a próxima!",
            "Preparado?",
            "Lá vem mais uma...",
            "Foco total!",
            "Respira e vai!"
        )
    }

    var fraseSucesso by remember { mutableStateOf(palavrasSucesso.random()) }
    var fraseErro by remember { mutableStateOf(palavrasErro.random()) }
    var fraseContinuar by remember { mutableStateOf(palavrasContinuar.random()) }

    LaunchedEffect(mostrandoRespostas) {
        if (mostrandoRespostas) {
            delay(1800)

            mostrandoRespostas = false
            fraseContinuar = palavrasContinuar.random()
            mostrarProximaTransicao = true
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Estilos.Cores.rosa),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.width(80.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Container(modifier = Modifier, classe = Estilos.Containers.Verde, texto = textoHeaderAtual)
            Spacer(modifier = Modifier.height(20.dp))

            Container(modifier = Modifier, classe = Estilos.Containers.Branco) {
                Column(modifier = Modifier.fillMaxSize().padding(25.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
                    Text(text = perguntaAtual?.nome ?: "", style = Estilos.Textos.TituloPergunta)

                    Column(modifier = Modifier, verticalArrangement = Arrangement.Center) {
                        perguntaAtual?.alternativas?.forEach { alternativa ->

                            Spacer(Modifier.height(20.dp))

                            val respostaNormal = Estilos.Containers.Pergunta;

                            val respostaColorida = if (mostrandoRespostas && alternativaSelecionada != null) {
                                if (alternativa.correta) {
                                    respostaNormal.copy(corBorda = Estilos.Cores.verde)
                                } else {
                                    respostaNormal.copy(corBorda = Estilos.Cores.rosa)
                                }
                            } else {
                                respostaNormal
                            }

                            Container(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(enabled = alternativaSelecionada == null) {
                                        alternativaSelecionada = alternativa
                                        viewModel.responder(alternativa)

                                        if (alternativa.correta) {
                                            fraseSucesso = palavrasSucesso.random()
                                            mostrarSucesso = true
                                        } else {
                                            fraseErro = palavrasErro.random()
                                            mostrarErro = true
                                        }
                                    },
                                classe = respostaColorida,
                                texto = alternativa.texto
                            )
                        }
                    }
                }
            }
        }

        //Animação de resposta correta
        AnimatedVisibility(
            visible = mostrarSucesso,
            enter = slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }, animationSpec = tween(500)),
            exit = slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth }, animationSpec = tween(500))
        ) {
            Transicao(
                cor = Estilos.Cores.verde,
                texto = fraseSucesso,
                tempo = 1500,
                corTexto = Estilos.Cores.preto,
                vaiPara = {
                    mostrarSucesso = false
                    mostrandoRespostas = true;
                }
            )
        }

        //Animação de resposta incorreta
        AnimatedVisibility(
            visible = mostrarErro,
            enter = slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }, animationSpec = tween(500)),
            exit = slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth }, animationSpec = tween(500))
        ) {
            Transicao(
                cor = Estilos.Cores.rosa,
                texto = fraseErro,
                tempo = 1500,
                corTexto = Estilos.Cores.preto,
                vaiPara = {
                    mostrarErro = false
                    mostrandoRespostas = true
                }
            )
        }

        //Animação de transição
        AnimatedVisibility(
            visible = mostrarProximaTransicao,
            enter = slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }, animationSpec = tween(500)),
            exit = slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth }, animationSpec = tween(500))
        ) {
            Transicao(
                cor = Estilos.Cores.amarelo,
                texto = fraseContinuar,
                tempo = 1500,
                corTexto = Estilos.Cores.preto,
                vaiPara = {
                    mostrarProximaTransicao = false
                    alternativaSelecionada = null

                    viewModel.avancarPergunta()

                    if (viewModel.fimJogo.value == true) {
                        telaFinal(acertos, totalPerguntas)
                    }
                }
            )
        }
    }
}