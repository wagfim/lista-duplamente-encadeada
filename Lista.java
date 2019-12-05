public class Lista {

    private No inicio;
    private No ultimo;
    private int quantidade;

    public void pushInicio(int dado) {
        No novoNo = new No(dado);
        if (isEmpty()) {
            inicio = ultimo = novoNo;
        } else {
            inicio.setAnterior(novoNo);
            novoNo.setProximo(inicio);
            inicio = novoNo;
        }
        quantidade++;
    }

    public void pushFim(int dado) {
        No novoNo = new No(dado);
        if (isEmpty()) {
            pushInicio(dado);
        } else {
            ultimo.setProximo(novoNo);
            novoNo.setAnterior(ultimo);
            ultimo = novoNo;
        }
        quantidade++;
    }

    public void pushMeio(int dado, int posicao) {
        if (isEmpty()) { //empty list
            pushInicio(dado);
        } else {
            if (posicao <= 0) { //push at the inicio
                pushInicio(dado);
            } else {
                if (posicao >= quantidade) { //push at the ultimo
                    pushFim(dado);
                } else {
                    No novoNo = new No(dado);
                    No atual = devolveNo(posicao);
                    atual.getAnterior().setProximo(novoNo);
                    novoNo.setAnterior(atual.getAnterior());
                    novoNo.setProximo(atual);
                    atual.setAnterior(novoNo);
                    quantidade++;
                }
            }
        }
    }

    public void delete(int position) {
        No atual = devolveNo(position);
        if (atual == null) {
            System.out.println("posição não existe ou fila vazia.");
        } else {
            //checa se o No atual é o primeiro ou o ultimo
            if (atual == inicio) {
                inicio = inicio.getProximo();
                inicio.setAnterior(null);
            } else {
                if (atual == ultimo) {
                    ultimo = ultimo.getAnterior();
                    ultimo.setProximo(null);
                } else {
                    atual.getAnterior().setProximo(atual.getProximo());
                    atual.getProximo().setAnterior(atual.getAnterior());
                }
            }
            quantidade--;
        }
    }

    public No devolveNo(int posicao) {
        int count = 0;
        No atual = inicio;
        while (atual != null) {
            if (count == posicao) {
                return atual;
            }
            count++;
            atual = atual.getProximo();
        }
        return null;
    }

    public int procuraPosicao(int dado) {
        int count = 0;
        No atual = inicio;
        while (atual != null) {
            if (atual.getDado() == dado) {
                return count;
            }
            count++;
            atual = atual.getProximo();
        }
        return -1;
    }

    public boolean isEmpty() {
        return inicio == null;
    }
    
    @Override
    public String toString() {
        String saida = "";
        No atual = inicio;
        while (atual != null) {
            saida += "[" + atual.getDado()+ "] ";
            atual = atual.getProximo();
        }
        saida += "[" + ultimo.getDado() + "]";
        return saida;
    }
     
}
