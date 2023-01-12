package programa;

import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Classe guarda a informacao da empresa em listas e tem a maioria dos metodos que asseguram a gestao da empresa
 */
public class Aor_Autocarro implements Serializable {

    private ArrayList<Utilizador> utilizadores = new ArrayList<>();
    private ArrayList<Reserva> reservas = new ArrayList<>();
    private ArrayList<Reserva> reservasCanceladas = new ArrayList<>();
    private ArrayList<Reserva> reservasemEspera = new ArrayList<>();
    private ArrayList<Motorista> motoristas = new ArrayList<>();
    private ArrayList<Autocarro> autocarros = new ArrayList<>();
    private ArrayList<Pagamento> listaPagamentos = new ArrayList<>();

    private Utilizador userLogado;

    /**
     * Metodo adiciona reserva a lista de reservas
     *
     * @param reserva Reserva - Recebe os atributos da classe reserva
     */
    public void addReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    /**
     * Metodo adiciona pagamento a lista de pagamentos
     *
     * @param pagamento Pagamento - Recebe atributos da classe Pagamento
     */
    public void addPagamento(Pagamento pagamento) {
        listaPagamentos.add(pagamento);
    }

    /**
     * Metodo Retorna a lista de reservas
     *
     * @return reservas
     */
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    @Override
    public String toString() {
        return "Aor_Autocarro{" +
                "utilizadores=" + utilizadores +
                ", reservas=" + reservas +
                ", motoristas=" + motoristas +
                ", autocarros=" + autocarros +
                '}';
    }

    /**
     * Metodo Valida o email e a palavra chave recebidos e verifica se coincidem com os dados da lista de utilizadores
     *
     * @param email        String   - recebe um email do utilizador
     * @param palavraChave String - recebe uma palavra chave do utilizador
     * @return boolean - Valido ou invalido
     */
    public boolean validarRegisto(String email, String palavraChave) {
        boolean validar = false;
        for (Utilizador u : utilizadores) {
            if (u.getEmail().equals(email) && u.getPalavraChave().equals(palavraChave)) {
                validar = true;
            }
        }
        return validar;
    }

    /**
     * Metoodo Verificar se o utilizador e admnistrador ou cliente
     *
     * @param email        String   - recebe um email do utilizador
     * @param palavraChave String - recebe uma palavra chave do utilizador
     * @return String - o tipo de utilizador
     */
    public String verificarTipoUtilizador(String email, String palavraChave) {
        String utilizador = null;
        for (Utilizador user : utilizadores) {
            if (user.getEmail().equals(email) && user.getPalavraChave().equals(palavraChave)) {
                if (user instanceof Cliente) {
                    utilizador = "cliente";
                } else {
                    utilizador = "administrador";
                }
            }
        }
        return utilizador;
    }

    /**
     * Metodo Contar os clientes na lista de Utilizadores
     *
     * @return int - numero de clientes
     */
    public int contarCliente() {
        int cliente = 1;
        for (Utilizador user : utilizadores) {
            if (user instanceof Cliente) {
                cliente++;
            }
        }
        return cliente;
    }

    /**
     * Metodo Contar os administradores na lista de Utilizadores
     *
     * @return int - numero de administradores
     */
    public int contarAdministrador() {
        int administrador = 0;
        for (Utilizador user : utilizadores) {
            if (user instanceof Administrador) {
                administrador++;
            }
        }
        return administrador;
    }

    /**
     * Metodo Retorna o utilizador logado
     *
     * @return Utilizado - utilizado logado
     */
    public Utilizador getUserLogado() {
        return userLogado;
    }

    /**
     * Metodo altera o utilizador logado
     *
     * @param userLogado Utilizador
     */
    public void setUserLogado(Utilizador userLogado) {
        this.userLogado = userLogado;
    }

    /**
     * Metodo retorna utilizador que esta logado atraves do email
     *
     * @param email String - recebe email para verificar com a lista de utilizadores qual o utilizador logado
     * @return utilizador
     */
    public Utilizador utilizadorLogado(String email) {
        for (Utilizador user : utilizadores) {
            if (user.getEmail().equals(email)) {
                userLogado = user;
            }
        }
        return userLogado;
    }


    /**
     * Metodo verifica se existe duplicacao de nif, no momento do registo
     *
     * @param nif String - atributo de validacao
     * @return boolean - Valido ou invalido
     */

    public boolean verificarDuplicacaoNif(String nif) {
        boolean duplicado = false;

        for (Utilizador user : utilizadores) {
            if (user.getNif().equals(nif)) {
                duplicado = true;
            }
        }
        return duplicado;
    }

