import {useState} from 'react';
import PaymentPage from "./PaymentPage";
import CreateCardPage from './CreateCardPage';
import InitialPage from './InitialPage';
import AboutUsPage from './AboutUsPage';
import ContactPage from './ContactPage';
import "./css/styles.css";

function App() {
  const [currentPage, setCurrentPage] = useState(<InitialPage setCurrentPage={() => setCurrentPage(<PaymentPage/>)}/>);

  return (
    <>
    <div className="wrapper">

        <div className="sidebar">
          <h2>SimuladorDePagamentos</h2>
            <ul>
                <li onClick={() => {setCurrentPage(<InitialPage setCurrentPage={() => setCurrentPage(<PaymentPage/>)}/>)}}>
                  <a><i className="fas fa-home"></i>Início</a>
                </li>
                <li onClick={() => {setCurrentPage(<CreateCardPage/>)}}>
                  <a><i className="fas fa-user"></i>Adicionar cartões</a>
                </li>
                <li onClick={() => {setCurrentPage(<PaymentPage/>)}}>
                  <a><i className="fas fa-address-card"></i>Simulador</a>
                </li>
                <li onClick={() => {setCurrentPage(<AboutUsPage/>)}}>
                  <a><i className="fas fa-project-diagram"></i>Sobre nós</a>
                </li>
                <li onClick={() => {setCurrentPage(<ContactPage/>)}}>
                  <a><i className="fas fa-blog"></i>Contato</a>
                </li>
            </ul>

          <div className="social_media">
            <a href="#"><i className="fab fa-facebook-f"></i> <img className="github_vector" src={require('./images/image4.png')} alt="Vetor do Github"/></a>
          </div>

        </div>

        <div className="main_content">
          <div className="info">
            {currentPage}
          </div>
        </div>
    </div>
    </>
  );
}

export default App;
