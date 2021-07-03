import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { MessageService } from 'primeng/api';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { PessoaFiltro, PessoaService } from '../pessoa.service';

@Component({
  selector: 'app-pessoas-pesquisa',
  templateUrl: './pessoas-pesquisa.component.html',
  styleUrls: ['./pessoas-pesquisa.component.css']
})
export class PessoasPesquisaComponent implements OnInit {

    filtro = new PessoaFiltro();

    pessoas: any = [];
    totalRegistros = 0;

    constructor(
      private pessoaService: PessoaService,
      private messageService: MessageService,
      private errorHandlerService: ErrorHandlerService,
      private title: Title
    ) {}

    ngOnInit(): void {
      this.title.setTitle('Pesquisa de pessoas');
    }

    pesquisar(pagina = 0): void {
      this.filtro.pagina = pagina;

      this.pessoaService.pesquisar(this.filtro)
        .then(response => {
          this.pessoas = response.content;
          this.totalRegistros = response.totalElements;
        })
        .catch(erro => this.errorHandlerService.handle(erro));
    }

    excluir(pessoa: any): void {
      this.pessoaService.excluir(pessoa.codigo)
        .then(() => {
          this.pesquisar();
          this.messageService.add({ severity: 'success', detail: 'Pessoa excluída com sucesso!' });
        })
        .catch(erro => this.errorHandlerService.handle(erro));
    }

    alterarStatus(pessoa: any): void {
      const novoStatus = !pessoa.ativo;
      this.pessoaService.alterarStatus(pessoa.codigo, novoStatus)
        .then(() => {
          pessoa.ativo = novoStatus;

          this.messageService.add({
            severity: 'success',
            detail: `Pessoa ${novoStatus ? 'ativada' : 'desativada'} com sucesso!`
          });
        })
        .catch(erro => this.errorHandlerService.handle(erro));
    }

}
