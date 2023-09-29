import {useState, useEffect} from 'react';

function CreateCardPage() {
    const [balance, setBalance] = useState(0);
    const [expirationDate, setExpirationDate] = useState("");
    const [invoice, setInvoice] = useState(0);
    const [limit, setLimit] = useState(0);
    const [name, setName] = useState("");
    const [number, setNumber] = useState("");
    const [securityCode, setSecurityCode] = useState("");

    return(
        <>
            <h2>Create Card Page</h2>

            <input type="number" onChange={(event)=>setBalance(event.target.value)}></input>

            <input type="date" onChange={(event)=>setExpirationDate(event.target.value)}></input>

            <input type="number" onChange={(event)=>setInvoice(event.target.value)}></input>

            <input type="number" onChange={(event)=>setLimit(event.target.value)}></input>
            
            <input onChange={(event)=>{setName(event.target.value)}}></input>

            <input type="number" onChange={(event)=>setNumber(event.target.value)}></input>

            <input onChange={(event)=>{setSecurityCode(event.target.value)}}></input>
        </>
    );
}

export default CreateCardPage;