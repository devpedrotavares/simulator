import {useState, useEffect} from 'react';

function CreateCardPage() {
    const [balance, setBalance] = useState(0);
    const [expirationDate, setExpirationDate] = useState("");
    const [invoice, setInvoice] = useState(0);
    const [limit, setLimit] = useState(0);
    const [name, setName] = useState("");
    const [number, setNumber] = useState("");
    const [securityCode, setSecurityCode] = useState("");

    const BASE_URL = "http://localhost:8080";

    async function postCard() {
        try{
            const response = await fetch(BASE_URL + "/card", {
                method: 'POST',
                headers: {
                "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    balance,
                    expirationDate,
                    invoice,
                    limit,
                    name,
                    number,
                    securityCode
                })
            })

            if(response.ok){
                alert("O cartão foi cadastrado com sucesso");
            }
            else{
                const body = await response.json();
                alert(body.message);
            }

        } 
        catch(error){
            alert("Um erro ocorreu...");
            console.log(error);
        }
    }

    return(
        <>
            <div className="header"> 
                <img className="card_vector" src={require('./images/image2.png')} alt="Vetor de cartão"/> 
                <h2> Adicionar cartões </h2> 
            </div>

            <div> Número do cartão 
                <input type="number" onChange={(event)=>setNumber(event.target.value)}></input> 
            </div>

            <div> 
                Saldo <input type="number" placeholder="R$" onChange={(event)=>setBalance(event.target.value)}></input> 
                Limite <input type="number" placeholder="R$" onChange={(event)=>setLimit(event.target.value)}></input> 
            </div>

            <div> 
                Titular <input onChange={(event)=>{setName(event.target.value)}}></input>
                Fatura <input type="number" placeholder="R$" onChange={(event)=>setInvoice(event.target.value)}></input>
            </div>

            <div> 
                Data de expiração <input type="date" onChange={(event)=>setExpirationDate(event.target.value)}></input> 
            </div>
            
            <div> 
                Código de segurança <input onChange={(event)=>{setSecurityCode(event.target.value)}}></input> 
            </div>

            <div> 
                <button id="comece_simular" onClick={postCard}> Adicionar cartão </button> 
            </div>
        </>
    );
}

export default CreateCardPage;