    /**
     * Metodo verifica se existe duplicacao de email, no momento do registo
     * Verificar duplicacao email boolean.
     *
     * @param email String - atributo de validacao
     * @return boolean - Valido ou invalido
     */
//Verificar se existe duplicação de registo de email, durante o registo de um novo utilizador
    public boolean verificarDuplicaçãoEmail(String email) {
        boolean duplicado = false;

        for (Utilizador user : utilizadores) {
            if (user.getEmail().equals(email)) {
                duplicado = true;
            }
        }
        return duplicado;
    }

    /**
     * Metodo que remove cliente, verifica e cancela viagens associadas a este cliente('Cancelar ReservasClienteRemovido')
     * e adiciona notificacao a listagem de notificacoes do cliente
     *
     * @param nif String - recebe o nif do cliente
     * @return boolean
     */
    public boolean removerCliente(String nif) {
        LocalDate hoje = LocalDate.now();
        Cliente removido;
        //pesquisa cliente com base no nif solicitado
        for (Utilizador utilizador : utilizadores) {
            if ((utilizador instanceof Cliente) && (utilizador.getNif().equals(nif))) {
                removido = (Cliente) utilizador;

                if (reservas.size() != 0) {
                    cancelarReservasdoClienteRemovido(nif);
                }


                //Criar e adicionar notificação de que o cliente foi removido,pelo que só terá acesso ao login

                Notificação clienteRemovido = new Notificação(removido.getEmail(), "ClienteRemovido",
                        "Foi removido da aplicação desta empresa.Pelo que não terá acesso ao seu menu Cliente",
                        hoje, false);
                removido.getNotificações().add(clienteRemovido);

                removido.setNome("inativo");
                removido.setMorada("inativo");
                removido.setTelefone("inativo");
                removido.setNif("inativo");


                return true;
            }
        }
        return false;
    }


    /**
     * Metodo cancela as reservas ativas(remove da lista de reservas e adiciona a lista das reservas canceladas)
     * , quando o cliente e removido pelo Administrador
     * Veifica se ha reservas em lista de espera ('verificarListaEspera')
     *
     * @param nif String - Identificador do cliente
     */
    public void cancelarReservasdoClienteRemovido(String nif) {//Cancelamento reservas, por cliente ter sido removido por um Administrador
        ArrayList<Reserva> reservasCopia = new ArrayList<>(reservas);
        // Verificar se este cliente tinha reservas pendentes
        for (Reserva res : reservasCopia) {
            //Identificar a reserva deste cliente, remover da lista de reservas e adicionar à lista de reservas canceladas
            if (res.getCliente().getNif().equals(nif)) {

                reservas.remove(res);
                reservasCanceladas.add(res);
                //Adicionar notificação de cancelamento de reserva à lista de notificações do cliente
                adicionarNotificaçãoCancelamento(res);

                //Verificar se existem clientes em lista de espera que possam ficar com esta reserva
                if (reservasemEspera.size() != 0) {
                    verificarListaEspera(res);
                }

            }
        }
    }


    /**
     * Metodo verifica se existe disponivel na empresa algum autocarro com capacidade para o numero pessoas solicitadas
     *
     * @param n_pessoas String - numero de pessoas para ocupar o autocarro
     * @return boolean
     */
    public boolean verificarAutocarroLotaçao(String n_pessoas) {
        int numeroPessoas = 0;
        try {
            numeroPessoas = Integer.parseInt(n_pessoas);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Preencha número pessoas");
        }
        boolean existe = false;
        for (Autocarro bus : autocarros) {
            if (numeroPessoas <= Integer.parseInt(bus.getLotacao())) {
                existe = true;
            }
        }
        return existe;
    }

    /**
     * Somatorio de todas as reservas(reservas ativas,reservas canceladas e reservas em espera).
     *
     * @return int - valor total das reservas
     */
    public int contarReservas() {

        int contaReservas = reservas.size() + reservasCanceladas.size() + reservasemEspera.size();

        if (contaReservas == 0) {
            contaReservas = 1;
        } else
            contaReservas++;
        return contaReservas;
    }

    /**
     * Metodo para efectuar reserva de Autocarro:consulta lista de reservas por autocarro, e verifica
     * a sua disponibilidade para o o numero de pessoas e periodo pretendido pelo cliente. Este metodo
     * identifica o autocarro e o motorista.
     *
     * @param cliente   Cliente - Info do cliente
     * @param dataReq   LocalDate - data do aluguer
     * @param nDias     String - numero dias de aluguer
     * @param nPessoas  String - numero pessoas para ocupar o autocarro
     * @param partida   String - Local de partida
     * @param destino   String - local de destino
     * @param distancia String - distancia
     * @return reserva Reserva -
     */

