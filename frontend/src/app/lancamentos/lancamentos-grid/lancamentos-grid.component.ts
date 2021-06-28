import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { LazyLoadEvent } from 'primeng/api';

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

    @ViewChild('tabela') grid: any;

    aoMudarPagina(event: LazyLoadEvent): void {
      const pagina = event.first / event.rows;
      this.paginaAlterada.emit(pagina);
    }

    excluir(lancamento: any): void {
      this.lancamentoExcluido.emit(lancamento);
      this.grid.reset();
    }

}
