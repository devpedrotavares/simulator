function InitialPage({setPage}) {

    return (<>
                <div> 
                    <img className="vetor" src={require('./images/image1.png')} alt="Vetor de pagamentos"/> 
                </div>

                <div> 
                    O site proporciona aos consumidores uma experiência prática e educativa, permitindo que <br/> pagamentos com cartões de débito e crédito de maneira realista e segura, para melhor compreensão do processo.
                </div>
                
                <div>
                    <button className="comece_simular" onClick={setPage}> Comece a simular </button>
                </div>
            </>
    );
}

export default InitialPage;