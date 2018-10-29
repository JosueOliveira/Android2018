package br.com.projetocombustivel.controle;

import br.com.projetocombustivel.modelo.entity.Combustivel;
import br.com.projetocombustivel.modelo.negocio.combustivelNegocio;

public class combustivelCotrole {
    combustivelNegocio cn = new combustivelNegocio();
    public Combustivel Calcular(String gs, String et)
    {
       double gasolina = Double.parseDouble(gs.toString());
       double etanol = Double.parseDouble(et.toString());
      return cn.Calcular(gasolina,etanol);
    }
}
