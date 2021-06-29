import { Component, OnInit } from '@angular/core';
import { Categoria, CategoriaService } from 'src/app/categorias/categoria.service';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';

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

    pessoas = [
        { label: 'João da Silva', value: 1},
        { label: 'Sebastião Souza', value: 2},
        { label: 'Maria Abadia', value: 3}
    ];

    constructor(
      private categoriaService: CategoriaService,
      private errorHandlerService: ErrorHandlerService
    ) {}

    ngOnInit(): void {
      this.carregarCategorias();
    }

    carregarCategorias(): void {
      this.categoriaService.listarTodas()
      .then(response => {
        const categorias = response as Categoria[];
        this.categorias = categorias.map(cat => ({ label: cat.nome, value: cat.codigo }));
      })
      .catch(erro => this.errorHandlerService.handle(erro));
    }
}
