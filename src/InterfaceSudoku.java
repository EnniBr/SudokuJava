import javax.swing.*;
import java.awt.*;

public class InterfaceSudoku {

    public static class JanelaPrincipal extends JFrame {
        private JTextField[][] campos = new JTextField[9][9];

        public JanelaPrincipal() {
            setTitle("Meu Jogo de Sudoku");
            setSize(600, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel painelPrincipal = new JPanel(new GridLayout(3, 3));

            for (int blocoLinha = 0; blocoLinha < 3; blocoLinha++) {
                for (int blocoColuna = 0; blocoColuna < 3; blocoColuna++) {

                    JPanel caixa = new JPanel(new GridLayout(3, 3));
                    caixa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    painelPrincipal.add(caixa);

                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            int linha = blocoLinha * 3 + i;
                            int coluna = blocoColuna * 3 + j;

                            JTextField campo = new JTextField();
                            campo.setHorizontalAlignment(JTextField.CENTER);
                            campo.setFont(new Font("Arial", Font.BOLD, 20));

                            campos[linha][coluna] = campo;
                            caixa.add(campo);
                        }
                    }
                }
            }

            add(painelPrincipal);

            int[][] valoresIniciais = {
                    {0, 0, 5}, {0, 1, 3}, {0, 2, 4}, {0, 3, 6}, {0, 4, 7}, {0, 5, 8}, {0, 6, 9}, {0, 7, 1}, {0, 8, 2},
                    {1, 0, 6}, {1, 1, 7}, {1, 2, 2}, {1, 3, 1}, {1, 4, 9}, {1, 5, 5}, {1, 6, 3}, {1, 7, 4}, {1, 8, 8},
                    {2, 0, 1}, {2, 1, 9}, {2, 2, 8}, {2, 3, 3}, {2, 4, 4}, {2, 5, 2}, {2, 6, 5}, {2, 7, 6}, {2, 8, 7},

                    {3, 0, 8}, {3, 1, 5}, {3, 2, 9}, {3, 3, 7}, {3, 4, 6}, {3, 5, 1}, {3, 6, 4}, {3, 7, 2}, {3, 8, 3},
                    {4, 0, 4}, {4, 1, 2}, {4, 2, 6}, {4, 3, 8}, {4, 4, 5}, {4, 5, 3}, {4, 6, 7}, {4, 7, 9}, {4, 8, 1},
                    {5, 0, 7}, {5, 1, 1}, {5, 2, 3}, {5, 3, 9}, {5, 4, 2}, {5, 5, 4}, {5, 6, 8}, {5, 7, 5}, {5, 8, 6},

                    {6, 0, 9}, {6, 1, 6}, {6, 2, 1}, {6, 3, 5}, {6, 4, 3}, {6, 5, 7}, {6, 6, 2}, {6, 7, 8}, {6, 8, 4},
                    {7, 0, 2}, {7, 1, 8}, {7, 2, 7}, {7, 3, 4}, {7, 4, 1}, {7, 5, 9}, {7, 6, 6}, {7, 7, 3}, {7, 8, 5},
                    {8, 0, 3}, {8, 1, 4}, {8, 2, 5}, {8, 3, 2}, {8, 4, 8}, {8, 5, 6}, {8, 6, 1}, {8, 7, 7}
            };

            for (int[] pos : valoresIniciais) {
                int linha = pos[0];
                int coluna = pos[1];
                int valor = pos[2];

                campos[linha][coluna].setText(String.valueOf(valor));
                campos[linha][coluna].setEditable(false);
                campos[linha][coluna].setBackground(new Color(220, 220, 220));
            }

            Timer verificador = new Timer(500, e -> verificarCampos());
            verificador.start();

            JButton btnLimpar = new JButton("Limpar");
            btnLimpar.addActionListener(e -> {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (campos[i][j].isEditable()) {
                            campos[i][j].setText("");
                            campos[i][j].setBackground(Color.WHITE);
                        }
                    }
                }
            });
            JPanel painelInferior = new JPanel();
            painelInferior.add(btnLimpar);
            add(painelInferior, BorderLayout.SOUTH);
        }

        private void verificarCampos() {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    campos[i][j].setBackground(Color.WHITE);
                }
            }

            for (int i = 0; i < 9; i++) {
                boolean[] vistos = new boolean[10];
                for (int j = 0; j < 9; j++) {
                    String texto = campos[i][j].getText();
                    if (!texto.isEmpty()) {
                        try {
                            int valor = Integer.parseInt(texto);
                            if (valor < 1 || valor > 9 || vistos[valor]) {
                                campos[i][j].setBackground(Color.PINK);
                            } else {
                                vistos[valor] = true;
                            }
                        } catch (NumberFormatException e) {
                            campos[i][j].setBackground(Color.PINK);
                        }
                    }
                }
            }

            for (int j = 0; j < 9; j++) {
                boolean[] vistos = new boolean[10];
                for (int i = 0; i < 9; i++) {
                    String texto = campos[i][j].getText();
                    if (!texto.isEmpty()) {
                        try {
                            int valor = Integer.parseInt(texto);
                            if (valor < 1 || valor > 9 || vistos[valor]) {
                                campos[i][j].setBackground(Color.PINK);
                            } else {
                                vistos[valor] = true;
                            }
                        } catch (NumberFormatException e) {
                            campos[i][j].setBackground(Color.PINK);
                        }
                    }
                }
            }

            for (int blocoLinha = 0; blocoLinha < 3; blocoLinha++) {
                for (int blocoColuna = 0; blocoColuna < 3; blocoColuna++) {
                    boolean[] vistos = new boolean[10];
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            int linha = blocoLinha * 3 + i;
                            int coluna = blocoColuna * 3 + j;
                            String texto = campos[linha][coluna].getText();
                            if (!texto.isEmpty()) {
                                try {
                                    int valor = Integer.parseInt(texto);
                                    if (valor < 1 || valor > 9 || vistos[valor]) {
                                        campos[linha][coluna].setBackground(Color.PINK);
                                    } else {
                                        vistos[valor] = true;
                                    }
                                } catch (NumberFormatException e) {
                                    campos[linha][coluna].setBackground(Color.PINK);
                                }
                            }
                        }
                    }
                }
            }

            boolean completoECorreto = true;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    String texto = campos[i][j].getText();
                    if (texto.isEmpty() || campos[i][j].getBackground().equals(Color.PINK)) {
                        completoECorreto = false;
                    }
                }
            }

            if (completoECorreto) {
                JOptionPane.showMessageDialog(this, "Parabéns! Você completou o Sudoku corretamente!", "Vitória", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        }

        public static void main(String[] args) {
            JanelaPrincipal janela = new JanelaPrincipal();
            janela.setVisible(true);
        }
    }
}
