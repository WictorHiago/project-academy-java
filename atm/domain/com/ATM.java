package atm.domain.com;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONObject;

public class ATM {
    private double saldoTotal;
    private ArrayList<Usuario> arrayUsuarios = new ArrayList<>();
    public boolean usuarioLogado;
    public String caixaMessage;

    public ATM(double saldoInicial) {
        this.saldoTotal = saldoInicial;
    }
    public void criarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite seu CPF: ");
        String cpf = scanner.next();
        System.out.println("Digite seu nome: ");
        String nome = scanner.next();
        System.out.println("♾️ Digite uma senha de 4 dígitos: ");
        String senha = scanner.next();
        this.arrayUsuarios.add(new Usuario(cpf,saldoTotal, nome, senha));
        //System.out.println("✅ Usuário cadastrado com sucesso!");
        exibirMenuCaixa();
    }
    public void fazerLogin() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite seu CPF: ");
            String verificarCPF = scanner.next();
            System.out.println("Digite sua senha: ");
            String verificarSenha = scanner.next();
            System.out.println("⚠️ Verificando dados...");
            for (Usuario usuario : this.arrayUsuarios) {
                System.out.println("Iterating through users.");
                if (usuario.getCpf().equals(verificarCPF) && usuario.getSenha().equals(verificarSenha)) {
                    System.out.println("User data verified.");
                    usuarioLogado = true;
                    System.out.println("User logged in.");
                    break;
                }
                if(usuarioLogado == true) {
                    System.out.println("User logged in successfully!");
                    exibirMenuCaixa();
                } else {
                    System.out.println("Invalid data");
                    menuLogin();
                }
            }


        }catch (Exception e){
            System.out.println("⚠️ algo deu errado");
            menuLogin();
        }
    }

    public void menuLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha uma opção: ");
        System.out.println("-> 1️⃣: Entrar com CPF____ |");
        System.out.println("-> 2️⃣: Criar conta_______ |");
        System.out.println("-> 3️⃣: Sair_______________|");
        try {
            int opcao= scanner.nextInt();
            switch (opcao) {
                case 1:
                    fazerLogin();
                    break;
                case 2:
                    criarUsuario();
                    break;
                    case 3:
                    System.out.println("Saindo...");
                    usuarioLogado = false;
                    System.out.println("Sessão finalizada!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    menuLogin();
            }
        }catch (Exception e){
            System.out.println("⚠️ Opção inválida!");
            menuLogin();
        }

    }

    public void mostrarSaldo() {
        this.caixaMessage = "Saldo atual: R$ " + this.saldoTotal;
        System.out.println("💵 " + "🪙 " + caixaMessage + " 💵");
        exibirMenuCaixa();
    }

    public void realizarDeposito(double valorDeposito) {
        this.saldoTotal += valorDeposito;
        this.caixaMessage = "Valor depositado R$ " + valorDeposito + " realizado com sucesso";
        System.out.println("💵 " + "🪙 " + caixaMessage + " 💵");
        exibirMenuCaixa();
    }

    public void realizarSaque(double valorSaque) {
        if (this.saldoTotal < valorSaque) {
            this.caixaMessage = "Saldo insuficiente";
            System.out.println("💵 " + "🪙 " + caixaMessage + " 💵");
            exibirMenuCaixa();
        } else {
            this.saldoTotal -= valorSaque;
            this.caixaMessage = "Saque no valor de R$ " + valorSaque + " realizado com sucesso";
            System.out.println("💵 " + "🪙 " + caixaMessage + " 💵");
            exibirMenuCaixa();
        }
    }

    public void exibirMenuCaixa() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("♾️ ATM 24 horas | opções:♾️");
        System.out.println("-> 1️⃣: Consultar_saldo_◀️ |");
        System.out.println("-> 2️⃣: Deposito________◀️ |");
        System.out.println("-> 3️⃣: Saque___________◀️ |");
        System.out.println("-> 4️: Abrir_conta_____◀️ |");
        System.out.println("-> 5️⃣: Sair____________◀️ |");

        try {
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    mostrarSaldo();
                    exibirMenuCaixa();
                    break;
                case 2:
                    System.out.println("Digite o valor a ser depositado: ");
                    double valorDeposito = scanner.nextDouble();
                    realizarDeposito(valorDeposito);
                    exibirMenuCaixa();
                    break;
                case 3:
                    System.out.println("Digite o valor a ser sacado: ");
                    double valorSaque = scanner.nextDouble();
                    realizarSaque(valorSaque);
                    exibirMenuCaixa();

                    break;
                case 4:
                    System.out.println("error");
                    exibirMenuCaixa();
                    break;
                case 5:
                    System.out.println("Encerrando sessão...");
                    System.out.println("Sessão encerrada!");
                    menuLogin();
                    break;
                default:
                    System.out.println("Opção inválida");
                    exibirMenuCaixa();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Operação inválida, tente novamente.");
            exibirMenuCaixa();
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM(0);
//        atm.exibirMenuCaixa(); // inicia o meno principal
        atm.menuLogin();
    }
}
