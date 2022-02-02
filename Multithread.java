// package main.oficial;
// import main.oficial.Data;
import java.util.Random;

public class Multithread extends Thread{
  private int colunaMax = Data.MatrizEntrada[0].length;
  private int linhaMax= Data.MatrizEntrada.length;
  private int posicaoInicial;
  private int posicaoFinal;
  private int nrThreads;
  private ModosInsercao modo;

  public Multithread(int posicaoInicial, int posicaoFinal, int nrThreads, ModosInsercao modo){
    this.posicaoInicial = posicaoInicial;
    this.posicaoFinal= posicaoFinal;
    this.nrThreads = nrThreads;
    this.modo = modo;
  }

  public void preencherRandomico(){
    Random gerador = new Random();
    int tam = (linhaMax * colunaMax) / nrThreads;
    int count=0,linha,coluna;
    Random geradorLinha = new Random();
    Random geradorColuna = new Random();
    while (count!=tam){
      linha =  geradorLinha.nextInt(posicaoFinal - posicaoInicial) + posicaoInicial;
      coluna = geradorColuna.nextInt(colunaMax);
      if (Data.MatrizEntrada[linha][coluna] == 0) {
        Data.MatrizEntrada[linha][coluna] = gerador.nextInt(100)+1;
        count++;
      }}
  }

  public void preencherSequencial(){
    Random gerador = new Random();
    for (int i = 0; i < linhaMax; i++) {
      for (int j = 0; j < colunaMax; j++) {
        Data.MatrizEntrada[j][i] = gerador.nextInt(100);
      }
    }
  }

  @Override
  public void run() {
    if(modo == ModosInsercao.randomico ){
      preencherRandomico();
    }
    if (modo == ModosInsercao.sequencial){
      preencherSequencial();
    }
    super.run();
  }
}
