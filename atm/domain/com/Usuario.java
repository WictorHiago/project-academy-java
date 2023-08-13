package atm.domain.com;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nomeCompleto;
    private String cpf;
    private String senha;
    private double saldoTotal;
    public Usuario(String nomeCompleto, double saldoTotal, String cpf, String senha){
        this.nomeCompleto = nomeCompleto;
        this.saldoTotal = saldoTotal;
        this.cpf = cpf;
        this.senha = senha;
    }
    public String getNomeCompleto() {
    return nomeCompleto;
    }
    public double getSaldoTotal() {
        return saldoTotal;
    }
    public String getCpf() {
        return cpf;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha (String senha) {
        this.senha = senha;
    }
}
