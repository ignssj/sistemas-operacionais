import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Main {
  static long valorTotal = 0;

  public static void main(String[] args) {

    int amostrasUserArg = Integer.parseInt(args[0]);
    int nrThreadsArg = Integer.parseInt(args[1]);
    int modoDeAcessoArg = Integer.parseInt(args[2]);

    int amostras = amostrasUserArg;
    int nrThreads = nrThreadsArg;
    int modoDeAcesso = modoDeAcessoArg;
    
    if(modoDeAcesso == 0){
      System.out.println("Quantidade de Amostras:" + amostras );
      System.out.println("Quantidade de threads:" + nrThreads );
      for (int i = 0; i < amostras; i++) {
        executarRandomicoThread(nrThreads, false);
        Data.MatrizEntrada = new int[1000][1000];
        Data.MatrizSaida = new int[1000][1000];
        Data.VetorAuxiliar = new int[101];
      }
      System.out.println("valor total:" + valorTotal);
      System.out.println("Media>" + valorTotal/ amostras);
    }
  }

  public static void executarSequencialmenteThreads(int nr, boolean isEscrita ) {
    SeparacaoThreads test = new SeparacaoThreads();
    Multithread threads[] =  test.setarThreads(nr, ModosInsercao.sequencial);
    long tempoInicial = System.currentTimeMillis();
    long tempoFinal = 0;
    System.out.println("\nInicio do processamento Sequencial");
    ExecutorService pool = Executors.newFixedThreadPool(threads.length);
    for (int i = 0; i < threads.length; i++) {
      pool.execute(threads[i]);
    }
    pool.shutdown();
    try {
      boolean b = pool.awaitTermination(1, TimeUnit.DAYS);

    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      Ordenacao ordenacao = new Ordenacao();
      ordenacao.ordenarOtimizado();
      System.out.println("Final do process de preenchimento Sequencial");
      tempoFinal = System.currentTimeMillis() - tempoInicial;
      System.out.printf("Tempo Final de Execução : %.3f ms%n\n", tempoFinal / 1000d);
      valorTotal = valorTotal + tempoFinal;
      if(isEscrita){
        System.out.println("Gravando No arquivo txt");
        Arquivo arquivo = new Arquivo();
        arquivo.imprimirMatriz(Data.MatrizSaida.length, Data.MatrizSaida, nr + "");
      }
    }

  }

  private static void executarRandomicoThread(int nr,boolean isEscrita) {
    SeparacaoThreads test = new SeparacaoThreads();
    Multithread threads[] = test.setarThreads(nr,  ModosInsercao.randomico);
    long tempoInicial = System.currentTimeMillis();
    long tempoFinal = 0;
    System.out.println("\nInicio do processamento randomico");
    ExecutorService pool = Executors.newFixedThreadPool(threads.length);
    for (int i = 0; i < threads.length; i++) {
      pool.execute(threads[i]);
    }
    pool.shutdown();
    try {
      boolean b = pool.awaitTermination(1, TimeUnit.DAYS);

    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      Ordenacao ordenacao = new Ordenacao();
      ordenacao.ordenarOtimizado();
      System.out.println("Final do process de preenchimento randomico");
      tempoFinal = System.currentTimeMillis() - tempoInicial;
      System.out.printf("Tempo Final de Execução : %.3f ms%n\n", tempoFinal / 1000d);
      valorTotal = valorTotal + tempoFinal;
      if(isEscrita){
        System.out.println("Gravando No arquivo txt");
        Arquivo arquivo = new Arquivo();
        arquivo.imprimirMatriz(Data.MatrizSaida.length, Data.MatrizSaida, nr + "");
      }
    }
  }
}


