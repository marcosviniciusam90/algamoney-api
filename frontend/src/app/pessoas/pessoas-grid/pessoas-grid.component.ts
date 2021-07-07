import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { ConfirmationService, LazyLoadEvent } from 'primeng/api';
import { Table } from 'primeng/table';
import { AuthService } from 'src/app/seguranca/auth.service';

@Component({
  selector: 'app-pessoas-grid',
  templateUrl: './pessoas-grid.component.html',
  styleUrls: ['./pessoas-grid.component.css']
})
export class PessoasGridComponent {

    @Input() pessoas: any = [];

    @Input() itensPorPagina: number;
    @Input() totalRegistros: number;

    @Output() paginaAlterada = new EventEmitter();
    @Output() pessoaExcluida = new EventEmitter();
    @Output() statusAlterado = new EventEmitter();

    @ViewChild('tabela') grid: Table;

    constructor(
      private confirmationService: ConfirmationService,
      public authService: AuthService
    ) {}

    aoMudarPagina(event: LazyLoadEvent): void {
      const pagina = event.first / event.rows;
      this.paginaAlterada.emit(pagina);
    }

    confirmarExclusao(pessoa: any): void {
      this.confirmationService.confirm({
        message: 'Tem certeza que deseja excluir?',
        accept: () => this.excluir(pessoa)
        // reject
      });

    }

    excluir(pessoa: any): void {
      this.pessoaExcluida.emit(pessoa);
      this.grid.reset();
    }

    alterarStatus(pessoa: any): void {
      this.statusAlterado.emit(pessoa);
    }

}
