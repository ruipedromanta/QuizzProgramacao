package pt.ipg.quizzprogramao;

/**
 * Created by ruima on 29/05/2018.
 */

public class PerguntasPlanetas {



    /**
     * Aqui poem-se as perguntas, na categoria Planetas.
     */
    public String Perguntas [] = {
            "Quaa é o primeiro planeta do sistema solar?",
            "Qual é o segundo planeta do sistema solar?",
            "Qual é o terceiro planeta do sistema solar?",
            "Qual é o quarto planeta do sistema solar?",
            "Qual é o quinto planeta do sistema solar?",
            "Qual é o sexto planeta do sistema solar?",
            "Qual é o setimo planeta do sistema solar?",
            "Qual é o oitavo planeta do sistema solar?",
            "Qual é o nono planeta do sistema solar?",
            "Qual é o planeta mais próximo do sol?"

    };




    /**
     * Aqui poem-se as respotas possiveis para responder á pergunta.
     */
    private String Respostas [][] = {
            {"Marte", "Mercurio", "Saturno", "Uránio"},
            {"Vênus", "Terra", "Marte", "Neptuno"},
            {"Terra", "Venus", "Marte", "Saturno"},
            {"Neptuno", "Venus", "Marte", "Terra"},
            {"Mercurio", "Plutão", "Marte", "Jupiter"},
            {"Terra", "Venus", "Marte", "Saturno"},
            {"Uranio", "Venus", "Marte", "Saturno"},
            {"Mercurio", "Neptuno", "Marte", "Saturno"},
            {"Mercurio", "Venus", "Marte", "Plutão"},
            {"Mercurio", "Neptuno", "Marte", "Saturno"}
    };



    /**
     * Aqui poem-se as respostas corretas por oredem como nas perguntas.
     */
    private String RespostasCorretas [] = {"Mercurio", "Vênus", "Terra", "Marte", "Júpiter", "Saturno", "Uranio", "Neptuno", "Plutão", "Mercurio"};




    /**
     * Aqui faz-se o push da pergunta
     */
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


    /**
     * Aqui faz-se o push da resposta correta
     */
    public String getRespostaCorreta(int a) {
        String resposta;
        resposta = RespostasCorretas[a];
        return resposta;

    }
}
