package arvores;

import java.util.ArrayList;
/**
 *
 * @author vinicius
 */
public class Arvore_binaria {

    private No raiz;

    public void inserir(String chave, No raiz) {
        if (raiz == null) {
            raiz = new No();
            raiz.setChave(chave);
        } else {
            if (chave.compareTo(raiz.getChave()) < 0) {
                raiz = raiz.getEsquerda();
            } else if (chave.compareTo(raiz.getChave()) > 0) {
                raiz = raiz.getDireita();
            } else {
                raiz.quantidade++;
            }
        }
    }

    public ArrayList<No> inorder() {
        ArrayList<No> ret = new ArrayList<No>();
        inorder(raiz, ret);
        return ret;
    }

    final protected void inorder(No no, ArrayList<No> lista) {
        if (no == null) {
            return;
        }
        inorder(no.getEsquerda(), lista);
        lista.add(no);
        inorder(no.getDireita(), lista);
    }

    public void remover() {
    }

    private class No {

        private No esquerda;
        private No direita;
        private String chave;
        private int quantidade = 1;

        public No getEsquerda() {
            return esquerda;
        }

        @Override
        public String toString() {
            return (this.chave + ": " + quantidade);
        }

        public void setEsquerda(No esquerda) {
            this.esquerda = esquerda;
        }

        public No getDireita() {
            return direita;
        }

        public void setDireita(No direita) {
            this.direita = direita;
        }

        public String getChave() {
            return chave;
        }

        public void setChave(String chave) {
            this.chave = chave;
        }

    }

    public No getRaiz() {
        return raiz;
    }
    
}
