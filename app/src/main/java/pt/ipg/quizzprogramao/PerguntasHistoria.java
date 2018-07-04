package pt.ipg.quizzprogramao;

/**
 * Created by ruima on 04/07/2018.
 */

public class PerguntasHistoria {


    /**
     * Aqui poem-se as perguntas, na categoria Historia.
     */
    public String PerguntasHistoria [] = {
            "Qual foi o primeiro rei de Portugal?",
            "Qual foi a primeira capital de Portugal?",
            "Em que data foi feita a Revolução dos cravos?",
            "Em que ano é que Portugal entrou para a CEE?",
            "Em que ano se deu em Lisboa um terrível terramoto, provocando a destruição de grande parte da capital?",
            "Em que século Portugal se tornou um país independente",
            "Ministro de D. José I, Sebastião José de Carvalho e Melo ficou também conhecido como",
            "A República foi implantada em Portugal em"






    };


    /**
     * Aqui poem-se as respotas possiveis para responder á pergunta.
     */
    private String RespostasHistoria [] [] = {
            {"Dom Afonso Henriques", "D. Sancho I", " D. João I", "D. Maule I"},
            {"Lisboa", "Porto", "Coimbra", "Guimarães"},
            {"1985", "1974", "1987", "1965"},
            {"1947", "1985", "1986", "1990"},
            {"1750", "1754", "1756", "1755"},
            {"XIV", "XI", "XII", "XV"},
            {"D. Manuel", "Marquês de Pombal", "D.Sebastião", "D.João"},
            {"1912", "1910", "1905", "1907" }




    };


    /**
     * Aqui poem-se as respostas corretas por oredem como nas perguntas.
     */
    private String RespostasHistoriaCorretas [] =
            {"Dom Afonso Henriques", "Guimarães", "1974", "1986","1755","XII", "Marquês de Pombal", "1910" };




    /**
     * Aqui faz-se o push da pergunta
     */
    public String getPerguntaHistoria(int a) {
        String perguntahistoria;
        perguntahistoria = PerguntasHistoria[a];
        return perguntahistoria;

    }

    public String getEscolha0(int a) {
        String Escolha;
        Escolha = RespostasHistoria[a][0];
        return Escolha;

    }


    public String getEscolha1(int a) {
        String Escolha;
        Escolha = RespostasHistoria[a][1];
        return Escolha;
    }


    public String getEscolha2(int a) {
        String Escolha;
        Escolha = RespostasHistoria[a][2];
        return Escolha;
    }


    public String getEscolha3(int a) {
        String Escolha;
        Escolha = RespostasHistoria[a][3];
        return Escolha;
    }



    /**
     * Aqui faz-se o push da resposta correta
     */
    public String getRespostaHistoriaCorreta (int a) {
        String resposta;
        resposta = RespostasHistoriaCorretas[a];
        return resposta;
    }

}
