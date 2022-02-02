import java.util.Random;

public class Sequencial {

  public void start() {
    long tempoInicial = System.currentTimeMillis();
    long tempoFinal = 0;

    System.out.println("\nInicio do processamento");

    Ordenacao ordenar = new Ordenacao();
    ordenar.ordenarMatriz();
    tempoFinal = System.currentTimeMillis() - tempoInicial;

    System.out.printf("Tempo Final de Execução : %.3f ms%n", tempoFinal / 1000d);
    Arquivo arquivo = new Arquivo();
    System.out.println("Gravando No arquivo txt");
    arquivo.imprimirMatriz(Data.MatrizSaida.length, Data.MatrizSaida, "Sequencial");
  }

}




