import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { ConfirmationService, LazyLoadEvent } from 'primeng/api';
import { Table } from 'primeng/table';

@Component({
  selector: 'app-lancamentos-grid',
  templateUrl: './lancamentos-grid.component.html',
  styleUrls: ['./lancamentos-grid.component.css']
})
export class LancamentosGridComponent {

    @Input() lancamentos: any = [];

    @Input() itensPorPagina: number;
    @Input() totalRegistros: number;

    @Output() paginaAlterada = new EventEmitter();
    @Output() lancamentoExcluido = new EventEmitter();

    @ViewChild('tabela') grid: Table;

    constructor(private confirmationService: ConfirmationService) {}

    aoMudarPagina(event: LazyLoadEvent): void {
      const pagina = event.first / event.rows;
      this.paginaAlterada.emit(pagina);
    }

    confirmarExclusao(lancamento: any): void {
      this.confirmationService.confirm({
        message: 'Tem certeza que deseja excluir?',
        accept: () => this.excluir(lancamento)
        // reject
      });

    }

    excluir(lancamento: any): void {
      this.lancamentoExcluido.emit(lancamento);
      this.grid.reset();
    }

}
