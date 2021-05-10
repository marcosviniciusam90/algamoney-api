import DataTable from "components/DataTable";
import Footer from "components/Footer";
import NavBar from "components/NavBar";

const Lancamentos = () => {
  return (
    <>
      <NavBar />
      <div className="container">
        <h1 className="text-primary py-3">Lançamentos</h1>

        <DataTable />
      </div>
      <Footer />
    </>
  );
}

export default Lancamentos;