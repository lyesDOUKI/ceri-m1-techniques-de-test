package fr.univavignon.pokedex.api;

/**
 * Implementation of the IPokemonFactory interface.
 * create a half created Pokemon object.
 * @see IPokemonFactory
 */
public class PokemonFactory implements IPokemonFactory{
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        //on commence a créer le pokemon, puis en continue dans PokedexImplement
        if(index < 0) {
            return null;
        }
        return new Pokemon(index, "", 0, 0, 0, cp,
                hp, dust,candy ,0);
    }
}
