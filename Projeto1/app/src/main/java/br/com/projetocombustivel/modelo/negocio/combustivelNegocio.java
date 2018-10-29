package br.com.projetocombustivel.modelo.negocio;

import br.com.projetocombustivel.modelo.entity.Combustivel;
import br.com.projetocombustivel.modelo.entity.Etanol;
import br.com.projetocombustivel.modelo.entity.Gasolina;

public class combustivelNegocio {



    public Combustivel Calcular(double gs, double et)
    {
        double resutl = et / gs;
        if(resutl <= 0.70)
            return  new Etanol();
        else
            return new Gasolina();
    }
}