    public Reserva efetuarReservaAutocarro(Cliente cliente, LocalDate dataReq, String nDias, String nPessoas, String partida, String destino, String distancia) {
        String resultado = null;
        ArrayList<Autocarro> autDisponiveis = new ArrayList<>();
        ArrayList<Reserva> reservasAlvo = new ArrayList<>();//lista de reservas com potencial para serem canceladas
        ArrayList<Motorista> motoristasDisponíveis = new ArrayList<>(motoristas);
        int duração = Integer.parseInt(nDias);
        LocalDate datafim = dataReq.plusDays(duração);//calcula data fim do periodo de reserva pretendido
        LocalDate hoje = LocalDate.now(); //data de hoje
        int numeroPessoas = Integer.parseInt(nPessoas);
        long difDias;//diferença de dias
        Autocarro autocarro;
        Motorista motorista;
        Reserva reserva = new Reserva();

        //Ordenar Lista de autocarros por Lotação
        autocarros.sort(Comparator.comparing(Autocarro::getLotacao));

        for (Autocarro bus : autocarros) {
            autDisponiveis.add(bus);
        }

        if (reservas.size() == 0) {
            for (Autocarro bus : autocarros) {
                if (Integer.parseInt(bus.getLotacao()) >= numeroPessoas) {
                    autocarro = bus;
                    motorista = motoristasDisponíveis.get(0);
                    reserva = new Reserva(cliente, autocarro, motorista, hoje, dataReq, nDias, nPessoas,
                            partida, destino, distancia, "res" + contarReservas());
                    return reserva;
                }} }
        else {
            for (Autocarro bus : autocarros) {
                for (Reserva res : reservas) {
                    if (res.getAutocarro().equals(bus)) {
                        //Verificar se existe(para cada autocarro) alguma reserva com data (início ou fim) compreendida para o periodo pretendido
                        if ((res.getDataPartida().isEqual(dataReq) || res.getDataPartida().isEqual(datafim)
                                || res.getDataPartida().isAfter(dataReq) && res.getDataPartida().isBefore(datafim))
                                || (res.getDataFim().isEqual(dataReq) || res.getDataFim().isEqual(datafim)
                                || res.getDataFim().isAfter(dataReq) && res.getDataFim().isBefore(datafim))
                                || dataReq.isAfter(res.getDataPartida()) && dataReq.isBefore(res.getDataFim())) {
//adicionamos os autocarro reservados
                            autDisponiveis.remove(res.getAutocarro());
                            //elimina motoristas autocarros, para ficarmos no final apenas com os disponíveis
                            motoristasDisponíveis.remove(res.getMotorista());
                            //guarda as reservas que cumprem os requisitos para serem canceladas
                            reservasAlvo.add(res);
                        }
                    }
                }
            }
        }

        if (autDisponiveis.size()!=0){
            autocarro = autDisponiveis.get(0);
            motorista = motoristasDisponíveis.get(0);
            //Criar e adicionar reserva à lista de reservas da Empresa
            reserva = new Reserva(cliente, autocarro, motorista, hoje, dataReq, nDias, nPessoas,
                    partida, destino, distancia, "res" + contarReservas());
            return reserva;
        } else {
            //Verificar tipo Cliente que quer reservar autocarro:
            //Se for cliente "Normal"
            if (cliente.getTipoCliente().equals("Normal")) {
                reserva = new Reserva(cliente, null, null, hoje, dataReq, nDias, nPessoas,
                        partida, destino, distancia, "res" + contarReservas());
                return reserva;
            }
            //Cliente Premium tem prioridade na reserva apenas sob clientes "normais"
            else if (cliente.getTipoCliente().equals("Premium")) {
                for (Reserva alvo : reservasAlvo) {
                    //Verificar qual o tipo cliente da reserva já efetuada
                    if (alvo.getCliente().getTipoCliente().equals("Premium")) {
                        reserva = new Reserva(cliente, null, null, hoje, dataReq, nDias, nPessoas,
                                partida, destino, distancia, "res" + contarReservas());
                        return reserva;

                    } else if (alvo.getCliente().getTipoCliente().equals("Normal")) {
                        difDias = ChronoUnit.DAYS.between(hoje, alvo.getDataPartida());
                        //As reservas dos clientes "normais" só podem ser canceladas se:
                        if (difDias <= 2) {
                            reserva = new Reserva(cliente, null, null, hoje, dataReq, nDias, nPessoas,
                                    partida, destino, distancia, "res" + contarReservas());
                            return reserva;
                        } else {
                            //Verificar qual o cliente com a data de reserva mais recente
                            //Ordenar reservas por ordem decrescente relativamente à data de Reserva
                            reservasAlvo.sort(Comparator.comparing(Reserva::getDataReserva).reversed());
                            Reserva cancelada = reservasAlvo.get(0);
                            //Adicionar notificação à lista de notificações do Cliente com reserva cancelada
                            adicionarNotificaçãoCancelamento(cancelada);
                            //Remover da Lista de Reservas da Empresa a reserva cancelada
                            reservas.remove(reservasAlvo.get(0));
                            //Adicionar a reserva cancelada à Lista de Reservas Canceladas da Empresa
                            reservasCanceladas.add(reservasAlvo.get(0));
                            //O autocarro associado à reserva cancelada fica disponível para reserva
                            autocarro = reservasAlvo.get(0).getAutocarro();
                            //O motorista associado ao autocarro fica disponível
                            motorista = reservasAlvo.get(0).getMotorista();
                            //Criar e adicionar reserva à lista de reservas da Empresa
                            reserva = new Reserva(cliente, autocarro, motorista, hoje, dataReq, nDias, nPessoas,
                                    partida, destino, distancia, "res" + contarReservas());
                            return reserva;

                        }
                    }
                }
            }
        }
        return reserva;
    }


