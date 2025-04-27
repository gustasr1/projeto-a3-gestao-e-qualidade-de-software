import javax.swing.JOptionPane;
public class Teste {

    public static void main(String[] args) {
double qtd, total = 0, subtotal;
        int codigo = -1;
        String valorDigitado;

        while (codigo != 0) {
            String menu = "----MENU----\n";
            menu += "1 - Cachorro quente\n";
            menu += "2 - Hamburgue\n";
            menu += "3 - Xburgue\n";
            menu += "4 - Egg\n";
            menu += "5 - Refri\n";
            menu += "0 - Sair\n";
            menu += "Digite sua opcao";
            valorDigitado = JOptionPane.showInputDialog(menu);

            if (valorDigitado.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Opcao invalida");
            } else {
                codigo = Integer.valueOf(valorDigitado);
                if( codigo!=0 ){
                valorDigitado = JOptionPane.showInputDialog("Informe a quantidade");
                qtd = Double.valueOf(valorDigitado);

                switch (codigo) {
                    case 1:
                        subtotal = qtd * 1.5;
                        total += subtotal;
                        break;
                    case 2:
                        subtotal = qtd * 2.0;
                        total += subtotal;
                        break;
                    case 3:
                        subtotal = qtd * 2.5;
                        total += subtotal;
                        break;
                    case 4:
                        subtotal = qtd * 3.0;
                        total += subtotal;
                        break;
                    case 5:
                        subtotal = qtd * 1.5;
                        total += subtotal;
                        break;
                    default:
                        subtotal = 0;
                        throw new AssertionError();
                }
                String texto = "Subtotal: " + subtotal + "\nTotal do pedido: " + total;
                JOptionPane.showMessageDialog(null, texto);
                }
            }
        }
    }
}
