package com.example.quizatron3000.Quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel() {
    data class Pergunta(
        val nome: String,
        val alternativas: List<Alternativa>
    )

    data class Alternativa(
        val texto: String,
        val correta: Boolean
    )

    val perguntas = listOf<Pergunta>(
        Pergunta(
            "Qual a capital da França?",
            alternativas = listOf(
                Alternativa("Jandira", false),
                Alternativa("Paris", true),
                Alternativa("Grécia", false),
                Alternativa( "Gales", false)
            )
        ),
        Pergunta(
            "Qual o planeta conhecido como planeta vermelho?",
            alternativas = listOf(
                Alternativa("Marte", true),
                Alternativa("Júpiter", false),
                Alternativa("Saturno", false),
                Alternativa( "Terra", false)
            )
        )
    )

    private var indiceAtual = 0;

    private val _perguntaAtual = MutableLiveData<Pergunta>()
    val perguntaAtual: LiveData<Pergunta> = _perguntaAtual

    private val _textoHeader = MutableLiveData<String>()
    val textoHeader: LiveData<String> = _textoHeader

    private val _fimJogo = MutableLiveData<Boolean>()
    val fimJogo: LiveData<Boolean> = _fimJogo

    private val _respostaCorreta = MutableLiveData<Boolean?>()
    val respostaCorreta: LiveData<Boolean?> = _respostaCorreta

    private val _acertos = MutableLiveData(0)
    val acertos: LiveData<Int> = _acertos

    private fun atualizarQuestao() {
        _perguntaAtual.value = perguntas[indiceAtual];
        _textoHeader.value = "Pergunta ${indiceAtual + 1} de ${perguntas.size}"
    }

    init {
        atualizarQuestao()
        _fimJogo.value = false;
    }

    fun responder(alternativa: Alternativa) {
        _respostaCorreta.value = alternativa.correta

        if (alternativa.correta) {
            _acertos.value = (_acertos.value ?: 0) + 1
        }
    }

    fun avancarPergunta() {
        _respostaCorreta.value = null

        if (indiceAtual < perguntas.size - 1) {
            indiceAtual++
            _fimJogo.value = false
            atualizarQuestao()
        } else {
            println("Fim de jogo")
            _fimJogo.value = true
        }
    }
}