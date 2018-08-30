import java.math.BigDecimal; 
import java.util.List;
import java.util.ArrayList;

class Conta {

    public BigDecimal saldo;
    public int numeroConta;
    protected double taxaMult = 1;

    public Conta(int numeroConta) {
        this.numeroConta = numeroConta;
        this.saldo = new BigDecimal(0);
    }

    public void deposita(double valor) {
        deposita(new BigDecimal(valor));
    }

    public void deposita(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    public BigDecimal retira(double valor) {
        return retira(new BigDecimal(valor));
    }

    public BigDecimal retira(BigDecimal valor) {
        if (valor.compareTo(saldo) == 1) {
            System.out.println("Valor desejado maior do que o saldo da conta " + numeroConta);
            return new BigDecimal(0);
        }

        this.saldo = this.saldo.subtract(valor);
        System.out.println("Valor removido com sucesso da conta " + numeroConta);
        return valor;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void atualiza(double taxa) {
        this.saldo = this.saldo.multiply(new BigDecimal(taxa * taxaMult));
    }

}

class ContaCorrente extends Conta {

    public ContaCorrente(int numeroConta) {
        super(numeroConta);
        taxaMult = 1.1;
    }

}

class ContaPoupanca extends Conta {

    public ContaPoupanca(int numeroConta) {
        super(numeroConta);
        taxaMult = 1.2;
    }

}

class Cliente {
    public String nome;
    public String CPF;
    private List<Conta> contas;

    public Cliente(String nome, String CPF) {
        this.nome = nome;
        this.CPF = CPF;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void addConta(Conta conta) {
        if (contas == null) {
            this.contas = new ArrayList<Conta>();
        }

        this.contas.add(conta);
    }

    public void imprimeCliente() {
        System.out.println("Nome do cliente: " + nome);
        System.out.println("CPF do cliente: " + CPF);

        for (Conta conta: contas) {
            System.out.println("Número da conta: " + conta.numeroConta);
            System.out.println("Saldo da conta: " + conta.saldo.toString());
        }

    }
}

class Programa {

    public static int numeroUltimaConta = 0;

    public static void main(String args[]) {
        Cliente cliente = new Cliente("Didonet", "123456987-45");

        System.out.println("Criando conta 1");
        numeroUltimaConta += 1;
        Conta conta = new ContaCorrente(numeroUltimaConta);
        conta.deposita(1000);
        cliente.addConta(conta);

        System.out.println("Criando conta 2");
        numeroUltimaConta += 1;
        conta = new ContaPoupanca(numeroUltimaConta);
        conta.deposita(2000);
        cliente.addConta(conta);

        System.out.println("Criando conta 3\n");
        numeroUltimaConta += 1;
        conta = new ContaPoupanca(numeroUltimaConta);
        conta.deposita(541);
        cliente.addConta(conta);

        cliente.imprimeCliente();

        System.out.println("\nAtualizando as contas do cliente:\n");

        for (Conta contaCliente: cliente.getContas()) {
            contaCliente.atualiza(1.4);
        }

        cliente.imprimeCliente();
    }
}