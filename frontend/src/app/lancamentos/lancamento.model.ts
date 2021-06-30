export class Lancamento {
  codigo: number;
  descricao: string;
  dataVencimento: Date;
  dataPagamento: Date;
  valor: number;
  observacao: string;
  tipo = 'RECEITA';
  categoria = new CategoriaId();
  pessoa = new PessoaId();
}

class CategoriaId {
  codigo: number;
}

class PessoaId {
  codigo: number;
}
