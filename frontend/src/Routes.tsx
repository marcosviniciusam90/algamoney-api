import Lancamentos from 'pages/Lancamentos';
import Home from 'pages/Home';
import { BrowserRouter, Route, Switch} from 'react-router-dom';

const Routes = () => {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" exact> <Home/> </Route>
        <Route path="/lancamentos"> <Lancamentos/> </Route>

      </Switch>
    
    </BrowserRouter>
  );
}

export default Routes;