    /**
     * Metodo para adicionar as notificacoes quando e feito o cancelamento da reserva e verifica se ha
     * possibilidade de reembolso de acordo com plano de subscricao
     *
     * @param reserva Reserva - recebe a reserva para atribuir as notificacoes
     */
    public void adicionarNotificaçãoCancelamento(Reserva reserva) {
        //Adicionar notificação de cancelamento à lista de Notificações do Cliente

        LocalDate hoje = LocalDate.now(); //data de hoje
        long diferençaDias = ChronoUnit.DAYS.between(hoje, reserva.getDataPartida());//Calcular diferença de dias entre hoje e a data partida
        double reembolso;

        //Adicionar notificação à lista de Notificações do Cliente cancelado
        //Cliente "normal" tem direito a reembolso com penalização 50% se diferençaDias>7
        if (diferençaDias > 7 && reserva.getCliente().getTipoCliente().equals("Normal")) {
            reembolso = (reserva.getCusto() / 2.0);
            Notificação cancelamento = new Notificação(reserva.getCliente().getEmail(), "Cancelamento",
                    "A sua reserva do dia " + reserva.getDataPartida() + " foi cancelada. " +
                            "Irá ser reembolsado pelo valor de " + reembolso + "€, através de" + identificarTipoPagamento(reserva),
                    hoje, false);
            reserva.getCliente().getNotificações().add(cancelamento);

            //Cliente "premium" tem direito a reembolso na sua totalidade se diferençaDias>2
        } else if (diferençaDias > 2 && reserva.getCliente().getTipoCliente().equals("Premium")) {
            reembolso = reserva.getCusto();
            Notificação cancelamento = new Notificação(reserva.getCliente().getEmail(), "Cancelamento",
                    "A sua reserva do dia " + reserva.getDataPartida() + " foi cancelada. " +
                            "Irá ser reembolsado pelo valor de " + reembolso + "€, através de" + identificarTipoPagamento(reserva),
                    hoje, false);
            reserva.getCliente().getNotificações().add(cancelamento);
            //Caso não se verifiquem nenhuma das condições anteriores, o cliente não tem direito a reembolso
        } else {
            Notificação cancelamento = new Notificação(reserva.getCliente().getEmail(), "Cancelamento",
                    "A sua reserva do dia " + reserva.getDataPartida() +
                            " foi cancelada. De acordo, com o seu pacote de subscrição, não irá ter direito a reembolso", hoje, false);
            reserva.getCliente().getNotificações().add(cancelamento);
        }
    }

    /**
     * Metodo para identificar a ultima reserva do cliente que foi efectuada ,que aguarda o pagamento.
     *
     * @param cliente Cliente
     * @return reserva
     */
    public Reserva identificarReservaPagamento(Cliente cliente) {
        LocalDate hoje = LocalDate.now();
        Reserva resPagamento = new Reserva();

        for (Reserva res : reservas) {
            if (res.getCliente().equals(cliente) && res.getDataReserva().isEqual(hoje)) {
                int tamanho = reservas.size() - 1;
                resPagamento = reservas.get(tamanho);
                return resPagamento;
            }
        }
        return resPagamento;

    }

