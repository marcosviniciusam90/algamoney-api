import Footer from "components/Footer";
import NavBar from "components/NavBar";
import { Link } from "react-router-dom";

const Home = () => {
  return (
    <>
    <NavBar />
    <div className="container">
      <div className="jumbotron">
        <h1 className="display-4">AlgaMoney</h1>
        <p className="lead">Tenha total controle financeiro de sua empresa</p>
        <hr />
        <p>Esta aplicação permite o controle financeiro a partir de dados obtidos via requisições REST em um backend construído com Spring Boot.</p>
        <Link className="btn btn-primary btn-lg" to="/lancamentos">Ver lançamentos</Link>
      </div>
    </div>
    <Footer />
    </>
  );
}

export default Home;