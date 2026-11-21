package trabalhofinaljava;

import java.util.ArrayList;
import java.util.List;

// Armazenamento centralizado em memoria.
// Todas as telas usam esta classe para guardar e buscar clientes e veiculos.
public class Repositorio {

    private static List<Cliente> clientes = new ArrayList<Cliente>();
    private static List<Veiculo> veiculos = new ArrayList<Veiculo>();

    // ---------- CLIENTES ----------

    public static void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public static void removerCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    public static List<Cliente> getClientes() {
        return clientes;
    }

    // Retorna o cliente com o CPF informado ou null se nao existir
    public static Cliente buscarClientePorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    // Retorna true se ja existe cliente com esse CPF (evita duplicados)
    public static boolean cpfJaCadastrado(String cpf) {
        return buscarClientePorCpf(cpf) != null;
    }

    // Retorna true se o cliente tem algum veiculo LOCADO no nome dele.
    // Usada na Tela 1 para bloquear a exclusao.
    public static boolean clientePossuiLocacaoAtiva(Cliente cliente) {
        for (Veiculo v : veiculos) {
            if (v.getEstado() == Estado.LOCADO && v.getLocacao().getCliente() == cliente) {
                return true;
            }
        }
        return false;
    }

    // ---------- VEICULOS ----------
    // A lista e do tipo Veiculo para usar polimorfismo
    // (Automovel, Motocicleta e Van ficam todos na mesma lista).

    public static void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public static List<Veiculo> getVeiculos() {
        return veiculos;
    }
}