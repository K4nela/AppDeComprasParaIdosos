import React, { useState, useEffect } from "react";
import { api } from "../../api/api";
import { useAuth } from "../../contexts/AuthContext";


export default function Configuracoes() {
    const { user } = useAuth();
    const [dados, setDados] = useState({});


    useEffect(() => {
        if (!user) return;
        api.get(`/usuarios/${user.id}`).then(res => setDados(res.data)).catch(() => {});
    }, [user]);


    async function salvar() {
        try {
            await api.put(`/usuarios/${user.id}`, dados);
            alert('Atualizado');
        } catch (err) { alert('Erro ao salvar'); }
    }


    return (
        <div style={{ padding: 20 }}>
            <h3>Configurações de Perfil</h3>
            <label>Nome</label>
            <input value={dados.nome || ''} onChange={e => setDados({ ...dados, nome: e.target.value })} />
            <br />
            <label>Email</label>
            <input value={dados.email || ''} onChange={e => setDados({ ...dados, email: e.target.value })} />
            <br />
            <button onClick={salvar}>Salvar</button>
        </div>
    );
}