    /**
     * Metodo para identificar o tipo de pagamento usado numa reserva, com a finalidade de utilizar o mesmo metodo para
     * reembolso dessa reserva
     *
     * @param reserva Reserva - reserva cancelada
     * @return tipo de pagamento - descricao que ira complementar a notificacao de cancelamento que ira ser mostrada ao cliente
     */
    public String identificarTipoPagamento(Reserva reserva) {//Identificar tipo de pagamento, para efetuar reembolso

        String tipoPagamento = null;

        for (Pagamento p : listaPagamentos) {
            if (p.getReserva().equals(reserva)) {
                if (p instanceof Paypal) {
                    tipoPagamento = "conta paypal com email: " + ((Paypal) p).getEmail();
                } else if (p instanceof MB) {
                    tipoPagamento = "multibanco.Foi enviado um pedido do IBAN para o email " + p.getReserva().getCliente().getEmail();
                } else if (p instanceof Cartaocredito) {
                    tipoPagamento = " Cartão de Crédito nº" + ((Cartaocredito) p).getNumeroCartao() + " em nome de " + ((Cartaocredito) p).getNomeClienteCartao();
                }
            }
        }
        return tipoPagamento;
    }

    /**
     * Metodo para cancelar reserva em espera,no momento,em que o cliente e informado que a sua reserva vai ficar em espera e decidi
     * nao prosseguir.
     * Remove reserva da lista de espera.
     *
     * @param reserva Reserva - recebe reserva atribuida pelo metodo 'efetuarReservaAutocarro'
     *
     */
    public void cancelarReservaemEspera(Reserva reserva) {
        reservasemEspera.remove(reserva);
    }

    /**
     * Metodo para cancelar reserva cliente e as devidas notificacoes do cancelamento.Verifca se existem clientes em lista de espera
     * ('verificarListaEspera')
     *
     * @param cliente Cliente - informacao do cliente
     * @param id      String - id da reserva
     * @return String - descricao que sera exibida por pop up, ao cliente relativa se ha possibilidade
     * de reembolso
     */
    public String cancelarReservaCliente(Cliente cliente, String id) {

        double reembolso;
        String descrição = null;
        LocalDate hoje = LocalDate.now(); //data de hoje

        long diferençaDias;

        for (Reserva res : reservas) {
            //Identificar a reserva, remover da lista de reservas e adicionar à lista de reservas canceladas
            if (res.getCliente().equals(cliente) && res.getId().equals(id)) {
                reservas.remove(res);
                reservasCanceladas.add(res);
                //Verificar se existem clientes em lista de espera que possam ficar com esta reserva
                if (reservasemEspera.size() != 0) {
                    verificarListaEspera(res);
                }
                LocalDate data_aluguer = res.getDataPartida();
                diferençaDias = ChronoUnit.DAYS.between(hoje, data_aluguer);
                //Verificar tipo de subscrição do cliente e se tem direito a reembolso
                //Cliente "normal" tem direito a reembolso com penalização 50% se diferençaDias>7
                if (diferençaDias > 7 && res.getCliente().getTipoCliente().equals("Normal")) {
                    reembolso = (res.getCusto() / 2.0);
                    descrição = "A sua reserva do dia " + data_aluguer + " foi cancelada com sucesso.\n " +
                            "Irá ser reembolsado pelo valor de " + reembolso + "€, através de" + identificarTipoPagamento(res);
                    return descrição;
                    //Cliente "premium" tem direito a reembolso na sua totalidade se diferençaDias>2
                } else if (diferençaDias > 2 && res.getCliente().getTipoCliente().equals("Premium")) {
                    reembolso = res.getCusto();
                    descrição = "A sua reserva do dia " + data_aluguer + " foi cancelada com sucesso. " +
                            "Irá ser reembolsado pelo valor de " + reembolso + "€, através de" + identificarTipoPagamento(res);
                    return descrição;
                    //Caso não se verifiquem nenhuma das condições anteriores, o cliente não tem direito a reembolso
                } else {
                    descrição = "A sua reserva do dia " + data_aluguer + " foi cancelada com sucesso.\n De acordo, com o seu pacote de subscrição, não irá ter direito a reembolso.";
                    return descrição;
                }
            }
        }
        descrição = "Não existe nenhuma reserva com esse id em seu nome \n " +
                "Sugerimos que proceda à consulta das suas reservas para confirmar id da reserva.";

        return descrição;
    }


