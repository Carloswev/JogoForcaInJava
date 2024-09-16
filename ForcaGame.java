import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ForcaGame {
    private String palavraSecreta;
    private char[] palavraEscondida;
    private Set<Character> letrasErradas = new HashSet<>();
    private int tentativasRestantes = 6;

    public ForcaGame(String palavra) {
        this.palavraSecreta = palavra.toLowerCase();
        this.palavraEscondida = new char[palavra.length()];
        Arrays.fill(palavraEscondida, '_');
    }

    public boolean adivinharLetra(char letra) {
        letra = Character.toLowerCase(letra);
        if (letrasErradas.contains(letra) || new String(palavraEscondida).indexOf(letra) != -1) {
            return false;
        }

        boolean acertou = false;
        for (int i = 0; i < palavraSecreta.length(); i++) {
            if (palavraSecreta.charAt(i) == letra) {
                palavraEscondida[i] = letra;
                acertou = true;
            }
        }

        if (!acertou) {
            letrasErradas.add(letra);
            tentativasRestantes--;
        }

        return acertou;
    }

    public String getPalavraEscondida() {
        return new String(palavraEscondida);
    }

    public Set<Character> getLetrasErradas() {
        return letrasErradas;
    }

    public int getTentativasRestantes() {
        return tentativasRestantes;
    }

    public boolean venceu() {
        return new String(palavraEscondida).equals(palavraSecreta);
    }

    public boolean perdeu() {
        return tentativasRestantes <= 0;
    }
}
