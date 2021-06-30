import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { Pessoa } from '../pessoa.model';
import { PessoaService } from '../pessoa.service';

@Component({
  selector: 'app-pessoa-cadastro',
  templateUrl: './pessoa-cadastro.component.html',
  styleUrls: ['./pessoa-cadastro.component.css']
})
export class PessoaCadastroComponent {

  pessoa = new Pessoa();

  constructor(
    private pessoaService: PessoaService,
    private messageService: MessageService,
    private errorHandlerService: ErrorHandlerService
  ) {}

  salvar(form: NgForm): void {
    this.pessoaService.adicionar(this.pessoa)
    .then(() => {
      this.messageService.add({ severity: 'success', detail: 'Pessoa adicionada com sucesso!' });

      form.reset();
      this.pessoa = new Pessoa();
    })
    .catch(erro => this.errorHandlerService.handle(erro));
  }

}
