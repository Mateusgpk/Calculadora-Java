import javax.swing.*;
import java.awt.*;

public class CalculadoraGUI extends JFrame{
    private JTextField conta;
    private StringBuilder valorAtual = new StringBuilder();
    private int primeiroValor = 0;
    private int segundoValor = 0;
    private int operacao = -1; // 0=+, 1=-, 2=*, 3=/
    private Calculadora calc = new Calculadora();
    private int linha=0,dis=0;
        public CalculadoraGUI(){
            setTitle("001");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(300,400);

            conta = new JTextField();
            conta.setEditable(false);
            add(conta, BorderLayout.NORTH);

            JPanel painel = new JPanel();
            for (int i = 0; i < 10 ; i++) {
                painel.add(CriarBotaoNumero(String.valueOf(i)));
            }

            painel.add(CriarBotaoOper("+", 0));
            painel.add(CriarBotaoOper("-", 1));
            painel.add(CriarBotaoOper("*", 2));
            painel.add(CriarBotaoOper("/", 3));
            painel.add(CriarBotaoigual());
            add(painel, BorderLayout.CENTER);
        }



        private JButton CriarBotaoNumero(String texto)
        {
            JButton btn = new JButton(texto);
            if (texto == "0") {
                btn.setBounds(125 , 400, 50, 50);
            } else {
                btn.setBounds(75 + (dis * 50), 350-(linha*50), 50, 50);
                dis++;
                if (dis%3==0){
                    linha++;
                    dis=0;
                }
            }
            btn.addActionListener(e -> {

                valorAtual.append(texto);
                conta.setText(valorAtual.toString());
            });
            return btn;
        }
        private JButton CriarBotaoOper (String texto, int o) {
            JButton btdo = new JButton(texto);
            btdo.setBounds(100,100,50,50);
            btdo.addActionListener(e -> {
                operacao = o;
                if (valorAtual.isEmpty()) return;
                primeiroValor = Integer.parseInt(valorAtual.toString());
                valorAtual.setLength(0);
                conta.setText("");
            });
            return btdo;
        }
        private JButton CriarBotaoigual () {
            JButton btdi = new JButton("=");
            btdi.setBounds(100,100,50,50);
            btdi.addActionListener(e -> {
                if (valorAtual.isEmpty()) return;
                segundoValor = Integer.parseInt(valorAtual.toString());
                valorAtual.setLength(0);
                int resultado = 0;
                try {
                    switch (operacao) {
                        case 0 -> resultado=calc.somar(primeiroValor,segundoValor);
                        case 1 -> resultado=calc.subtrai(primeiroValor,segundoValor);
                        case 2 -> resultado=calc.multi(primeiroValor,segundoValor);
                        case 3 -> resultado=calc.div(primeiroValor,segundoValor);
                    }
                    conta.setText(String.valueOf(resultado));
                    primeiroValor = resultado;
                    valorAtual.setLength(0);
                } catch (ArithmeticException ex) {
                    conta.setText("Erro: " + ex.getMessage());
                    valorAtual.setLength(0);
                }
            });
            return btdi;
        }

}