    /**
     * Metodo para verificar a lista de espera - metodo chamado por outros metodos associados ao cancelamento de reserva.
     * Verifica se algum autocarro associado a uma reserva cancelada, podera ser atribuido a uma das reservas em espera.
     * em caso afirmativo,cria notificacao do tipo ListaEspera e adiciona a lista de notificacoes do cliente
     *
     * @param reserva Reserva -reserva cancelada
     */
    public void verificarListaEspera(Reserva reserva) {

        ArrayList<Reserva> candidatas = new ArrayList<>();
        Notificação notificaçãoReserva = null;
        LocalDate hoje = LocalDate.now(); //data de hoje
        Autocarro autocarro = reserva.getAutocarro();//autocarro da reserva cancelada
        Motorista motorista = reserva.getMotorista();//motorista da reserva cancelada


        for (Reserva resEspera : reservasemEspera) {
            //verifica se o autocarro da reserva cancelada tem capacidade para o nº pessoas e período das reservas em espera
            if (Integer.parseInt(resEspera.getNumeroPessoas()) <= Integer.parseInt(reserva.getAutocarro().getLotacao())) {
                //Adiciona à lista de candidatas, as reservas que satisfazem os requisitos pretendidos
                candidatas.add(resEspera);
                //Verifica na lista de reservas, quais as reservas atribuidas ao autocarro da reserva cancelada
                for (Reserva res : reservas) {
                    if (res.getAutocarro().equals(reserva.getAutocarro())) {
                        //Verificar se existe alguma reserva com data (início ou fim) fora do periodo pretendido
                        LocalDate dataReq = resEspera.getDataPartida();// data partida da reserva em espera
                        LocalDate datafim = resEspera.getDataFim();// data fim da reserva em espera
                        if ((res.getDataPartida().isEqual(dataReq) || res.getDataPartida().isEqual(datafim)
                                || res.getDataPartida().isAfter(dataReq) && res.getDataPartida().isBefore(datafim))
                                || (res.getDataFim().isEqual(dataReq) || res.getDataFim().isEqual(datafim)
                                || res.getDataFim().isAfter(dataReq) && res.getDataFim().isBefore(datafim))) {
                            //Remove à lista de candidatas, as reservas que ocupam o período pretendido
                            candidatas.remove(resEspera);
                        }
                    }
                }
            }
        }
        //Criar notificações para informar clientes com potencial para efetuarem reserva
        if (candidatas.size() >= 1) {
            for (int i = 0; i < candidatas.size(); i++) {
                notificaçãoReserva = new Notificação(candidatas.get(i).getCliente().getEmail(), "ListaEspera", "A sua reserva em espera foi processada,proceda ao seu pagamento " +
                        ".Data de partida em " + candidatas.get(i).getDataPartida(), hoje, false);
                //Adiciona notificação à lista das notificaçóes do cliente
                candidatas.get(i).getCliente().getNotificações().add(notificaçãoReserva);
                //Adicionar autocarro e motorista às reservas Candidatas em lista de espera;
                candidatas.get(i).setAutocarro(autocarro);
                candidatas.get(i).setMotorista(motorista);

            }
        }
    }


    /**
     * Metodo para cancelar as reservas de clientes, por autocarro ter sido removido por um Administrador
     *Por intermedio do metodo 'adicionarNotificacaoCancelamento',sao adicionadas notificacoes do tipo Cancelament
     * @param matrícula String - matricula do Autocarro
     */
    public void cancelarReservasporAutocarro(String matrícula) {
        LocalDate hoje = LocalDate.now(); //data de hoje

        for (Reserva res : reservas) {
            if (res.getAutocarro().getMatricula().equals(matrícula)) {
                reservas.remove(res);
                reservasCanceladas.add(res);
                //Criar e adicionar notificação de que o autocarro foi removido
                Notificação autocarroRemovido = new Notificação(res.getCliente().getEmail(), "Cancelamento",
                        "Pedimos imensa desculpa pelo transtorno, mas o autocarro associado à sua reserva deixou de fazer parte da frota desta empresa.",
                        hoje, false);
                res.getCliente().getNotificações().add(autocarroRemovido);
                //Adicionar notificação de cancelamento de reserva à lista de notificações do cliente
                adicionarNotificaçãoCancelamento(res);
            }
        }
    }

    /**
     * Metodo para cancelar reservas de clientes, por motorista ter sido removido por um Administrador
     * Por intermedio do metodo 'adicionarNotificacaoCancelamento',sao adicionadas notificacoes do tipo Cancelamento
     *
     * @param email String - email do motorista
     */
//
    public void cancelarReservasporMotorista(String email) {
        LocalDate hoje = LocalDate.now(); //data de hoje

        for (Reserva res : reservas) {
            if (res.getMotorista().getEmail().equals(email)) {
                reservas.remove(res);
                reservasCanceladas.add(res);
                //Criar e adicionar notificação de que o autocarro foi removido
                Notificação motoristaRemovido = new Notificação(res.getCliente().getEmail(), "Cancelamento",
                        "Pedimos imensa desculpa pelo transtorno, mas o motorista associado à sua reserva encontra-se indisponível.",
                        hoje, false);
                res.getCliente().getNotificações().add(motoristaRemovido);
                //Adicionar notificação de cancelamento de reserva à lista de notificações do cliente
                adicionarNotificaçãoCancelamento(res);
            }
        }
    }

