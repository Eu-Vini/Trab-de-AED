package main;

import arvores.Arvore_AVL;
import arvores.Arvore_binaria;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import ui.Janela;

public class Main {

    public static void main(String[] args) {
        String nome_arquivo = "arquivo.txt";
        ArrayList<String> texto = lerTexto(nome_arquivo);

        System.out.println("AVL: " + (double) arvore_avl(texto)[0] / 1000000 + " ms");
        System.out.println("Binaria: " + (double) arvore_binaria(texto)[0] / 1000000 + " ms");

    }

    public static ArrayList<String> lerTexto(String nome_arquivo) {
        ArrayList<String> texto = new ArrayList<>();
        try {
            FileReader arquivo = new FileReader(nome_arquivo);
            BufferedReader ler_arquivo = new BufferedReader(arquivo);
            String linha = ler_arquivo.readLine();
            while (linha != null) {
                texto.add(linha);
                linha = ler_arquivo.readLine();
            }
            arquivo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return texto;
    }

    public static long[] arvore_avl(ArrayList<String> texto) {
        Arvore_AVL avl = new Arvore_AVL();
        long tempoInicial = System.nanoTime();
        for (int i = 0; i < texto.size(); i++) {
            String palavras[] = texto.get(i).split(" ");
            for (int j = 0; j < palavras.length; j++) {
                if (palavras[j].compareTo("") != 0 ) {
                    avl.inserir(palavras[j].toLowerCase());
                }
            }
        }
        long tempoFinal = System.nanoTime();
        long retorno[] = new long[1];
        retorno[0] = tempoFinal - tempoInicial;
        new Janela(avl);
        return retorno;
    }

    public static long[] arvore_binaria(ArrayList<String> texto) {
        Arvore_binaria binaria = new Arvore_binaria();
        long tempoInicial = System.nanoTime();
        for (int i = 0; i < texto.size(); i++) {
            String palavras[] = texto.get(i).split(" ");
            for (int j = 0; j < palavras.length; j++) {
                binaria.inserir(palavras[j], binaria.getRaiz());
            }
        }
        long tempoFinal = System.nanoTime();
        long retorno[] = new long[1];
        retorno[0] = tempoFinal - tempoInicial;
        return retorno;
    }
}
