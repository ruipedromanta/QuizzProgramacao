package pt.ipg.quizzprogramao;

/**
 * Created by ruima on 29/05/2018.
 */

public class PerguntasPlanetas {

    public String Perguntas [] = {
            "Quanto é 1 + 1?",
            "Qual é o segundo planeta do sistema solar?",
            "Qual é o terceiro planeta do sistema solar?",
            "Qual é o quarto planeta do sistema solar?",
            "Qual é o quinto planeta do sistema solar?",
            "Qual é o sexto planeta do sistema solar?",
            "Qual é o setimo planeta do sistema solar?",
            "Qual é o oitavo planeta do sistema solar?",
            "Qual é o nono planeta do sistema solar?"

    };




    private String Respostas [][] = {
            {"2", "3", "1", "8"},
            {"Venus", "Terra", "Marte", "Neptuno"},
            {"Terra", "Venus", "Marte", "Saturno"},
            {"Neptuno", "Venus", "Marte", "Terra"},
            {"Mercurio", "Plutão", "Marte", "Jupiter"},
            {"Terra", "Venus", "Marte", "Saturno"},
            {"Uranio", "Venus", "Marte", "Saturno"},
            {"Mercurio", "Neptuno", "Marte", "Saturno"},
            {"Mercurio", "Venus", "Marte", "Plutão"},

    };


    private String RespostasCorretas [] = {"Mercurio", "Venus", "Terra", "Marte", "Jupiter", "Saturno", "Uranio", "Neptuno", "Plutão"};

    public String getPergunta(int a){
        String pergunta;
        pergunta = Perguntas[a];
        return pergunta;

    }


    public String getEscolha1(int a){
        String Escolha;
        Escolha = Respostas[a][0];
        return Escolha;

    }
    public String getEscolha2(int a){
        String Escolha;
        Escolha = Respostas[a][1];
        return Escolha;

    }
    public String getEscolha3(int a){
        String Escolha;
        Escolha = Respostas[a][2];
        return Escolha;

    }
    public String getEscolha4(int a){
        String Escolha;
        Escolha = Respostas[a][3];
        return Escolha;

    }



    public String getRespostaCorreta(int a) {
        String resposta;
        resposta = RespostasCorretas[a];
        return resposta;

    }
}
