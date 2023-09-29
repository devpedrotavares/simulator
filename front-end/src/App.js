import {useState} from 'react';
import PaymentPage from "./PaymentPage"
import CreateCardPage from './CreateCardPage';

function App() {
  const [currentPage, setCurrentPage] = useState("InitialPage");

  return (
    <>
    <header setCurrentPage={setCurrentPage}>
      This will be the side bar 

      <button onClick={() => {setCurrentPage("PaymentPage")}}>Simular</button>
      <button onClick={() => {setCurrentPage("CreateCardPage")}}>Adicionar Cartão</button>
      <button onClick={() => {setCurrentPage("InitialPage")}}>Início</button>
    </header>

    <br></br>

    {currentPage === "PaymentPage" ? 
        <PaymentPage></PaymentPage>
        :
        (currentPage === "CreateCardPage" ? <CreateCardPage></CreateCardPage>
          : <h2>Welcome to the Initial Page</h2>
          )
    }
    </>
  );
}

export default App;
