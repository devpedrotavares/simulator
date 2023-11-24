import {useState, useEffect} from 'react';

function PaymentPage() {
    const [cards, setCards] = useState([]);
    const [selectedCardId, setSelectedCardId] = useState(0);
    const [selectedCard, setSelectedCard] = useState({});
    const [value, setValue] = useState(0);
    const [paymentType, setPaymentType] = useState("debit");

    const BASE_URL = "http://localhost:8080";

    async function postPayment() {
      try{
        const response = await fetch(BASE_URL + "/payment", {
          method: 'POST',
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            "cardId":selectedCardId,
            "value":value,
            "paymentType":paymentType
          })
        })

        if(response.ok){
          fetchCards();

          alert("O pagamento foi efetuado com sucesso");
      }
      else{
          const body = await response.json();
          alert(body.message);
      }

      } catch(error){
          alert("Um erro ocorreu...");
          console.log(error);
      }
    }

    async function fetchCards(){
        try{
          const response = await fetch(BASE_URL + "/card", {
            method: 'GET'
          })
          
          const body = await response.json();

          setCards(body);
        } 
        catch(error){
            console.log(error);
        }
    }
  
    useEffect(() => {
      fetchCards();
    }, []);

    useEffect(() => {
      if(!cards || !selectedCardId) {
        return;
      }

      setSelectedCard(cards.filter(card => card.id === parseInt(selectedCardId, 10))[0]);
    }, [cards, selectedCardId]);

    const dateFormatter = new Intl.DateTimeFormat("pt-BR", {
      dateStyle: "full"
    })

    return(<>
              <div className="header"> 
                <img className="card_vector" src={require('./images/image2.png')} alt="Vetor de histórico"/> 
                <h2> Simulando pagamentos </h2> 
              </div>
              
              <div>
                Selecione o cartão
                <select onChange={(event) => setSelectedCardId(event.target.value)}>
                      <option value={0}>Selecione o cartão</option>
                      {cards?.map(card => (
                        <option key={card.id} value={card.id}> {card.name + " - " + card.number} </option>
                      ))}
                </select>

                </div>

                <div>
                  Selecione o tipo da operação
                  <select onChange={(event) => {setPaymentType(event.target.value)}}>
                      <option value="">Selecione</option>
                      <option value="credit">Crédito</option>
                      <option value="debit">Débito</option>
                  </select>
                </div>
                

                <div>
                  Defina o valor
                  <input type="number" placeholder="R$" onChange={(event) => {setValue(event.target.value)}}/>
                </div>

                <div>
                  <button id="comece_simular" onClick={postPayment}>Realizar pagamento</button>
                </div>

                <div className="historico"> 
                  <img className="historic_vector" src={require("./images/image3.png")} alt="Vetor de histórico"/> 
                  <h2> Histórico </h2> 
                </div>
                      
                <div className="tabelas">
                  <table>
                    <thead>
                      <tr>
                          <th> Data </th>
                          <th> Detalhes </th>
                          <th> Valor </th>
                      </tr>
                    </thead>

                    <tbody>
                        {selectedCard?.payments?.map(payment => (
                        <tr>
                            <td> {dateFormatter.format(new Date(payment.dateTime))} </td>
                            <td> {payment.paymentType === "debit" ? "débito" : "crédito"} </td>
                            <td> {payment.value} </td>
                        </tr>
                        ))}
                    </tbody>
                  </table>
                </div>
          </>
      );
}

export default PaymentPage;