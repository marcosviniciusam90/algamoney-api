import axios from "axios";
import Pagination from "components/Pagination";
import { useEffect, useState } from "react";
import { LancamentoPage } from "types/lancamento";
import { formatLocalDate } from "utils/format";
import { BASE_URL } from "utils/requests";

const DataTable = () => {

  const [activePage, setActivePage] = useState(0);

  const [page, setPage] = useState<LancamentoPage>({
    first: true,
    last: true,
    number: 0,
    totalElements: 0,
    totalPages: 0
  });

  const changePage = (index: number) => {
    setActivePage(index);
  }

  useEffect(() => {
    const token = 'Bearer '.concat("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbkBhbGdhbW9uZXkuY29tIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sIm5vbWUiOiJBZG1pbmlzdHJhZG9yIiwiZXhwIjoxNjIwNjcxMDQ2LCJhdXRob3JpdGllcyI6WyJST0xFX0NBREFTVFJBUl9DQVRFR09SSUEiLCJST0xFX1BFU1FVSVNBUl9QRVNTT0EiLCJST0xFX1JFTU9WRVJfUEVTU09BIiwiUk9MRV9DQURBU1RSQVJfTEFOQ0FNRU5UTyIsIlJPTEVfUEVTUVVJU0FSX0xBTkNBTUVOVE8iLCJST0xFX1JFTU9WRVJfTEFOQ0FNRU5UTyIsIlJPTEVfQ0FEQVNUUkFSX1BFU1NPQSIsIlJPTEVfUEVTUVVJU0FSX0NBVEVHT1JJQSJdLCJqdGkiOiI1Njk1MDBkMy05MWRmLTQ0NDMtYTMzNC0yOTVkMjMxYTgyZGQiLCJjbGllbnRfaWQiOiJhbmd1bGFyIn0.HL_ae4WhPRlHN3XYcZuRGhcAM7iOTdOqLoqApZNiBcc"); 
    axios.get(`${BASE_URL}/lancamentos?resumo&size=10&page=${activePage}`, { headers: { Authorization: token } })
      .then(response => {
        setPage(response.data);
      })
  }, [activePage]);

  return (
    <>

      <Pagination page={page} onPageChange={changePage} />

      <div className="table-responsive">
        <table className="table table-striped table-sm">
          <thead>
            <tr>
              <th>Descrição</th>
              <th>Data venc.</th>
              <th>Data pag.</th>
              <th>Valor</th>
              <th>Tipo</th>
              <th>Categoria</th>
              <th>Pessoa</th>
            </tr>
          </thead>
          <tbody>
            {page.content?.map(item => (

              <tr key={item.codigo}>

                <td>{item.descricao}</td>
                <td>{formatLocalDate(item.dataVencimento, "dd/MM/yyyy")}</td>
                <td>{formatLocalDate(item.dataPagamento, "dd/MM/yyyy")}</td>
                <td>{item.valor.toFixed(2)}</td>
                <td>{item.tipo}</td>
                <td>{item.categoria}</td>
                <td>{item.pessoa}</td>

              </tr>

            ))
            }
          </tbody>
        </table>
      </div >
    </>
  );
}

export default DataTable;
