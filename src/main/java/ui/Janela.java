package ui;

import arvores.Arvore_AVL;
import arvores.No;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author vinicius
 */
public class Janela extends JFrame {

    //receber a arvore como parametro
    public Janela(Arvore_AVL arvore) {
        initComponents(arvore);
    }

    public void initComponents(Arvore_AVL arvore) {
        Painel panel = new Painel(arvore);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                repaint();
            }
        });
        scrollPane.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                repaint();
            }
        });
        // calcular quanto elementos tem na arvore para ver qual será a largura da base
        // e setar a raiz para o meio, para que todos elementos sejam visiveis.
        this.setSize(1000, 1000);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(scrollPane);
        this.setVisible(true);
    }

    class Painel extends JPanel {

        int largura, altura;
        Arvore_AVL arvore;

        public Painel(Arvore_AVL arvore) {
            int altura = arvore.altura(arvore.getRaiz());
            this.setPreferredSize(new Dimension((2 * (int) Math.pow(2, altura) - 1) * 100, 5000));
            this.largura = (2 * (int) Math.pow(2, altura) - 1) * 100;
            this.altura = 5000;
            this.arvore = arvore;
            this.setBackground(Color.white);
            System.out.println(altura);
            System.out.println((2 * Math.pow(2, altura) - 1) * 100);

        }

        @Override
        protected void paintComponent(Graphics g) {
            g.fillRect((largura / 2) - 50, 100, 100, 15);
            g.setColor(Color.red);
            g.drawString(arvore.getRaiz().getChave(),(largura / 2) - 50+5,100+10);
            g.setColor(Color.black);
            teste(g, (largura / 2) - 50, 100, this.arvore.altura(arvore.getRaiz()), (int) (100 * (Math.pow(2, this.arvore.altura(arvore.getRaiz())))), this.arvore.getRaiz());
        }

        //n = altura da arvore
        // distancia x = 100 * 2^n
        public void teste(Graphics g, int x, int y, int n, int distanciax, No no) {
            if (n > 0) {

                n--;

                distanciax /= 2;
                if (no.getEsquerda() != null) {
                    g.fillRect(x - distanciax, y + 100, 100, 15);
                    g.drawLine(x, y + 15, x - distanciax + 50, y + 100);
                    g.setColor(Color.red);
                    g.drawString(no.getEsquerda().getChave(), x - distanciax+5, y + 110);
                    g.setColor(Color.black);
                }
                if (no.getDireita() != null) {
                    g.fillRect(x + distanciax, y + 100, 100, 15);
                    g.drawLine(x + 100, y + 15, x + distanciax + 50+5, y + 100);
                    g.setColor(Color.red);
                    g.drawString(no.getDireita().getChave(), x + distanciax+5, y + 110);
                    g.setColor(Color.black);
                }

                if (no.getEsquerda() != null) {
                    teste(g, x - distanciax, y + 100, n, distanciax, no.getEsquerda());
                }
                if (no.getDireita() != null) {
                    teste(g, x + distanciax, y + 100, n, distanciax, no.getDireita());
                }

            }
        }
    }
}
