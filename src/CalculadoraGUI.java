import javax.swing.*;
import java.awt.*;

public class CalculadoraGUI extends JFrame{
    private JTextField conta;
    private StringBuilder valorAtual = new StringBuilder();
    private float primeiroValor = 0;
    private float segundoValor = 0;
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

            JPanel painel = new JPanel(new GridLayout(4, 3, 5, 5));
            for (int i = 9; i >0 ; i--) {
                painel.add(CriarBotaoNumero(String.valueOf(i)));
            }
            JPanel paineloper = new JPanel( new GridLayout(5,1, 5, 5));
            paineloper.add(CriarBotaoOper("+", 0));
            paineloper.add(CriarBotaoOper("-", 1));
            paineloper.add(CriarBotaoOper("*", 2));
            paineloper.add(CriarBotaoOper("/", 3));
            painel.add(CriarBotaoVoltar());
            painel.add(CriarBotaoNumero("0"));
            painel.add(CriarBotaoigual());
            paineloper.add(CriarBotaoponto());
            add(painel, BorderLayout.CENTER);
            add(paineloper, BorderLayout.EAST);

        }



        private JButton CriarBotaoNumero(String texto)
        {
            JButton btn = new JButton(texto);
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
                primeiroValor = Float.parseFloat(valorAtual.toString());
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
                segundoValor = Float.parseFloat(valorAtual.toString());
                valorAtual.setLength(0);
                float resultado = 0;
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
        private JButton CriarBotaoVoltar () {
            JButton btnv = new JButton("<");
            btnv.addActionListener( e -> {
                if (!valorAtual.isEmpty()){
                    valorAtual.delete(valorAtual.length()-1,valorAtual.length());
                    conta.setText(valorAtual.toString());
                };
        });
            return btnv;
        }
        private JButton CriarBotaoponto () {
            JButton btnp = new JButton( ".");
            btnp.addActionListener(e -> {
                if(!valorAtual.toString().contains("."))
                {valorAtual.append(".");}
            });
            return  btnp;
        }
    }


