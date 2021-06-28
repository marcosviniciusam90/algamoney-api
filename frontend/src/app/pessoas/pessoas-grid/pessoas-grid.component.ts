import { Component, EventEmitter, Input, Output } from '@angular/core';
import { LazyLoadEvent } from 'primeng/api';

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

    aoMudarPagina(event: LazyLoadEvent): void {
      const pagina = event.first / event.rows;
      this.paginaAlterada.emit(pagina);
    }

}
