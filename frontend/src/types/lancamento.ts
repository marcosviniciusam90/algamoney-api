export type Lancamento = {
  codigo: number;
  descricao: string;
  dataVencimento: string;
  dataPagamento: string;
  valor: number;
  observacao: string;
  tipo: string;
  categoria: string;
  pessoa: string;
}

// ? -> indica que o atributo é opcional
export type LancamentoPage = {
  content?: Lancamento[];
  last: boolean;
  totalElements: number;
  totalPages: number;
  size?: number;
  number: number;
  first: boolean;
  numberOfElements?: number;
  empty?: boolean;
}