import { Categoria } from '../categorias/categoria.service';
import { Pessoa } from '../pessoas/pessoa.service';

export class Lancamento {
  codigo: number;
  descricao: string;
  dataVencimento: Date;
  dataPagamento: Date;
  valor: number;
  observacao: string;
  tipo = 'RECEITA';
  categoria = new Categoria();
  pessoa = new Pessoa();
}
