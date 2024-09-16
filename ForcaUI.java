import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForcaUI extends JFrame {
    private ForcaGame jogo;
    private JLabel labelPalavra;
    private JLabel labelErros;
    private JLabel labelTentativas;
    private JTextField campoLetra;
    private JButton botaoAdivinhar;
    private JPanel painelJogo;

    public ForcaUI() {
        configurarJanela();
        exibirTelaInicial();
    }

    private void configurarJanela() {
        setTitle("Jogo da Forca");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void exibirTelaInicial() {
        JPanel painelInicial = new JPanel(new GridLayout(3, 1));
        JLabel labelInstrucao = new JLabel("Insira a palavra para o jogo:", SwingConstants.CENTER);
        JTextField campoPalavra = new JTextField();
        JButton botaoIniciar = new JButton("Iniciar Jogo");

        painelInicial.add(labelInstrucao);
        painelInicial.add(campoPalavra);
        painelInicial.add(botaoIniciar);

        add(painelInicial, BorderLayout.CENTER);

        botaoIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String palavra = campoPalavra.getText().trim();
                if (!palavra.isEmpty()) {
                    jogo = new ForcaGame(palavra);
                    getContentPane().remove(painelInicial); // Remove a tela inicial
                    exibirTelaJogo(); // Exibe o jogo
                    revalidate();
                    repaint();
                }
            }
        });
    }

    private void exibirTelaJogo() {
        painelJogo = new JPanel(new BorderLayout());
        painelJogo.setBackground(new Color(240, 240, 240));

        labelPalavra = new JLabel(jogo.getPalavraEscondida(), SwingConstants.CENTER);
        labelPalavra.setFont(new Font("Arial", Font.BOLD, 36));

        labelErros = new JLabel("Erros: " + jogo.getLetrasErradas(), SwingConstants.CENTER);
        labelTentativas = new JLabel("Tentativas restantes: " + jogo.getTentativasRestantes(), SwingConstants.CENTER);

        campoLetra = new JTextField(1);
        campoLetra.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoAdivinhar = new JButton("Adivinhar");

        JPanel painelInferior = new JPanel();
        painelInferior.add(new JLabel("Letra:"));
        painelInferior.add(campoLetra);
        painelInferior.add(botaoAdivinhar);

        painelJogo.add(labelPalavra, BorderLayout.NORTH);
        painelJogo.add(labelErros, BorderLayout.CENTER);
        painelJogo.add(labelTentativas, BorderLayout.SOUTH);
        painelJogo.add(painelInferior, BorderLayout.PAGE_END);

        add(painelJogo, BorderLayout.CENTER);

        configurarListeners();
    }

    private void configurarListeners() {
        botaoAdivinhar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String letraTexto = campoLetra.getText();
                if (letraTexto.length() == 1) {
                    char letra = letraTexto.charAt(0);
                    boolean acertou = jogo.adivinharLetra(letra);

                    labelPalavra.setText(jogo.getPalavraEscondida());
                    labelErros.setText("Erros: " + jogo.getLetrasErradas());
                    labelTentativas.setText("Tentativas restantes: " + jogo.getTentativasRestantes());

                    if (jogo.venceu()) {
                        JOptionPane.showMessageDialog(null, "Parabéns, você venceu!");
                        resetarJogo();
                    } else if (jogo.perdeu()) {
                        JOptionPane.showMessageDialog(null, "Você perdeu! A palavra era: " + jogo.getPalavraEscondida());
                        resetarJogo();
                    }

                    campoLetra.setText("");
                }
            }
        });
    }

    private void resetarJogo() {
        getContentPane().remove(painelJogo);
        exibirTelaInicial();
        revalidate();
        repaint();
    }
}
