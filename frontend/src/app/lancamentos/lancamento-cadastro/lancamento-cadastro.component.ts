import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { Categoria, CategoriaService } from 'src/app/categorias/categoria.service';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { Pessoa, PessoaService } from 'src/app/pessoas/pessoa.service';
import { Lancamento } from '../lancamento.model';
import { LancamentoService } from '../lancamento.service';

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
    lancamento = new Lancamento();

    constructor(
      private categoriaService: CategoriaService,
      private pessoaService: PessoaService,
      private lancamentoService: LancamentoService,
      private errorHandlerService: ErrorHandlerService,
      private messageService: MessageService
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

    salvar(form: NgForm): void {
      this.lancamentoService.adicionar(this.lancamento)
      .then(() => {
        this.messageService.add({ severity: 'success', detail: 'Lançamento adicionado com sucesso!' });

        form.reset();
        this.lancamento = new Lancamento();
      })
      .catch(erro => this.errorHandlerService.handle(erro));
    }


}
