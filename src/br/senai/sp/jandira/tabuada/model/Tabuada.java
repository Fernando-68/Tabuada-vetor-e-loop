package br.senai.sp.jandira.tabuada.model;

import java.util.Scanner;

public class Tabuada {

       public int multiplicando;
       public int multiplicadorInicial;
       public int multiplicadorFinal;
        int apoio;
        int tamanho;
        int i;
        int produto;
       public String[] tabuada;

        public void obterInfomacoes(){
            Scanner leitor = new Scanner(System.in);

            System.out.print("Informe de qual número será a tabuada: ");
            multiplicando = leitor.nextInt();

            System.out.print("Informe qual será o número a qual iniciará a multiplicação: ");
            multiplicadorInicial = leitor.nextInt();

            System.out.print("Informe qual será o número a qual terminará a multiplicação: ");
            multiplicadorFinal = leitor.nextInt();

            calcularTabuada();
        }

        public String[] calcularTabuada(){
            if (multiplicadorInicial > multiplicadorFinal){
                apoio = multiplicadorInicial;
                multiplicadorInicial = multiplicadorFinal;
                multiplicadorFinal = apoio;
            }

            tamanho = multiplicadorFinal - multiplicadorInicial + 1;
            tabuada = new String[tamanho];

            i = 0;
            while(i < tamanho){
                produto = multiplicando * multiplicadorInicial;
                tabuada[i] = multiplicando + " × " + multiplicadorInicial + " = " + produto;
                multiplicadorInicial++;
                i++;
            }

            return tabuada;
            //exibirTabuada();
        }

        public void exibirTabuada(){
            System.out.println("");
            System.out.println("");
            System.out.println("RESULTADOS DA TABUADA");
            i = 0;
            while(i < tamanho){
                System.out.println(tabuada[i]);
                i++; // i = i + 1
            }
        }

}
