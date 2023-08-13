package atm.domain.com;
import java.io.FileWriter;
import java.util.Scanner;
import org.json.simple.JSONObject;

public class ATM {
    private double saldoTotal;
    public String caixaMessage;

   public ATM(double saldoinicial) {
       this.saldoTotal = saldoinicial;
   }
    public void abrirConta(String nome, double saldoInicial, String cpf, String senha) {
        Usuario user = new Usuario(nome, saldoInicial,cpf, senha);

        JSONObject jsonFile = new JSONObject();
        jsonFile.put("nome", user.getNomeCompleto()); // Mantém o nome completo sem alterações
        jsonFile.put("saldoInicial", user.getSaldoTotal());

        String nomeArquivo = user.getNomeCompleto().replace(" ", "-") + ".json";

        try (FileWriter file = new FileWriter(nomeArquivo)) {
            file.write(jsonFile.toJSONString());
            file.flush();
            System.out.println("Usuário criado e salvo em " + nomeArquivo);
        } catch (Exception e) {
            System.out.println("Erro ao salvar o usuário em um arquivo JSON.");
            e.printStackTrace();
        }
    }

    public void CaixaConsultarSaldo() {
        this.caixaMessage = "Saldo atual: R$ " + this.saldoTotal;
        System.out.println("**** " + caixaMessage + " ****");
        menuCaixa();
    }

    public void CaixaDepositar(double valorDeposito) {
        this.saldoTotal += valorDeposito;
        this.caixaMessage = "Depósito no valor de R$ " + valorDeposito + " realizado com sucesso";
        System.out.println("**** " + caixaMessage + " ****");
        menuCaixa();
    }
    public void CaixaSacarSaldo(double valorSaque) {
        if (this.saldoTotal < valorSaque) {
            this.caixaMessage = "Saldo insuficiente";
            System.out.println("**** " + caixaMessage + " ****" );
            menuCaixa();
        }else{
            this.saldoTotal -= valorSaque;
            this.caixaMessage = "Saque no valor de R$ " + valorSaque + " realizado com sucesso";
            System.out.println("**** " + caixaMessage + " ****");
            menuCaixa();
        }
    }

    public void menuCaixa(){
       Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha uma opção: ");
        System.out.println("| 1️⃣: Consultar saldo |");
        System.out.println("| 2️⃣: Deposito        |");
        System.out.println("| 3️⃣: Saque           |");
        System.out.println("| 4️: Abrir conta     |");
        System.out.println("| 5️⃣: Sair            |");

        try {
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    CaixaConsultarSaldo();
                    break;
                case 2:
                    System.out.println("Digite o valor a ser depositado: ");
                    double valorDeposito = scanner.nextDouble();
                    CaixaDepositar(valorDeposito);
                    break;
                case 3:
                    System.out.println("Digite o valor a ser sacado: ");
                    double valorSaque = scanner.nextDouble();
                    CaixaSacarSaldo(valorSaque);
                    break;
                case 4:
                    System.out.println("Abrindo conta...");
                    System.out.println("Digite seu CPF: ");
                    String cpf = scanner.next();
                    System.out.println("Digite o nome do usuário: ");
                    String nome = scanner.next();
                    System.out.println("Digite uma senha de 4 dígitos: ");
                    String senha = scanner.next();
                    this.abrirConta(nome, this.saldoTotal, cpf, senha);
                    menuCaixa();
                    break;
                case 5:
                    System.out.println("Saindo do sistema...");
                    System.out.println("Logout");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
                    menuCaixa();
                    break;
            }
        }catch (Exception e){
            System.out.println("Operação inválida, tente novamente.");
            menuCaixa();
        }
    }
    public static void main(String[] args) {
       ATM wictor = new ATM(0);
       wictor.menuCaixa();
    }
}