    /**
     * Metodo atribui reserva efetiva a cliente em lista de espera, quando faz login
     *
     * @param email String- email do cliente logado
     * @return nova reserva
     */
//
    public Reserva atribuirReservaListaEspera(String email) {

        Cliente logado = (Cliente) utilizadorLogado(email);//identificar cliente através do email
        Reserva novaReserva = null;
        Autocarro reservado = null;
        String descrição = null;
        int contador = 0;
        //Verificar qual a reserva e o autocarro atribuidos à sua reserva em lista de espera
        for (Reserva reserva : reservasemEspera) {
            if (reserva.getCliente().equals(logado)) {
                novaReserva = reserva;
                double custo=reserva.getCusto();
                novaReserva.setId(reserva.getId());
                novaReserva.setCusto(custo);
                reservado=reserva.getAutocarro();
                novaReserva.setAutocarro(reservado);
                novaReserva.setMotorista(reserva.getMotorista());
                reservas.add(novaReserva);
                //return novaReserva;
            }
        }

        //Caso tenha sido atribuido um autocarro ao cliente, verificamos se é o único cliente que tem esse autocarro atribuido,
        // ou se há mais clientes em lista de espera com o mesmo autocarro atribuido
        for (Reserva reservas : reservasemEspera) {
            if (reservas.getAutocarro().equals(reservado)) {
                contador++;
            }
        }
        //Caso o contador seja =1, quer dizer que só este cliente tem este autocarro atribuido, pelo que iremos
        //remover a sua reserva à lista de espera e adicioná-la à lista de reservas da empresa.
        if (contador == 1) {
            //Remover reserva deste cliente da lista de espera e colocar na lista de reservas da empresa
            reservasemEspera.remove(novaReserva);

        } else {
            //Caso seja maior que 1, consideramos que este foi o primeiro cliente a ler a notificação, ficando a reserva
            //deste autocarro para si. Nos restantes clientes iremos voltar a atribuir um valor "null" nos campos
            //relativos ao autocarro e motorista.
            reservasemEspera.remove(novaReserva);

            for (Reserva reservas : reservasemEspera) {
                if (reservas.getAutocarro().equals(reservado)) {
                    reservas.setAutocarro(null);
                    reservas.setMotorista(null);
                    //Removemos a notificação da lista de notificações dos restantes clietes que estavam
                    //em lista de espera para esta reserva
                    for (Notificação le : reservas.getCliente().getNotificações()) {
                        if (le.getTipoNotificação().equals("ListaEspera")) {
                            le.setLido(true);
                        }
                    }
                }
            }
        }
        return novaReserva;
    }


    /**
     * Metodo alterar as reservas
     *
     * @param reservas ArrayList - lista de reservas
     */
    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    /**
     * Metodo retornar a lista de reservas canceladas
     *
     * @return reservas canceladas
     */
    public ArrayList<Reserva> getReservasCanceladas() {
        return reservasCanceladas;
    }

    /**
     * Metodo retornar a lista reservas em espera
     *
     * @return reservas em espera
     */
    public ArrayList<Reserva> getReservasemEspera() {
        return reservasemEspera;
    }


    /**
     * Retorna a lista de motoristas
     *
     * @return motoristas
     */
    public ArrayList<Motorista> getMotoristas() {
        return motoristas;
    }

    /**
     * Altera a lista de motoristas
     *
     * @param motoristas
     */
    public void setMotoristas(ArrayList<Motorista> motoristas) {
        this.motoristas = motoristas;
    }

    /**
     * Retorna a lista de Autocarros
     *
     * @return autocarros
     */
    public ArrayList<Autocarro> getAutocarros() {
        return autocarros;
    }

    /**
     * Metodo Altera lista de autocarros
     *
     * @param autocarros Autocarro
     */
    public void setAutocarros(ArrayList<Autocarro> autocarros) {
        this.autocarros = autocarros;
    }


    /**
     * Retorna a lista de Utilizadores
     *
     * @return utilizadores
     */
    public ArrayList<Utilizador> getUtilizadores() {
        return utilizadores;
    }


    /**
     * Metodo para retornar o motorista removido
     *
     * @param email String - email do motorista
     * @return Motorista
     */
//========================================================================================
    //Metodos MOTORISTA
    public Motorista removerMotorista(String email) {
        Motorista motoristaRemovido = null;
        for (Motorista motorista : motoristas) {
            if ((motorista.getEmail().equals(email))) {
                motoristaRemovido = motorista;
            } else {
                motoristaRemovido = null;
            }
        }
        return motoristaRemovido;
    }

    /**
     * Metodo verificar a duplicacao email motorista boolean.
     *
     * @param email String - Motorista
     * @return boolean
     */
    public boolean verificarDuplicaçãoEmailMotorista(String email) {
        boolean duplicado = false;

        for (Motorista motorista : motoristas) {
            if (motorista.getEmail().equals(email)) {
                duplicado = true;
            }
        }
        return duplicado;
    }

