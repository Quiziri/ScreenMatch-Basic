package br.com.screenmatch.modelos;

public class Serie extends Titulo {
    private int temporadas = 0;
    private boolean ativa;
    private int episodiosPorTemporada;
    private int minutosPorEpsitodio;
    
    public Serie(String nome, int anoDeLancamento) {
        super(nome, anoDeLancamento);
    }

    
    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public int getEpisodiosPorTemporada() {
        return episodiosPorTemporada;
    }

    public void setEpisodiosPorTemporada(int episodiosPorTemporada) {
        this.episodiosPorTemporada = episodiosPorTemporada;
    }

    public int getMinutosPorEpsitodio() {
        return minutosPorEpsitodio;
    }

    public void setMinutosPorEpsitodio(int minutosPorEpsitodio) {
        this.minutosPorEpsitodio = minutosPorEpsitodio;
    }

    @Override
    public int getDuracaoEmMinutos(){
        return temporadas * episodiosPorTemporada * getMinutosPorEpsitodio();
    }

    public String toString() {
        return "Série: " + this.getNome() + " (" + this.getAnoDeLancamento() + ")";
    }
}
