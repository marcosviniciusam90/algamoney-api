import { Component, OnInit } from '@angular/core';
import { Categoria, CategoriaService } from 'src/app/categorias/categoria.service';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { Pessoa, PessoaService } from 'src/app/pessoas/pessoa.service';

@Component({
  selector: 'app-lancamento-cadastro',
  templateUrl: './lancamento-cadastro.component.html',
  styleUrls: ['./lancamento-cadastro.component.css']
})
export class LancamentoCadastroComponent implements OnInit{

    tipos = [
        { label: 'Receita', value: 'RECEITA'},
        { label: 'Despesa', value: 'DESPESA'}
    ];

    categorias: any = [];

    pessoas: any = [];

    constructor(
      private categoriaService: CategoriaService,
      private pessoaService: PessoaService,
      private errorHandlerService: ErrorHandlerService
    ) {}

    ngOnInit(): void {
      this.carregarCategorias();
      this.carregarPessoas();
    }

    carregarCategorias(): void {
      this.categoriaService.listarTodas()
      .then(response => {
        const categorias = response as Categoria[];
        this.categorias = categorias.map(cat => ({ label: cat.nome, value: cat.codigo }));
      })
      .catch(erro => this.errorHandlerService.handle(erro));
    }

    carregarPessoas(): void {
      this.pessoaService.listarTodas()
      .then(response => {
        const pessoas = response.content as Pessoa[];
        this.pessoas = pessoas.map(pes => ({ label: pes.nome, value: pes.codigo }));
      })
      .catch(erro => this.errorHandlerService.handle(erro));
    }
}
