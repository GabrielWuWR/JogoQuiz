package com.example.quizatron3000

import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizatron3000.ui.theme.Quizatron3000Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Quizatron3000Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaHome(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TelaHome(
    modifier: Modifier = Modifier
) {
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
        Text(
            text = "Quizatron 3000",
            style = Estilos.Textos.Titulo
        )
    }
}