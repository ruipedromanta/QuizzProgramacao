package pt.ipg.quizzprogramao;

/**
 * Created by ruima on 04/07/2018.
 */

public class PerguntasDesporto {




    /**
     * Aqui poem-se as perguntas, na categoria Desporto.
     */
        public String PerguntasDesporto [] = {
                "Quem é o melhor jogador do mundo de futebol de 11?",
                "Quem é o melhor jogador do mundo de futsal?",
                "Quem é o melhor jogador do mundo de futebol de praia?",




        };

    /**
     * Aqui poem-se as respotas possiveis para responder á pergunta.
     */
        private String RespostasDesporto [] [] = {
                {"Lionel Messi", "Neymar", "Cristiano Ronaldo", "Frank Ribery"},
                {"Ricardinho", "Elisandro", "Rafeal Rato", "Cardinal"},
                {"Alan", "Madjer", "André", "Ristovsky"}




        };
    /**
     * Aqui poem-se as respostas corretas por oredem como nas perguntas.
     */
        private String RespostasDesportoCorretas [] =
                {"Cristiano Ronaldo", "Ricardinho", "Madjer"};



    /**
     * Aqui faz-se o push da pergunta
     */
        public String getPerguntaDesporto(int a) {
            String perguntadesporto;
            perguntadesporto = PerguntasDesporto[a];
            return perguntadesporto;

        }

        public String getEscolha0(int a) {
            String Escolha;
            Escolha = RespostasDesporto[a][0];
            return Escolha;

        }


        public String getEscolha1(int a) {
            String Escolha;
            Escolha = RespostasDesporto[a][1];
            return Escolha;
        }


        public String getEscolha2(int a) {
            String Escolha;
            Escolha = RespostasDesporto[a][2];
            return Escolha;
        }


        public String getEscolha3(int a) {
            String Escolha;
            Escolha = RespostasDesporto[a][3];
            return Escolha;
        }


    /**
     * Aqui faz-se o push da resposta correta
     */
        public String getRespostaDesportoCorreta (int a) {
            String resposta;
            resposta = RespostasDesportoCorretas[a];
            return resposta;
        }

    }

