import { Component, OnInit } from '@angular/core';
import { LancamentoFiltro, LancamentoService } from '../lancamento.service';

@Component({
  selector: 'app-lancamentos-pesquisa',
  templateUrl: './lancamentos-pesquisa.component.html',
  styleUrls: ['./lancamentos-pesquisa.component.css']
})
export class LancamentosPesquisaComponent implements OnInit {

    filtro = new LancamentoFiltro();

    lancamentos: any = [];
    totalRegistros = 0;

    constructor(private lancamentoService: LancamentoService) {}

    ngOnInit(): void {
    }

    pesquisar(pagina = 0): void {
      this.filtro.pagina = pagina;

      this.lancamentoService.pesquisar(this.filtro)
        .then(response => {
          this.lancamentos = response.content;
          this.totalRegistros = response.totalElements;
        });
    }
}