    /**
     * Metodo verifica a duplicacao de matricula
     *
     * @param matricula String - recebe matricula do autocarro
     * @return boolean
     */
    public boolean verificarDuplicacaoMatricula(String matricula) {
        for (Autocarro autocarro : autocarros) {
            if (autocarro.getMatricula().equals(matricula)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo retorna o cliente na lista de utilizadores
     *
     * @param nif String - nif do cliente
     * @return Cliente
     */
    public Utilizador getCliente(String nif) {
        for (Utilizador cliente : utilizadores) {
            if (cliente instanceof Cliente) {
                if ((cliente.getNif().equals(nif))) {
                    return cliente;
                }
            }

        }
        return null;
    }

    /**
     * Metodo conta o numero de motoristas na lista
     *
     * @return int
     */
    public int contarMotorista() {
        int motorista = 0;
        for (Motorista moto : motoristas) {
            motorista++;
        }
        return motorista;
    }

    /**
     * Metodo conta o numero de autocarros na lista
     *
     * @return int
     */
    public int contarAutocarro() {
        int autocarro = 0;
        for (Autocarro auto : autocarros) {
            autocarro++;
        }
        return autocarro;
    }

    /**
     * Metodo retorna administrador dentro da lista de utilizadores
     *
     * @param email String - email do administrador
     * @return Administrador
     */
    public Utilizador getAdministrador(String email) {
        for (Utilizador administrador : utilizadores) {
            if ((administrador.getEmail().equals(email))) {
                return administrador;
            }
        }
        return null;
    }

    /**
     * Metodo para alterar a palavra chave
     *
     * @param email                String - recebe o email do utilizador logado
     * @param palavraChaveAtual    String - recebe a palavra chave atual para validar a palavra chave do utilizador
     * @param novaPalavraChave     String - recebe a nova palavra chave
     * @param confirmePalavraChave String - recebe a nova palavra chave para confirmação
     */
    public void alterarPalavraChave(String email, String palavraChaveAtual,
                                    String novaPalavraChave, String confirmePalavraChave) {
        if (validarRegisto(email, palavraChaveAtual)) {
            if (novaPalavraChave.equals(confirmePalavraChave)) {
                for (Utilizador cliente : getUtilizadores()) {
                    if (cliente.getEmail().equals(email)) {
                        cliente.setPalavraChave(novaPalavraChave);
                        JOptionPane.showMessageDialog(null, "Palavra chave alterado com sucesso");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Palavra chave não coincidem");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Palavra chave atual incorrecta");
        }
    }


    /**
     * Metodo retorna o autocarro associado a uma matricula
     *
     * @param matricula String - matricula do autocarro
     * @return Autocarro
     */
    public Autocarro getAutocarro(String matricula) {
        for (Autocarro autocarro : autocarros) {
            if ((autocarro.getMatricula().equals(matricula))) {
                return autocarro;
            }
        }
        return null;
    }

    /**
     * Metodo retorna o motorista associado a um email
     *
     * @param email String - email do motorista
     * @return Motorista
     */
    public Motorista getMotorista(String email) {
        for (Motorista motorista : motoristas) {
            if ((motorista.getEmail().equals(email))) {
                return motorista;
            }
        }
        return null;
    }

    /**
     * Metodo para identificar o autocarro mais requisitado
     *
     * @return String - Matricula do autocarro
     */
    public String matriculaMaisRequisitado() {
        String elementoMaisFrequente = "";
        int contador = 0;
        for (Reserva reserva1 : reservas) {
            int tempContador = 0;
            for (Reserva reserva2 : reservas) {
                if (reserva2.getAutocarro().getMatricula().equals(reserva1.getAutocarro().getMatricula())) {
                    tempContador++;
                }
            }
            if (tempContador > contador) {
                elementoMaisFrequente = reserva1.getAutocarro().getMatricula();
                contador = tempContador;
            }
        }
        return elementoMaisFrequente;
    }


    /**
     * Metodo identificar o cliente com mais reservas efectuadas
     *
     * @return String - nome do cliente
     */
    public String clienteComMaisReservas() {
        String elementoMaisFrequenteCliente = "";
        int contadorCliente = 0;
        for (Reserva cliente1 : reservas) {
            int tempContadorCliente = 0;
            for (Reserva cliente2 : reservas) {
                if (cliente2.getCliente().getNome().equals(cliente1.getCliente().getNome())) {
                    tempContadorCliente++;
                }
            }
            if (tempContadorCliente > contadorCliente) {
                elementoMaisFrequenteCliente = cliente1.getCliente().getNome();
                contadorCliente = tempContadorCliente;
            }
        }
        return elementoMaisFrequenteCliente;
    }
}










