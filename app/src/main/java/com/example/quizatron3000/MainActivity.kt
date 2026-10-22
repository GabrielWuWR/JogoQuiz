package com.example.quizatron3000

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quizatron3000.Home.TelaHome
import com.example.quizatron3000.Quiz.TelaQuiz
import com.example.quizatron3000.TelaResultado.TelaResultado
import com.example.quizatron3000.Transicao.Transicao
import com.example.quizatron3000.ui.theme.Quizatron3000Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val palavrasTitulo = listOf<String>(
                "Preparado?",
                "Vamos lá?",
                "Boa sorte!",
                "Tente sobreviver",
                "Você consegue!",
                "Are you are? Fight!"
            );

            Quizatron3000Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.fillMaxSize().padding(innerPadding)
                    ) {
                        composable("home") {
                            TelaHome(onClickIniciar = {
                                navController.navigate("transicao")
                            })
                        }

                        composable(
                            "transicao",
                            enterTransition = {
                                slideInHorizontally(
                                    initialOffsetX = {fullWidth -> fullWidth},
                                    animationSpec = tween(durationMillis = 500)
                                )
                            },
                            exitTransition = {
                                slideOutHorizontally(
                                    targetOffsetX = { fullWidth -> -fullWidth },
                                    animationSpec = tween(durationMillis = 500)
                                )
                            }
                        ) {
                            Transicao(
                                modifier = Modifier,
                                cor = Estilos.Cores.amarelo,
                                texto = remember { palavrasTitulo.random() },
                                tempo = 1500,
                                corTexto = Estilos.Cores.preto,
                                vaiPara = {
                                    navController.navigate("quiz") {
                                        popUpTo("home") {inclusive = false}
                                    }
                                }
                            )
                        }

                        composable("quiz") {
                            TelaQuiz(
                                telaFinal = { acertos, total ->
                                    navController.navigate("telaFim/$acertos/$total")
                                }
                            );
                        }

                        composable(
                            route = "telaFim/{acertos}/{total}",
                            arguments = listOf(
                                navArgument("acertos") { type = NavType.IntType },
                                navArgument("total") { type = NavType.IntType }
                            )
                        ) { backStackEntry ->

                            val acertos = backStackEntry.arguments?.getInt("acertos") ?: 0
                            val total = backStackEntry.arguments?.getInt("total") ?: 0

                            TelaResultado(
                                acertos = acertos,
                                total = total,
                                irPraHome = {
                                    navController.navigate("home") {
                                        popUpTo("home") { inclusive = true }
                                    }
                                }
                            )
                        }
                    }

                }
            }
        }
    }
}