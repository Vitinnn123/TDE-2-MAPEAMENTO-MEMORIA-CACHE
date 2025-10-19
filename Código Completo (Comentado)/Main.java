public class Main {

    // FIFO
    public static void fifo(String nomeSequencia, int[] sequencia, int tamanhoSequencia, int quantidadeQuadros) {
        int[] memoria = new int[quantidadeQuadros];
        int quantidadeOcupada = 0;
        int posicaoRemover = 0;
        int faltas = 0;

        System.out.println("\n" + nomeSequencia + " (FIFO)");
        int indice = 0;
        while (indice < tamanhoSequencia) {
            int pagina = sequencia[indice];
            System.out.print("Acessa página " + pagina + " > ");

            boolean encontrada = false;
            int posicao = 0;
            // Verifica se a página já está na memória
            while (posicao < quantidadeOcupada) {
                if (memoria[posicao] == pagina) {
                    encontrada = true;
                    break;
                }
                posicao++;
            }

            if (encontrada) {
                System.out.println("Já está na memória.");
            } else {
                faltas++;

                // Se ainda tiver espaço na memória
                if (quantidadeOcupada < quantidadeQuadros) {
                    memoria[quantidadeOcupada] = pagina;
                    quantidadeOcupada++;
                    System.out.print("FALTA #" + faltas + " | Adiciona > ");
                } else {
                    // Se estiver cheia, remove o mais antigo
                    int removida = memoria[posicaoRemover];
                    memoria[posicaoRemover] = pagina;
                    posicaoRemover++;
                    if (posicaoRemover == quantidadeQuadros) posicaoRemover = 0;
                    System.out.print("FALTA #" + faltas + " | Saiu " + removida + ", entrou " + pagina + " > ");
                }

                mostrarMemoria(memoria, quantidadeOcupada);
            }

            indice++;
        }
        System.out.println("Total de faltas: " + faltas);
    }

    // LRU
    public static void lru(String nomeSequencia, int[] sequencia, int tamanhoSequencia, int quantidadeQuadros) {
        int[] memoria = new int[quantidadeQuadros];
        int[] uso = new int[quantidadeQuadros]; // Guarda o tempo de uso de cada página
        int quantidadeOcupada = 0;
        int faltas = 0;
        int tempo = 0; // Contador pra saber qual foi usada mais recentemente

        System.out.println("\n" + nomeSequencia + " (LRU)");
        int indice = 0;
        while (indice < tamanhoSequencia) {
            int pagina = sequencia[indice];
            tempo++;
            System.out.print("Acessa página " + pagina + " > ");

            int posicaoEncontrada = -1;
            int posicao = 0;
            // Verifica se a página já está na memória
            while (posicao < quantidadeOcupada) {
                if (memoria[posicao] == pagina) {
                    posicaoEncontrada = posicao;
                    break;
                }
                posicao++;
            }

            if (posicaoEncontrada != -1) {
                uso[posicaoEncontrada] = tempo; // atualiza o tempo de uso
                System.out.println("Página já estava na memória.");
            } else {
                faltas++;
                // Se ainda houver espaço, adiciona normalmente
                if (quantidadeOcupada < quantidadeQuadros) {
                    memoria[quantidadeOcupada] = pagina;
                    uso[quantidadeOcupada] = tempo;
                    quantidadeOcupada++;
                    System.out.print("FALTA #" + faltas + " | Adiciona > ");
                } else {
                    // Procura a página menos usada pra substituir
                    int menorTempo = uso[0];
                    int posicaoMenor = 0;
                    int k = 1;
                    while (k < quantidadeQuadros) {
                        if (uso[k] < menorTempo) {
                            menorTempo = uso[k];
                            posicaoMenor = k;
                        }
                        k++;
                    }
                    int removida = memoria[posicaoMenor];
                    memoria[posicaoMenor] = pagina;
                    uso[posicaoMenor] = tempo;
                    System.out.print("FALTA #" + faltas + " | Saiu " + removida + ", entrou " + pagina + " > ");
                }
                mostrarMemoria(memoria, quantidadeOcupada);
            }
            indice++;
        }
        System.out.println("Total de faltas: " + faltas);
    }

    // MRU
    public static void mru(String nomeSequencia, int[] sequencia, int tamanhoSequencia, int quantidadeQuadros) {
        int[] memoria = new int[quantidadeQuadros];
        int[] uso = new int[quantidadeQuadros]; // controla qual foi a mais recente
        int quantidadeOcupada = 0;
        int faltas = 0;
        int tempo = 0;

        System.out.println("\n" + nomeSequencia + " (MRU)");
        int indice = 0;
        while (indice < tamanhoSequencia) {
            int pagina = sequencia[indice];
            tempo++;
            System.out.print("Acessa página " + pagina + " > ");

            int posicaoEncontrada = -1;
            int posicao = 0;
            // Verifica se a página já está na memória
            while (posicao < quantidadeOcupada) {
                if (memoria[posicao] == pagina) {
                    posicaoEncontrada = posicao;
                    break;
                }
                posicao++;
            }

            if (posicaoEncontrada != -1) {
                uso[posicaoEncontrada] = tempo; // atualiza o tempo de uso
                System.out.println("Página já estava na memória.");
            } else {
                faltas++;
                // Se ainda tiver espaço livre, só adiciona
                if (quantidadeOcupada < quantidadeQuadros) {
                    memoria[quantidadeOcupada] = pagina;
                    uso[quantidadeOcupada] = tempo;
                    quantidadeOcupada++;
                    System.out.print("FALTA #" + faltas + " | Adiciona > ");
                } else {
                    // Procura a mais recente pra remover
                    int maiorTempo = uso[0];
                    int posicaoMaior = 0;
                    int k = 1;
                    while (k < quantidadeQuadros) {
                        if (uso[k] > maiorTempo) {
                            maiorTempo = uso[k];
                            posicaoMaior = k;
                        }
                        k++;
                    }
                    int removida = memoria[posicaoMaior];
                    memoria[posicaoMaior] = pagina;
                    uso[posicaoMaior] = tempo;
                    System.out.print("FALTA #" + faltas + " | Saiu " + removida + ", entrou " + pagina + " > ");
                }
                mostrarMemoria(memoria, quantidadeOcupada);
            }
            indice++;
        }

        System.out.println("Total de faltas: " + faltas);
    }

    // Função auxiliar
    public static void mostrarMemoria(int[] memoria, int quantidadeOcupada) {
        System.out.print("[ ");
        int i = 0;
        // Mostra o conteúdo atual da memória
        while (i < quantidadeOcupada) {
            System.out.print(memoria[i] + " ");
            i++;
        }
        System.out.println("]");
    }

    // Main
    public static void main(String[] args) {
        int quantidadeQuadros = 8;

        // Sequências
        int[] sequenciaA = {4,3,25,8,19,6,25,8,16,35,45,22,8,3,16,25,7};
        int[] sequenciaB = {4,5,7,9,46,45,14,4,64,7,65,2,1,6,8,45,14,11};
        int[] sequenciaC = {4,6,7,8,1,6,10,15,16,4,2,1,4,6,12,15,16,11};

        // Tamanhos
        int tamanhoA = 17;
        int tamanhoB = 18;
        int tamanhoC = 18;

        // Executa as substituições para cada sequência
        fifo("SEQUÊNCIA A", sequenciaA, tamanhoA, quantidadeQuadros);
        lru("SEQUÊNCIA A", sequenciaA, tamanhoA, quantidadeQuadros);
        mru("SEQUÊNCIA A", sequenciaA, tamanhoA, quantidadeQuadros);

        fifo("SEQUÊNCIA B", sequenciaB, tamanhoB, quantidadeQuadros);
        lru("SEQUÊNCIA B", sequenciaB, tamanhoB, quantidadeQuadros);
        mru("SEQUÊNCIA B", sequenciaB, tamanhoB, quantidadeQuadros);

        fifo("SEQUÊNCIA C", sequenciaC, tamanhoC, quantidadeQuadros);
        lru("SEQUÊNCIA C", sequenciaC, tamanhoC, quantidadeQuadros);
        mru("SEQUÊNCIA C", sequenciaC, tamanhoC, quantidadeQuadros);
    }
}
