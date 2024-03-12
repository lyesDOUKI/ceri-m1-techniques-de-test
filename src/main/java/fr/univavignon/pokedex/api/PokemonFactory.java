package fr.univavignon.pokedex.api;

public class PokemonFactory implements IPokemonFactory{
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        //on commence a créer le pokemon, puis en continue dans PokedexImplement
        return new Pokemon(index, "", 0, 0, 0, cp,
                hp, dust,candy ,0);
    }
}
