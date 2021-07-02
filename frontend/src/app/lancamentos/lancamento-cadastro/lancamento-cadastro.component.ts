import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Categoria } from 'src/app/categorias/categoria.model';
import { CategoriaService } from 'src/app/categorias/categoria.service';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { PessoaResumo } from 'src/app/pessoas/pessoa.model';
import { PessoaService } from 'src/app/pessoas/pessoa.service';
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

    categorias: any[];
    pessoas: any[];
    lancamento = new Lancamento();

    constructor(
      private categoriaService: CategoriaService,
      private pessoaService: PessoaService,
      private lancamentoService: LancamentoService,
      private errorHandlerService: ErrorHandlerService,
      private messageService: MessageService,
      private route: ActivatedRoute
    ) {}

    ngOnInit(): void {
      const codigoLancamento = this.route.snapshot.params.codigo;
      if (codigoLancamento) {
        this.carregarLancamento(codigoLancamento);
      }

      this.carregarCategorias();
      this.carregarPessoas();
    }

    get editando(): boolean {
      return Boolean(this.lancamento.codigo);
    }

    carregarLancamento(codigo: number): void {
      this.lancamentoService.buscarPorCodigo(codigo)
        .then(lancamento => {
          this.lancamento = lancamento;
        })
        .catch(erro => this.errorHandlerService.handle(erro));

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
        const pessoas = response.content as PessoaResumo[];
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
