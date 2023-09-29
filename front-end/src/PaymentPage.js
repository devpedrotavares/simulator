import {useState, useEffect} from 'react';

function PaymentPage() {
    const [cards, setCards] = useState([]);
    const [selectedCard, setSelectedCard] = useState({});
    const [value, setValue] = useState(0);
    const [type, setType] = useState("");

    const BASE_URL = "http://localhost:8080";

    async function postPayment(payment) {
      try{
        const response = await fetch(BASE_URL + "/payment", {
          method: 'POST',
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(payment)
        })

        if(response.ok){
          alert("O pagamento foi efetuado com sucesso");
        }
        else{
          alert("Não foi possível efetuar o pagamento...");
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
        fetchCards()
      }, []);

      const onCardSelect = (event) => {
        const newSelectedCard = cards.filter(card => card.id === event.target.value);

        setSelectedCard(newSelectedCard);
      }

      function handlePayment() {
          postPayment({
            "cardId" : selectedCard.id,
            "type": type,
            "value": value
          })
      }

    return(
        <>
        <p>Selecione o cartão</p>
        
        <select onChange={onCardSelect}>
              {cards.map(card => (
                <option value={card.id}> {card.name + " - " + card.number} </option>
              ))}
        </select>

        <p>Selecione o tipo da operação</p>

        <select onChange={(event) => {setType(event.target.value)}}>
              <option value="credit">Crédito</option>
              <option value="debit">Débito</option>
        </select>

        <p>Defina o valor</p>

        <input type="number" onChange={(event) => {setValue(event.target.value)}}/>

        <button onClick={handlePayment}>Realizar pagamento
        </button>
        
        </>
    );
}

export default PaymentPage;